# Janji
Saya Muhammad 'Azmi Salam dengan NIM 2406010 mengerjakan Tugas Praktikum 5 pada Mata Kuliah Desain dan Pemrograman Berorientasi Objek (DPBO) untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin

# Struktur File
```
Main
├── ProductData/
│   ├── src/
│   │   ├── Database.java
│   │   ├── Product.java
│   │   ├── ProductMenu.java
│   │   └── ProductMenu.form
│   │   
│   └── lib/
│       └── mysql-connector-j-9.4.0.jar
│
├── Dokumentasi/
│   └── Record.mp4
│
├── db_product.sql
└── README.md
```

# Desain
Program mencakup __3__ class, yaitu __Product__, __ProductMenu__, dan __Database__. Berikut atribut dari masing-masing class:
- Product:
  - ID
  - Nama
  - Harga   
  - Stok
  - Kategori
  - Kualitas
- ProductMenu:
  - database
- Database:
  - connection
  - statement

# Penjelasan
Setelah program dijalankan, akan muncul sebuah jendela yang berisi:
- formulir input untuk `id`, `nama`, `harga`, `stok`, `kategori`, dan `kualitas`,
- tombol __Add__ untuk menambahkan produk baru ketika form diisi,
- tombol __Cancel__ untuk membatalkan input pada form, serta
- tabel yang menampilkan daftar produk dengan atribut-atribut tersebut.

Pengguna dapat mengisi form lalu menekan tombol __Add__ untuk menambahkan data produk ke dalam tabel. Selain itu, pengguna juga bisa memilih salah satu produk pada tabel untuk menampilkan detailnya di form. Setelah data produk muncul di form, pengguna dapat memperbarui atau menghapusnya menggunakan tombol __Update__ atau __Delete__ yang akan aktif setelah data dipilih.

Jika pengguna mengubah nilai pada form dan menekan tombol __Update__, maka data produk yang dipilih akan diperbarui di tabel. Sementara itu, ketika tombol __Delete__ ditekan, akan muncul jendela konfirmasi penghapusan, dan data produk akan dihapus apabila pengguna memilih tombol __Yes__.

Note:
Program ini mirip dengan [Tugas Praktikum 4](https://github.com/zicofarry/TP4DPBO2425C2), hanya saja menggunakan koneksi ke dalam database.

# Dokumentasi
https://github.com/user-attachments/assets/2fe40efa-ee48-49fe-8e8d-e27f10ffca46