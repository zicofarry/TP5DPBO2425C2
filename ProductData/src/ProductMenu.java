import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductMenu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        ProductMenu menu = new ProductMenu();

        // atur ukuran window
        menu.setSize(700, 600);

        // letakkan window di tengah layar
        menu.setLocationRelativeTo(null);

        // isi window
        menu.setContentPane(menu.mainPanel);

        // ubah warna background
        menu.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        menu.setVisible(true);

        // agar prgoram ikut berhenti saat window diclose
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua produk
    private ArrayList<Product> listProduct;
    private Database database;

    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField hargaField;
    private JTextField stokField;
    private JTable productTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox<String> kategoriComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel namaLabel;
    private JLabel hargaLabel;
    private JLabel stokLabel;
    private JLabel kategoriLabel;
    private JLabel kualitasLabel;
    private JSlider kualitasSlider;
    private JLabel kualitasValueLabel;

    // constructor
    public ProductMenu() {
        // inisialisasi listProduct
        listProduct = new ArrayList<>();

        // buat objek database
        database = new Database();

        // isi listProduct
        populateList();

        // isi tabel produk
        productTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] kategoriData = { "???", "Elektronik", "Makanan", "Minuman", "Pakaian", "Alat Tulis"};
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        kualitasSlider.setMinimum(1);
        kualitasSlider.setMaximum(5);
        kualitasSlider.setValue(3);
        kualitasSlider.setMajorTickSpacing(1);
        kualitasSlider.setPaintTicks(true);
        kualitasSlider.setPaintLabels(true);

        kualitasValueLabel.setText("3");

        kualitasSlider.addChangeListener(e -> {
            kualitasValueLabel.setText(String.valueOf(kualitasSlider.getValue()));
        });


        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedIndex == -1){
                    insertData();
                }else{
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO: tambahkan konfirmasi sebelum menghapus data
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION
                );

                // jika yes
                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        productTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = productTable.getSelectedRow();

                // simpan value textfield dan combo box
                String curId = productTable.getModel().getValueAt(selectedIndex, 0).toString();
                String curNama = productTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curHarga = productTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curStok = productTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = productTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curKualitas = productTable.getModel().getValueAt(selectedIndex, 5).toString();

                // ubah isi textfield dan combo box
                idField.setText(curId);
                namaField.setText(curNama);
                hargaField.setText(curHarga);
                stokField.setText(curStok);
                kategoriComboBox.setSelectedItem(curKategori);
                kualitasSlider.setValue(Integer.parseInt(curKualitas));

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] cols = {"ID Produk", "Nama", "Harga", "Stok", "Kategori", "Kualitas"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        try{
            ResultSet resultSet = database.selectQuery("SELECT * FROM product");

            // isi tabel dengan hasil query
            int i = 0;
            while(resultSet.next()){
                Object[] row = new Object[6];
                row[0] = resultSet.getString("id");
                row[1] = resultSet.getString("nama");
                row[2] = resultSet.getString("harga");
                row[3] = resultSet.getString("stok");
                row[4] = resultSet.getString("kategori");
                row[5] = resultSet.getString("kualitas");
                tmp.addRow(row);
                i++;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return tmp; // return juga harus diganti
    }

    public void insertData() {
        try {
            // ambil value dari textfield dan combobox
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int kualitas = kualitasSlider.getValue();

            // tambahkan data ke dalam database
            String sqlQuery = "INSERT INTO product VALUES ('" + id + "', '" + nama + "', " + harga + ", " + stok + ", '" + kategori + "', '" + kualitas + "')";
            database.insertUpdateDeleteQuery(sqlQuery);

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Insert berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void updateData() {
        try{
            // ambil data dari form
            String id = idField.getText();
            String nama = namaField.getText();
            double harga = Double.parseDouble(hargaField.getText());
            int stok = Integer.parseInt(stokField.getText());
            String kategori = kategoriComboBox.getSelectedItem().toString();
            int kualitas = kualitasSlider.getValue();

            // ubah data produk pada database
            String sqlQuery = "UPDATE product SET " +
                    "nama='" + nama + "', " +
                    "harga=" + harga + ", " +
                    "stok=" + stok + ", " +
                    "kategori='" + kategori + "', " +
                    "kualitas=" + kualitas +
                    " WHERE id='" + id + "'";
            database.insertUpdateDeleteQuery(sqlQuery);;

            // update tabel
            productTable.setModel(setTable());

            // bersihkan form
            clearForm();

            // feedback
            System.out.println("Update berhasil");
            JOptionPane.showMessageDialog(null, "Data berhasil diubah");
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void deleteData() {
        // hapus data dari database
        String id = idField.getText();
        String sqlQuery = "DELETE FROM product WHERE id='" + id + "'";
        database.insertUpdateDeleteQuery(sqlQuery);

        // update tabel
        productTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        System.out.println("Delete berhasil");
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        idField.setText("");
        namaField.setText("");
        hargaField.setText("");
        stokField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        kualitasSlider.setValue(3);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    // panggil prosedur ini untuk mengisi list produk
    private void populateList() {
        listProduct.add(new Product("P001", "Laptop Asus", 8500000.0, 15, "Elektronik", 4));
        listProduct.add(new Product("P002", "Mouse Logitech", 350000.0, 23, "Elektronik", 5));
        listProduct.add(new Product("P003", "Keyboard Mechanical", 750000.0, 41, "Elektronik", 4));
        listProduct.add(new Product("P004", "Roti Tawar", 15000.0, 16, "Makanan", 3));
        listProduct.add(new Product("P005", "Susu UHT", 12000.0, 12, "Minuman", 2));
        listProduct.add(new Product("P006", "Kemeja Putih", 125000.0, 25, "Pakaian", 1));
        listProduct.add(new Product("P007", "Celana Jeans", 200000.0, 33, "Pakaian", 4));
        listProduct.add(new Product("P008", "Pensil 2B", 3000.0, 54, "Alat Tulis", 3));
        listProduct.add(new Product("P009", "Buku Tulis", 8000.0, 14, "Alat Tulis", 4));
        listProduct.add(new Product("P010", "Air Mineral", 5000.0, 22, "Minuman", 5));
        listProduct.add(new Product("P011", "Smartphone Samsung", 4500000.0, 29, "Elektronik", 5));
        listProduct.add(new Product("P012", "Kue Brownies", 25000.0, 20, "Makanan", 4));
        listProduct.add(new Product("P013", "Jaket Hoodie", 180000.0, 70, "Pakaian", 5));
        listProduct.add(new Product("P014", "Pulpen Gel", 5000.0, 20, "Alat Tulis", 2));
        listProduct.add(new Product("P015", "Teh Botol", 8000.0, 15, "Minuman", 4));
    }
}