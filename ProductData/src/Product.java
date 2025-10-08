public class Product {
    private String id;
    private String nama;
    private double harga;
    private int stok;
    private String kategori;
    private int kualitas;

    public Product(String id, String nama, double harga, int stok, String kategori, int kualitas) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.kategori = kategori;
        this.kualitas = kualitas;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setKualitas(int kualitas) {
        this.kualitas = kualitas;
    }

    public String getId() {
        return this.id;
    }

    public String getNama() {
        return this.nama;
    }

    public double getHarga() {
        return this.harga;
    }

    public int getStok() {
        return this.stok;
    }

    public String getKategori() {
        return this.kategori;
    }

    public int getKualitas() {
        return this.kualitas;
    }
}
