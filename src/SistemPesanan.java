//import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.Date;

public class SistemPesanan {
    private Map<String, Anggota> daftarAnggota = new HashMap<>();
    private Map<String, Tamu> daftarTamu = new HashMap<>();
    //private Map<String, Menu> memu = new HashMap<>();
    private Map<String, Makanan> daftarMakanan = new HashMap<>();
    private Map<String, Minuman> daftarMinuman = new HashMap<>();
    private Map<String, Pesanan> daftarPesanan = new HashMap<>();
    private List<Promosi> daftarPromosi = new ArrayList<>();


    public void tambahAnggota(String IDAnggota, String nama, Date tanggalDaftar, double saldoAwal) {
        if (daftarAnggota.containsKey(IDAnggota)) {
            System.out.println("TAMBAH MEMBER GAGAL: " + IDAnggota + " SUDAH ADA");
        } else {
            Anggota anggota = new Anggota(IDAnggota, nama, tanggalDaftar, saldoAwal);
            daftarAnggota.put(IDAnggota, anggota);
            System.out.println("TAMBAH MEMBER SUKSES: " + IDAnggota + " " + nama);
        }
    }

    public void tambahTamu(String IDTamu, double saldoAwal) {
        if (daftarTamu.containsKey(IDTamu)) {
            System.out.println("TAMBAH GUEST GAGAL: " + IDTamu + " SUDAH ADA");
        } else {
            Tamu tamu = new Tamu(IDTamu, saldoAwal);
            daftarTamu.put(IDTamu, tamu);
            System.out.println("TAMBAH GUEST SUKSES: " + IDTamu + " " + saldoAwal);
        }
    }

    public void tambahMakanan(String IDMenu, String namaMenu, int harga) {
        if (daftarMakanan.containsKey(IDMenu)) {
            System.out.println("TAMBAH MAKANAN GAGAL: " + IDMenu + " SUDAH ADA");
        } else {
            Makanan makanan = new Makanan(IDMenu, namaMenu, harga);
            daftarMakanan.put(IDMenu, makanan);
            System.out.println("TAMBAH MAKANAN SUKSES: " + IDMenu + " " + namaMenu);
        }
    }

    public void tambahMinuman(String IDMenu, String namaMenu, int harga, String tipeMinuman) {
        if (daftarMinuman.containsKey(IDMenu)) {
            System.out.println("TAMBAH MINUMAN GAGAL: " + IDMenu + " SUDAH ADA");
        } else {
            Minuman minuman = new Minuman(IDMenu, namaMenu, harga, tipeMinuman);
            daftarMinuman.put(IDMenu, minuman);
            System.out.println("TAMBAH MINUMAN SUKSES: " + IDMenu + " " + namaMenu);
        }
    }

    public void tambahPesanan(String IDPelanggan, String IDMenu, int kuantitas) {
        if (daftarAnggota.containsKey(IDPelanggan) || daftarTamu.containsKey(IDPelanggan)) {
            Menu menu = null;
            if (daftarMakanan.containsKey(IDMenu)) {
                menu = daftarMakanan.get(IDMenu);
            } else if (daftarMinuman.containsKey(IDMenu)) {
                menu = daftarMinuman.get(IDMenu);
            }
            if (menu != null) {
                Pesanan pesanan = daftarPesanan.getOrDefault(IDPelanggan, new Pesanan());
                pesanan.tambahKeKeranjang(menu, kuantitas);
                daftarPesanan.put(IDPelanggan, pesanan);
                System.out.println("TAMBAH KE KERANJANG SUKSES: " + kuantitas + " " + menu.getNamaMenu());
            } else {
                System.out.println("TAMBAH KE KERANJANG GAGAL: MENU TIDAK ADA");
            }
        } else {
            System.out.println("TAMBAH KE KERANJANG GAGAL: PELANGGAN TIDAK DITEMUKAN");
        }
    }
    
    public void hapusDariPesanan(String IDPelanggan, String IDMenu, int kuantitas) {
        if (daftarAnggota.containsKey(IDPelanggan) || daftarTamu.containsKey(IDPelanggan)) {
            Menu menu = null;
            if (daftarMakanan.containsKey(IDMenu)) {
                menu = daftarMakanan.get(IDMenu);
            } else if (daftarMinuman.containsKey(IDMenu)) {
                menu = daftarMinuman.get(IDMenu);
            }
            if (menu != null) {
                Pesanan pesanan = daftarPesanan.get(IDPelanggan);
                if (pesanan != null) {
                    pesanan.hapusDariKeranjang(menu, kuantitas);
                    if (pesanan.getJumlahMenu(menu) >= 1) {
                        System.out.println("HAPUS DARI KERANJANG BERHASIL: " + menu.getNamaMenu() + " KUANTITAS DIKURANGI");
                    } else {
                        daftarPesanan.remove(IDPelanggan);
                        System.out.println("HAPUS DARI KERANJANG: " + menu.getNamaMenu() + " TELAH DIHAPUS");
                    }
                } else {
                    System.out.println("HAPUS DARI KERANJANG GAGAL: PELANGGAN TIDAK MEMILIKI KERANJANG BELANJA");
                }
            } else {
                System.out.println("HAPUS DARI KERANJANG GAGAL: MENU TIDAK ADA");
            }
        } else {
            System.out.println("HAPUS DARI KERANJANG GAGAL: PELANGGAN TIDAK DITEMUKAN");
        }
    }
    
    public void tambahPromo(String jenisPromo, String kodePromo, Date tanggalMulai, Date tanggalBerakhir, double diskon, double maksimumPotongan, double minimumPembelian) {
        Promosi promo;
        if (jenisPromo.equals("DELIVERY")) {
            promo = new PromoPengantaran(kodePromo, tanggalMulai, tanggalBerakhir, diskon, minimumPembelian, maksimumPotongan);
        } else if (jenisPromo.equals("CASHBACK")) {
            promo = new PromoCashback(kodePromo, tanggalMulai, tanggalBerakhir, diskon, maksimumPotongan, minimumPembelian);
        } else if (jenisPromo.equals("DISCOUNT")) {
            promo = new PromoDiskon(kodePromo, tanggalMulai, tanggalBerakhir, diskon, maksimumPotongan, minimumPembelian);
        } else {
            System.out.println("TAMBAH PROMO GAGAL: Jenis promo tidak valid");
            return;
        }
    
        if (daftarPromosi.contains(promo)) {
            System.out.println("TAMBAH PROMO GAGAL: " + kodePromo + " sudah ada");
        } else {
            daftarPromosi.add(promo);
            System.out.println("TAMBAH PROMO SUKSES: " + kodePromo);
        }
    }

    public void pasangPromo(String IDPelanggan, String kodePromo) throws Exception {
        Promosi promo = null;
        for (Promosi p : daftarPromosi) {
            if (p.getKodePromo().equals(kodePromo)) {
                promo = p;
                break;
            }
        }

        Anggota anggota = null;
        for (Anggota a : daftarAnggota.values()) {
            if (a.getIDPelanggan().equals(IDPelanggan)) {
                anggota = a;
                break;
            }
        }
    
        if (promo == null) {
            System.out.println("PASANG PROMO GAGAL: Promo " + kodePromo + " tidak ditemukan");
            return;
        }
    
        Pesanan pesanan = daftarPesanan.get(IDPelanggan);
        if (pesanan == null) {
            System.out.println("PASANG PROMO GAGAL: Keranjang belanja tidak ditemukan untuk pelanggan " + IDPelanggan);
            return;
        }
    
        if (promo.totalHargaMemenuhiSyarat(pesanan) != true) {
            System.out.println("PASANG PROMO GAGAL: Minimum pembelian tidak terpenuhi untuk promo " + kodePromo);
            return;
        }
        
        if (promo.pelangganMemenuhiSyarat(anggota)!= true) {
            System.out.println("PASANG PROMO GAGAL: Pelanggan tidak memenuhi syarat" + kodePromo);
            return;
        }
        pesanan.setPromo(promo);
    
        System.out.println("PASANG PROMO SUKSES: " + kodePromo);
    }
    
    public void topUp(String IDPelanggan, double jumlahTopUp) {
        Anggota anggota = daftarAnggota.get(IDPelanggan);
        if (anggota != null) {
            anggota.topUp(jumlahTopUp);
            System.out.println("TOPUP SUCCESS: " + anggota.getNama() + " " + jumlahTopUp + " => " + anggota.getSaldoAwal());
        } else {
            System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
        }
    }
    
    public void checkOut(String IDPelanggan) {
        Pesanan pesanan = daftarPesanan.get(IDPelanggan);
        if (pesanan != null) {
            if (daftarAnggota.containsKey(IDPelanggan)) {
                Anggota anggota = daftarAnggota.get(IDPelanggan);
                double totalHarga = pesanan.getTotalHarga();
                double saldoAwal = anggota.getSaldoAwal();
                if (saldoAwal >= totalHarga) {
                    anggota.kurangiSaldo(totalHarga);
                    // int pesananID = anggota.tambahPesanan(pesanan);
                    System.out.println("CHECK OUT SUKSES: " + anggota.getIDPelanggan() + " " + anggota.getNama());
                    daftarPesanan.remove(IDPelanggan);
                } else {
                    System.out.println("CHECK OUT GAGAL: " + anggota.getIDPelanggan() + " " + anggota.getNama() + " SALDO TIDAK CUKUP");
                }
            } else if (daftarTamu.containsKey(IDPelanggan)) {
                Tamu tamu = daftarTamu.get(IDPelanggan);
                System.out.println("CHECK OUT SUKSES: " + tamu.getIDPelanggan() + " " + tamu.getSaldoAwal());
                daftarPesanan.remove(IDPelanggan);
            } else {
                System.out.println("CHECK OUT GAGAL: PELANGGAN TIDAK ADA");
            }
        } else {
            System.out.println("CHECK OUT GAGAL: " + IDPelanggan + " tidak memiliki keranjang belanja");
        }
    }

    public void cetakPesanan() {
        
    }

    public void cetakRiwayatPesanan() {
        
    }

    public void cetakAnggota() {
        System.out.println("=== ===");
        for (Anggota anggota : daftarAnggota.values()) {
            System.out.println(anggota);
        }
    }

    public void cetakTamu() {
        System.out.println("=== TAMU ===");
        for (Tamu tamu : daftarTamu.values()) {
            System.out.println(tamu);
        }
    }

    public void cetakMenu() {
        System.out.println("=== MENU ===");
        System.out.println("=== MAKANAN ===");
        for (Makanan makanan : daftarMakanan.values()) {
            makanan.display();;
        }

        System.out.println("=== MINUMAN ===");
        for (Minuman minuman : daftarMinuman.values()) {
            minuman.display();
        }
    }

    public void cetakPromo() {
        System.out.println("=== PROMO ===");
        for (Promosi promosi : daftarPromosi) {
            System.out.println(promosi);
        }
    }
}