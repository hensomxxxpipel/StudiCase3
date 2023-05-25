import java.util.HashMap;
import java.util.Map;

public class Pesanan {
    private final double ongkosKirim = 15000;
    private Map<Menu, Integer> keranjang = new HashMap<>();
    private double diskon;
    private double totalHarga;

    public void tambahKeKeranjang(Menu menu, int kuantitas) {
        int jumlahMenu = keranjang.getOrDefault(menu, 0);
        jumlahMenu += kuantitas;
        keranjang.put(menu, jumlahMenu);
    }

    public void hapusDariKeranjang(Menu menu, int kuantitas) {
        int jumlahMenu = keranjang.getOrDefault(menu, 0);
        jumlahMenu -= kuantitas;
        if (jumlahMenu >= 1) {
            keranjang.put(menu, jumlahMenu);
        } else {
            keranjang.remove(menu);
        }
    }

    public int getJumlahMenu(Menu menu) {
        return keranjang.getOrDefault(menu, 0);
    }
    
    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double diskon) {
        double totalHarga = 0.0;
        for (Map.Entry<Menu, Integer> entry : keranjang.entrySet()) {
            Menu menu = entry.getKey();
            int kuantitas = entry.getValue();
            totalHarga += menu.getHarga() * kuantitas;
        }
        this.totalHarga = totalHarga;
    }

    public void setPromo(Promosi promo) throws Exception {
        if (promo instanceof PromoDiskon) {
            double diskon = promo.hitungDiskon(this);
            pasangDiskon(diskon);
        } else if (promo instanceof PromoCashback) {
            double cashback = promo.hitungCashback(this);
            tambahSaldo(cashback);
        } else if (promo instanceof PromoPengantaran) {
            double potonganOngkosKirim = promo.hitungPotonganOngkosKirim(this);
            pasangPotonganOngkosKirim(potonganOngkosKirim);
        } else {
            throw new Exception("Promo tidak valid");
        }
    }
    


    public void checkOut() {
        double totalHarga = getTotalHarga();
        if (totalHarga > 0) {
            System.out.println("Total harga pesanan: " + totalHarga);
            keranjang.clear();
        } else {
            System.out.println("Keranjang kosong. Tidak ada yang bisa di-checkout.");
        }
    }

    public double getOngkosKirim() {
        return ongkosKirim;
    }

    public void pasangDiskon(double diskon) {
        this.diskon = diskon;
    }

    public double totalBayar() {
        double totalHarga = getTotalHarga();
        double totalBayar = totalHarga + ongkosKirim - diskon;
        return totalBayar;
    }
}
