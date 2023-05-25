import java.util.*;

public abstract class Pelanggan {
    private String IDPelanggan;
    private double saldoAwal;

	public Pelanggan(String IDPelanggan, double saldoAwal) {
        this.IDPelanggan = IDPelanggan;
        this.saldoAwal = saldoAwal;
    }

    public String getIDPelanggan() {
        return IDPelanggan;
    }

    public void setIDPelanggan(String kodePelanggan) {
        this.IDPelanggan = kodePelanggan;
    }

    public double getSaldoAwal() {
        return saldoAwal;
    }

    public void setSaldoAwal(double saldoAwal) {
        this.saldoAwal = saldoAwal;
    }

    public abstract boolean isAnggota();
}

class Tamu extends Pelanggan {
    public Tamu(String IDPelanggan, double saldoAwal) {
        super(IDPelanggan, saldoAwal);
    }

    @Override
    public boolean isAnggota() {
        // TODO Auto-generated method stub
        return false;
    }
}

class Anggota extends Pelanggan {
    private String nama;
    private Date tanggalMenjadiAnggota;

    public Anggota(String IDPelanggan, String nama, Date tanggalMenjadiAnggota, double saldoAwal) {
        super(IDPelanggan, saldoAwal);
        this.nama = nama;
        this.tanggalMenjadiAnggota = tanggalMenjadiAnggota;
    }

    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalMenjadiAnggota() {
        return tanggalMenjadiAnggota;
    }

    public int getLamaMenjadiAnggota() {
        Date sekarang = new Date();
        long millisecondsPerDay = 24 * 60 * 60 * 1000;
        long lamaMenjadiAnggota = sekarang.getTime() - tanggalMenjadiAnggota.getTime();
        int hari = (int) (lamaMenjadiAnggota/millisecondsPerDay);
        return hari;
    }
    
    @Override
    public boolean isAnggota() {
        // TODO Auto-generated method stub
        return true;
    }

    public void topUp(double saldoBaru) {
        setSaldoAwal(getSaldoAwal() + saldoBaru);
        System.out.println("TOPUP SUCCESS: " + getNama() + " " + saldoBaru + " => " + getSaldoAwal());
    }

    public void kurangiSaldo(double jumlah) {
        setSaldoAwal(getSaldoAwal() - jumlah);
    }

    public void checkOut() {
        
    }

}

/**
 * Main
 */
// class Main {
//     public static void main(String[] args) {
//         List<Menu> menu = new ArrayList<>();
//         Member p1 = new Member("Ival", "Hitam");
//         System.out.println(p1.isMember());
//         Menu makanan1 = new Makanan("Nasi Goreng", "N", 10000, 15000, 50, "Kering");
//         menu.add(makanan1);
//         Menu minuman1 = new Minuman("Teh", "L", 3000, 5000, 50, 50, "ES");
//         menu.add(minuman1);
//         Pesanan m001 = new Pesanan(menu, 5000, 0, 1);
//         m001.printDetails();
//         p1.confirmPay(m001);
       
        
//     }
    
// }