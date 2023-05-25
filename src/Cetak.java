import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Cetak {
    public static void main(String[] args) {
        SistemPesanan bookingSystem = new SistemPesanan();
        Scanner scanner = new Scanner(System.in);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                break;
            }
            
            String[] inputParts = input.split("\\|");
            String command = inputParts[0];
            
            if (command.equals("CREATE MEMBER")) {
                String[] memberInfo = inputParts[1].split("\\s+");
                String IDAnggota = memberInfo[0];
                String nama = memberInfo[1];
                Date tanggalDaftar;
                try {
                    tanggalDaftar = dateFormat.parse(memberInfo[2]);
                } catch (ParseException e) {
                    System.out.println("Format data tidak valid. Gunakan format yyyy/MM/dd.");
                    continue;
                }
                double saldoAwal = Double.parseDouble(memberInfo[3]);
                //method tambah anggota
                bookingSystem.tambahAnggota(IDAnggota, nama, tanggalDaftar, saldoAwal);

            } else if (command.equals("CREATE GUEST")) {
                String[] guestInfo = inputParts[1].split("\\s+");
                String IDTamu = guestInfo[0];
                double saldoAwal = Double.parseDouble(guestInfo[1]);
                //method tambah tamu
                bookingSystem.tambahTamu(IDTamu, saldoAwal);

            } else if (command.equals("CREATE MENU MAKANAN")) {
                String[] makananInfo = inputParts[1].split("\\s+");
                String IDMenu = makananInfo[0];
                String namaMenu = makananInfo[1];
                int harga = Integer.parseInt(makananInfo[2]);
                //method tambah makanan
                bookingSystem.tambahMakanan(IDMenu, namaMenu, harga);

            } else if (command.equals("CREATE MENU MINUMAN")) {
                String[] minumanInfo = inputParts[1].split("\\s+");
                String IDMenu = minumanInfo[0];
                String namaMenu = minumanInfo[1];
                int harga = Integer.parseInt(minumanInfo[2]);
                String tipeMinuman = minumanInfo[3];
                //method tambah minuman
                bookingSystem.tambahMinuman(IDMenu, namaMenu, harga, tipeMinuman);
            }
            else if (command.equals("CREATE PROMO DELIVERY")) {
                String kodePromo = inputParts[1];
                String tanggalMulai = inputParts[2];
                String tanggalBerakhir = inputParts[3];
                double diskon = Double.parseDouble(inputParts[4].replaceAll("%", "")) / 100.0;
                double maksimumPotongan = Double.parseDouble(inputParts[5]);
                double minimumPembelian = Double.parseDouble(inputParts[6]);
                
                SistemPesanan.tambahPromo(kodePromo, tanggalMulai, tanggalBerakhir, diskon,
                        maksimumPotongan, minimumPembelian);

            } else if (command.equals("CREATE PROMO CASHBACK")) {
                String kodePromo = inputParts[1];
                String tanggalMulai = inputParts[2];
                String tanggalBerakhir = inputParts[3];
                double diskon = Double.parseDouble(inputParts[4].replaceAll("%", "")) / 100.0;
                double maksimumPotongan = Double.parseDouble(inputParts[5]);
                double minimumPembelian = Double.parseDouble(inputParts[6]);
                
                SistemPesanan.tambahPromosiCashback(kodePromo, tanggalMulai, tanggalBerakhir, diskon,
                        maksimumPotongan, minimumPembelian);

            } else if (command.equals("CREATE PROMO DISCOUNT")) {
                String kodePromo = inputParts[1];
                String tanggalMulai = inputParts[2];
                String tanggalBerakhir = inputParts[3];
                double diskon = Double.parseDouble(inputParts[4].replaceAll("%", "")) / 100.0;
                double maksimumPotongan = Double.parseDouble(inputParts[5]);
                double minimumPembelian = Double.parseDouble(inputParts[6]);
                
                SistemPesanan.tambahPromosiDiskon(kodePromo, tanggalMulai, tanggalBerakhir, diskon,
                        maksimumPotongan, minimumPembelian);

            }else if (command.equals("ADD_TO_CART")) {
                String IDPelanggan = inputParts[1];
                String IDMenu = inputParts[2];
                int kuantitas = Integer.parseInt(inputParts[3]);

                SistemPesanan.tambahKeKeranjang(IDPelanggan, IDMenu, kuantitas);

            } else if (command.equals("REMOVE_FROM_CART")) {
                String IDPelanggan = inputParts[1];
                String IDMenu = inputParts[2];
                int kuantitas = Integer.parseInt(inputParts[3]);

                SistemPesanan.hapusDariKeranjang(IDPelanggan, IDMenu, kuantitas);

            } else if (command.equals("TOPUP")) {
                String IDPelanggan = inputParts[1];
                double jumlahTopUp = Double.parseDouble(inputParts[2]);

                bookingSystem.topUp(IDPelanggan, jumlahTopUp);
                
            } else if (command.equals("PRINT")) {
                String IDPelanggan = inputParts[1];
                bookingSystem.printKeranjang(IDPelanggan);

            } else if (command.equals("APPLY_PROMO")) {
                String IDPelanggan = inputParts[1];
                String kodePromo = inputParts[2];
                bookingSystem.applyPromo(IDPelanggan, kodePromo);

            } else if (command.equals("CHECK_OUT")) {
                String IDPelanggan = inputParts[1];
                bookingSystem.checkout(IDPelanggan);

            } else if (command.equals("PRINT_HISTORY")) {
                String IDPelanggan = inputParts[1];
                bookingSystem.printHistory(IDPelanggan);
            }
        }
        scanner.close();
    }
}