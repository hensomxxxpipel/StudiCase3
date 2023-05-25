import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum StatusPesanan {
    UNPAID,
    SUCCESSFUL,
    CANCELED
}

public class Keranjang{
    public static int jumlahPesanan = 0;

    private Date tanggalPesanan;
    private int nomorPesanan;
    //private double subTotal;
    private double ongkosKirim;
    private double totalDiskon;
    //private double totalHarga;
    private int kuantitas;
    private StatusPesanan statusPesanan;
    private List <Menu> menuPesanan;

    public Keranjang(List<Menu> menuPesanan, double ongkosKirim, double totalDiskon, int kuantitas) {
    
        this.tanggalPesanan = new Date();
        this.nomorPesanan = jumlahPesanan++;
        this.ongkosKirim = ongkosKirim;
        this.totalDiskon = totalDiskon;
        this.kuantitas = kuantitas;
        //this.totalHarga = totalHarga + ongkosKirim - totalDiskon;
        this.menuPesanan = menuPesanan;
        this.menuPesanan = new ArrayList<>();
        //this.menuPesanan.add(menuPesanan);
        this.statusPesanan = StatusPesanan.UNPAID;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public void setTanggalPesanan(Date tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public int getNomorPesanan() {
        return nomorPesanan;
    }

    // public double getSubTotal() {
    //     return subTotal;
    // }

    public double getOngkosKirim() {
        return ongkosKirim;
    }

    public double getTotalDiskon() {
        return totalDiskon;
    }

    public int getKuantitas() {
        return kuantitas;
    }

    public StatusPesanan getStatusPesanan() {
        return statusPesanan;
    }

    public void setStatusPesanan(StatusPesanan statusPesanan) {
        this.statusPesanan = statusPesanan;
    }

    public double getTotalHarga() {
        return hitungSubtotal() + ongkosKirim - totalDiskon;
    }

    public double hitungSubtotal() {
        double subtotal = 0;
        for (Menu menu : menuPesanan) {
            if (menu instanceof Makanan) {
                Makanan makanan = (Makanan) menu;
                subtotal += makanan.getHarga();
            } else if (menu instanceof Minuman) {
                Minuman minuman = (Minuman) menu;
                if (minuman.getTipeMinuman().equalsIgnoreCase("L")) {
                    subtotal += minuman.getHarga() * kuantitas;
                } else {
                    subtotal += minuman.getHarga() * kuantitas;
                }
            }
        }
        return subtotal;
    }


    public void printDetails() {
        System.out.println("===== NOTA PEMBELIAN =====");
        System.out.println("Tanggal Pesanan: " + tanggalPesanan);
        System.out.println("Nomor Pesanan: " + nomorPesanan);
        System.out.println("Kuantitas: " + kuantitas);
        System.out.println("Sub Total: " + hitungSubtotal());
        System.out.println("Ongkos Kirim: " + ongkosKirim);
        System.out.println("Total Diskon: " + totalDiskon);
        System.out.println("Total Harga: " + getTotalHarga());
        System.out.println("Status Pesanan: " + statusPesanan);
    }

    public void pay() {
        if (statusPesanan == StatusPesanan.UNPAID) {
            statusPesanan = StatusPesanan.SUCCESSFUL;
            System.out.println("Pesanan dengan nomor " + nomorPesanan + " telah berhasil dibayar.");
        } else {
            System.out.println("Pesanan dengan nomor " + nomorPesanan + " sudah dibayar sebelumnya.");
        }
    }
}