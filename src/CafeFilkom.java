public class CafeFilkom {
    public static void main(String[] args) {
        SistemPesanan admin = new SistemPesanan();

        admin.tambahMakanan("M001", "Nasi Goreng", 20000);
        admin.tambahMinuman("M002", "Kopi", 20000, "L");
        admin.tambahMakanan("M001", "Nasi Goreng", 20000);
        
        admin.tambahAnggota("A001", "Jhony Shin", null, 10000);

        //admin.cetakMenu();

        admin.tambahPesanan("A001", "M001", 2);
        admin.tambahPesanan("A001", "M002", 2);
        
        admin.tambahPromo("DELIVERY", "A6M0", null, null, 50, 20000, 30000);
        
    }
}
// // import java.util.*;

// public class CafeFilkom {
//     public static void main(String[] args) {
//         // Scanner masuk = new Scanner(System.in);

//         // System.out.print("Banyak macam Makanan : ");
//         // int banyak = masuk.nextInt();
//         // masuk.nextLine();

//         // Makanan[] Makanan = new Makanan[banyak];
//         // String nama;
//         // String ukuran;

//         // for (int i = 0; i < Makanan.length; i++) {
//         //     System.out.print("Nama Makanan : ");
//         //     nama = masuk.nextLine();

//         //     System.out.print("Ukuran : ");
//         //     ukuran = masuk.nextLine();

//         //     System.out.print("Jenis Makanan : ");
//         //     String jenisMakanan = masuk.nextLine();

//         //     try {
//         //         System.out.print("Harga Size Normal : ");
//         //         int hargaNormal = masuk.nextInt();

//         //         System.out.print("Harga Size Large : ");
//         //         int hargaLarge = masuk.nextInt();

//         //             System.out.print("Stok Makanan : ");
//         //         int stokMakanan = masuk.nextInt();
//         //         masuk.nextLine();
//         //         Makanan[i] = new Makanan(nama, ukuran, hargaNormal, hargaLarge, stokMakanan, jenisMakanan);
//         //     } catch (InputMismatchException e) {
//         //         System.err.println("Harus Menggunakan Angka!");
//         //     }
//         //     System.out.println("\n");
//         // }
        
//         // System.out.print("Banyak macam Minuman : ");
//         // int banyak2 = masuk.nextInt();
//         // masuk.nextLine();
        
//         // Minuman[] Minuman = new Minuman[banyak2];
        
//         // for (int i = 0; i < Minuman.length; i++) {
//         //     System.out.print("Nama Minuman : ");
//         //     nama = masuk.nextLine();
            
//         //     System.out.print("Ukuran : ");
//         //     ukuran = masuk.nextLine();

//         //     System.out.print("Jenis Minuman : ");
//         //     String jenisMinuman = masuk.nextLine();
            
//         //     System.out.print("Harga Size Normal : ");
//         //     int hargaNormal = masuk.nextInt();
            
//         //     System.out.print("Harga Size Large : ");
//         //     int hargaLarge = masuk.nextInt();
            
//         //     System.out.print("Stok Normal : ");
//         //     int stokNormal = masuk.nextInt();
            
//         //     System.out.print("Stok Large : ");
//         //     int stokLarge = masuk.nextInt();
//         //     masuk.nextLine();

//         //     Minuman[i] = new Minuman(nama, ukuran, hargaNormal, hargaLarge, stokNormal, stokLarge, jenisMinuman);
            
//         //     System.out.println("");
            
//         // }
        
        
        
//         Makanan nasgor = new Makanan("Nasi Goreng", "Normal & Jumbo", 10000, 15000, 50, "Kering");
//         Makanan bakmi = new Makanan("Bakmi", "Normal & Jumbo", 10000, 15000, 50, "Kering/Kuah");
//         Makanan indomie = new Makanan("Indomie", "Normal & Double", 6000, 10000, 50, "Goreng/Kuah");

//         Menu[] Makanan = {nasgor, bakmi, indomie};

//         Minuman Teh = new Minuman("Teh", "Normal/Jumbo", 3000, 5000, 50, 50, "Panas/Es");
//         Minuman kopiHitam = new Minuman("Kopi Hitam", "Cangkir/Gelas", 3000, 5000, 50, 50, "Panas/Es");
//         Minuman kopiSusu = new Minuman("Kopi Susu", "Cangkir/Gelas", 3000, 5000, 50, 50, "Panas/Es");

//         Menu[] Minuman = {Teh, kopiHitam, kopiSusu};

//         // Hiasan
//         System.out.printf("| %53s%52s|\n|", "MAKANAN", " ");
//         for (int i = 0; i < 106; i++) {
//             System.out.print("_");
//         }

//         System.out.print("|");
//         System.out.printf("\n| %-20s | %-15s | %-15s | %-13s | %-13s | %-13s |\n", "Nama","Ukuran","Jenis", "Harga Normal", "Harga Large", "Stok Normal");

//         for (Menu makanan : Makanan) {
//             makanan.display();
//         }

//         // Hiasan
//         System.out.print("|");
//         for (int i = 0; i < 106; i++) {
//             System.out.print("_");
//         }
//         System.out.println("|");
        
        
//         // Hiasa aja
//         System.out.printf("\n| %61s%60s|\n", "MINUMAN", " ");
//         System.out.print("|");
//         for (int i = 0; i < 122; i++) {
//             System.out.print("_");
//         }
//         System.out.print("|");
//         System.out.printf("\n| %-20s | %-15s | %-15s | %-13s | %-13s | %-13s | %-13s |\n", "Nama", "Ukuran", "Jenis", "Harga Normal", "Harga Large", "Stok Normal", "Stok Large");

//         for (Menu minuman : Minuman) {
//             minuman.display();
//         }

//         // Hiasan
//         System.out.print("|");
//         for (int i = 0; i < 122; i++) {
//             System.out.print("_");
//         }
//         System.out.println("|");


//         //Pesanan pesanan 1 =
//     }
// }