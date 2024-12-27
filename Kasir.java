import java.util.Scanner;

public class Kasir {

    // Scanner Input
    static Scanner input = new Scanner(System.in);
    
    // Deklarasi pilih
    static int pilihMenuUtama, pilihMenuKasir, pilihMenuBarang, pilihHapusBarang, pilihMenuAdmin, pilihMenuOwner;
    
    // Deklarasi jumlah barang dan harga
    static int jumlahBarangDanHarga;
    
    // Deklarasi total jumlah barang dibeli
    static int totalBarangDiBeli = 0;
    
    // Deklarasi jumlah barang
    static int jumlahBarangDiBeli;
    
    // Deklarasi barang
    static String barangYangDiBeli, namaBarangBaru, barangSetelah;
    
    // Deklarasi ubah harga barang
    static int barangYangMauDiUbahHarganya;
    
    // Deklarasi harga
    static int hargaBarangDiBeli, hargaBarangBaru, hargaSetelah;
    
    
    // Membuat array
    static String[] barang = new String[100];
    static int[] harga = new int[100];
    static String[] barangDiBeli = new String[100];
    static int[] hargaDiBeli = new int[100];
    static int[] jumlahDiBeli = new int[100];
    
    // Pin untuk mengakses menu
    static int pinKasir, pinAdmin, pinOwner;
    
    // ============================= MENU UTAMA ======================================== //
    // Menu Utama
    static void menuUtama(){
            System.out.println("\nMenu Utama Bakso Kaki Sapi PPS");
            System.out.println("Dahanrejo, Suci, Kec. Manyar, Kabupaten Gresik, Jawa Timur 61151");
            System.out.println();
            System.out.println("1. Kasir");
            System.out.println("2. Admin");
            System.out.println("3. Owner");
            System.out.println("4. Selesai");
    }
    
    // ============================== HALAMAN KASIR =================================== //
    
    // Tampilan Menu Kasir
    static void menuKasir() {
        System.out.print("Masukkan pin kasir : ");
        pinKasir = input.nextInt();
        if (pinKasir != 2010113) {
            System.out.println("Maaf pin anda salah");
            return;
        }

        do {
            System.out.println("\nMenu Kasir Bakso Kaki Sapi PPS");
            System.out.println("Dahanrejo, Suci, Kec. Manyar, Kabupaten Gresik, Jawa Timur 61151");
            System.out.println();
            System.out.println("1. Lakukan Transaksi Pembelian");
            System.out.println("2. Selesai");
            System.out.print("Pilih : ");
            pilihMenuKasir = input.nextInt();

            switch (pilihMenuKasir) {
                case 1:
                    transaksiBarang();
                    break;

                case 2:
                    System.out.println("Kembali ke menu utama ..");
                    break;

                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }

        } while (pilihMenuKasir != 2);
    }
    
    // Menampilkan barang dan harga
    static void tampilkanBarang(){
        System.out.println("\nDaftar barang dan harga");
        for (int i = 0; i < barang.length; i++) {
            if (barang[i] != null || harga[i] > 0) {
                System.out.printf("%-3d %-20s Rp. %,6d\n", (i + 1), barang[i], harga[i]);
            }
        }
    }
    
    // Transaksi barang
    static void transaksiBarang() {
        do {
            tampilkanBarang();
            System.out.println("Isi angka 0 jika ingin selesai");

            // Memilih barang
            System.out.print("Pilih barang yang ingin dibeli : ");
            pilihMenuBarang = input.nextInt();

            if (pilihMenuBarang == 0) {
                System.out.println("Kembali ke menu utama ...");
                return;
            }
            if (pilihMenuBarang < 1 || pilihMenuBarang > barang.length) {
                System.out.println("Barang tidak valid, silahkan ulang lagi ...");

            }

            // Memasukkan jumlah 
            System.out.print("Masukkan jumlah yang ingin dibeli : ");
            jumlahBarangDiBeli = input.nextInt();
            barangYangDiBeli = barang[pilihMenuBarang - 1];
            hargaBarangDiBeli = harga[pilihMenuBarang - 1];

            // Mengecek barang apakah sudah masuk ke dalam transaksi
            boolean barangSudahDiBeli = false;
            for (int i = 0; i < totalBarangDiBeli; i++) {
                if (barang[i].equals(barangYangDiBeli)) {
                    jumlahDiBeli[i] += jumlahBarangDiBeli;
                    barangSudahDiBeli = true;
                    break;
                }
            }

            // Jika barang belum ada, tambahkan ke daftar transasksi
            if (!barangSudahDiBeli) {
                barangDiBeli[totalBarangDiBeli] = barangYangDiBeli;
                hargaDiBeli[totalBarangDiBeli] = hargaBarangDiBeli;
                jumlahDiBeli[totalBarangDiBeli] = jumlahBarangDiBeli;
                totalBarangDiBeli++;
            }
            
            // Menghitung harga
            int totalHarga = hitungHarga(hargaBarangDiBeli, jumlahBarangDiBeli);
            System.out.println("Berhasil membeli " + barangYangDiBeli + " sebanyak " + jumlahBarangDiBeli + " Rp. " + totalHarga);
        } while (true);
    }
    
    // Menghitung harga barang berdasarkan jumlah yang ingin dibeli
    static int hitungHarga(int harganya, int jumlahnya){
        return harganya * jumlahnya;
    }
    
    
    // ================================== MENU ADMIN ==================================== //
    // Tampilan menu admin
    static void menuAdmin() {
        System.out.print("Masukkan pin admin : ");
        if (!input.hasNextInt()) {
            System.out.println("Input harus berupa angka. Kembali ke menu utama...");
            input.nextLine(); 
            return;
        }
        pinAdmin = input.nextInt();
        if (pinAdmin != 2010113) {
            System.out.println("Maaf pin anda salah");
            return;
        }

        do {
            System.out.println("\nMenu Admin Bakso Kaki Sapi PPS");
            System.out.println("Dahanrejo, Suci, Kec. Manyar, Kabupaten Gresik, Jawa Timur 61151");
            System.out.println();
            System.out.println("1. Menampilkan barang dan harga");
            System.out.println("2. Menambah barang dan harga");
            System.out.println("3. Mengubah harga barang");
            System.out.println("4. Menghapus data barang (MasterBarang)");
            System.out.println("5. Selesai");
            System.out.print("Pilih menu admin: ");
            pilihMenuAdmin = input.nextInt();
            
            switch (pilihMenuAdmin) {
                case 1:
                    tampilkanBarang();
                    break;
                case 2:
                    menambahBarangDanHarga();
                    break;
                case 3:
                    mengubahHargaBarang();
                    break;
                case 4:
                    hapusBarang();
                    break;
                case 5:
                    System.out.println("Kembali ke menu utama ..");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        } while (pilihMenuAdmin != 5);
    }


    // Menambah barang dan harga
    static void menambahBarangDanHarga(){
        if (jumlahBarangDanHarga > barang.length){
            System.out.println("Barang penuh");
            return;
        }
        
        input.nextLine();
        System.out.print("Isi nama barang baru : "); 
        namaBarangBaru = input.nextLine();
        
        System.out.print("Isi harga barang baru : "); hargaBarangBaru = input.nextInt();
        if (namaBarangBaru == null || hargaBarangBaru < 0) {
            System.out.println("Harga barang baru tidak valid / harus diisi");
            return;
        }
        
        barang[jumlahBarangDanHarga] = namaBarangBaru;
        harga[jumlahBarangDanHarga] = hargaBarangBaru;
        jumlahBarangDanHarga++;
        System.out.println("Berhasil menambahkan " + namaBarangBaru + " dengan harga Rp. " + hargaBarangBaru);
    }
    
    // Mengubah harga barang
    static void mengubahHargaBarang(){
        tampilkanBarang();
        System.out.print("Pilih barang : "); barangYangMauDiUbahHarganya = input.nextInt();

        if (barangYangMauDiUbahHarganya < 1 || barangYangMauDiUbahHarganya > barang.length) {
            System.out.println("Barang tidak ditemukan.");
            return;
        }
        
        int index = barangYangMauDiUbahHarganya - 1;
        
        System.out.print("Harga yang mau diubah : "); hargaSetelah = input.nextInt();
        if (hargaSetelah <= 0){
            System.out.println("Harga tidak valid");
            return;
        }
        
        harga[index] = hargaSetelah;
        System.out.println("Berhasil mengubah harga menjadi Rp. " + hargaSetelah);
    }
    
    // Menghapus data barang (Master Barang)
    static void hapusBarang(){
        
        tampilkanBarang();
        System.out.println("Isi 0 jika ingin selesai");
        System.out.print("Pilih barang : "); pilihHapusBarang = input.nextInt();

        if (pilihHapusBarang == 0){
            System.out.println("Operasi selesai.");
            return;
        }

        if (pilihHapusBarang < 1 || pilihHapusBarang > jumlahBarangDanHarga) {
            System.out.println("Barang tidak ditemukan");
            return;
        }

        int index = pilihHapusBarang - 1;
        for (int i = index; i < jumlahBarangDanHarga - 1; i++) {
            barang[i] = barang[i + 1];
            harga[i] = harga[i + 1];
        }
        barang[jumlahBarangDanHarga - 1] = null;
        harga[jumlahBarangDanHarga - 1] = 0;
        jumlahBarangDanHarga--;

        System.out.println("Barang berhasil dihapus.");
        
    }
    
    // ================================== MENU OWNER ==================================== //
    // Menu Owner
    static void menuOwner(){
        System.out.print("Masukkan pin owner : "); pinOwner = input.nextInt();
        if (pinOwner != 2010113){
            System.out.println("Maaf, pin owner salah");
            return;
        }

        do {
            System.out.println("\nMenu Owner Bakso Kaki Sapi PPS");
            System.out.println("Dahanrejo, Suci, Kec. Manyar, Kabupaten Gresik, Jawa Timur 61151");
            System.out.println();
            System.out.println("1. Laporan Pemasukan Keuangan");
            System.out.println("2. Selesai");
            System.out.print("Pilih menu owner : ");
            pilihMenuOwner = input.nextInt();
            
            switch(pilihMenuOwner){
                case 1 :
                    laporanPemasukan();
                    break;
                case 2 :
                    System.out.println("Kembali ke menu utama ..");
                    break;
                default :
                    System.out.println("Pilihan tidak valid ..");
                    break;
            }
        }while (pilihMenuOwner != 2);
        
    }
    
    // Laporan Pemasukan
    static void laporanPemasukan(){
        int totalPemasukan = 0;
        // Hitung total pemasukan dan tampilkan laporan
        if(totalBarangDiBeli == 0){
            System.out.println("Barang kosong, silahkan lakukan transaksi..");
            return;
        }
        
        // Mengurutkan barang dan harga barang berdasarkan jumlah barang
        for(int i = 0; i < totalBarangDiBeli; i++){
            for(int j = 0; j < totalBarangDiBeli - 1 - i; j++){
                if(jumlahDiBeli[j] < jumlahDiBeli[j + 1]){
                    // Menukarkan jumlah
                    int tempJumlah = jumlahDiBeli[j];
                    jumlahDiBeli[j] = jumlahDiBeli[j + 1];
                    jumlahDiBeli[j + 1] = tempJumlah;
                    
                    // Menukarkan barang
                    String tempBarang = barangDiBeli[j];
                    barangDiBeli[j] = barangDiBeli[j + 1];
                    barangDiBeli[j + 1] = tempBarang;
                    
                    // Menukarkan harga
                    int tempHarga = hargaDiBeli[j];
                    hargaDiBeli[j] = hargaDiBeli[j + 1];
                    hargaDiBeli[j + 1] = tempHarga;
                    
                }
            }
        }
        
        // Output laporan 
        System.out.println("Laporan Pemasukan Bakso Kaki Sapi PPS");
        System.out.println("Dahanrejo, Suci, Kec. Manyar, Kabupaten Gresik, Jawa Timur 61151");
        System.out.println();
        System.out.println();
        System.out.println("Barang              Jumlah   Harga");
        for(int i = 0; i < totalBarangDiBeli; i++){
            System.out.printf("%-3d %-20s %-6d Rp. %,6d\n", (i + 1), barangDiBeli[i], jumlahDiBeli[i], hargaDiBeli[i] * jumlahDiBeli[i]);
            totalPemasukan += hargaDiBeli[i] * jumlahDiBeli[i];
        }
        System.out.println("Total Pemasukan : " + totalPemasukan);
        
    }
    
    // ================================== MAIN METHOD =================================== //
    
    public static void main(String[] args) {
        
        jumlahBarangDanHarga = 11;
        
        // Daftar barang
        barang[0] = "Bakso Biasa"; harga[0] = 22000;
        barang[1] = "Bakso Campur"; harga[1] = 22000;
        barang[2] = "Bakso Kekel"; harga[2] = 27000;
        barang[3] = "Mie Ayam Spesial"; harga[3] = 26000;
        barang[4] = "Siomay"; harga[4] = 15000;
        barang[5] = "Lontong"; harga[5] = 6000;
        barang[6] = "Gorengan Nikmat"; harga[6] = 10000;
        barang[7] = "Es Teh"; harga[7] = 4000;
        barang[8] = "Es Jeruk"; harga[8] = 7000;
        barang[9] = "Es Blewah"; harga[9] = 9000;
        barang[10] = "Teh Pucuk"; harga[10] = 6000;
        
        do{
            menuUtama();
            System.out.print("Pilih : "); pilihMenuUtama = input.nextInt();

            switch(pilihMenuUtama) {
                case 1:
                    menuKasir();
                    break;

                case 2:
                    menuAdmin();
                    break;

                case 3:
                    menuOwner();
                    break;

                case 4:
                    System.out.println("Terima kasih sudah menggunakan program");
                    break;

                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
        }while(pilihMenuUtama != 4);
    }
}
