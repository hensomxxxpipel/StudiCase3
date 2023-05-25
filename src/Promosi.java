import java.util.Date;

interface DapatDiterapkan {
    boolean pelangganMemenuhiSyarat(Pelanggan pelanggan);
    boolean totalHargaMemenuhiSyarat(Pesanan pesanan);
    boolean ongkosKirimMemenuhiSyarat(Pesanan pesanan);
    double hitungDiskon(Pesanan pesanan) throws Exception;
    double hitungCashback(Pesanan pesanan) throws Exception;
    double hitungPotonganOngkosKirim(Pesanan pesanan) throws Exception;
}

public abstract class Promosi implements DapatDiterapkan{
    private String kodePromo;
    private Date tanggalMulai;
    private Date tanggalBerakhir;
    private double diskon;
    private double maksimumPotongan;
    private double minimumPembelian;

    public Promosi(String kodePromo, Date tanggalMulai, Date tanggalBerakhir, double diskon, double maksimumPotongan, double minimumPembelian) {
        this.kodePromo = kodePromo;
        this.tanggalMulai = tanggalMulai;
        this.tanggalBerakhir = tanggalBerakhir;
        this.diskon = diskon / 100;
        this.maksimumPotongan = maksimumPotongan;
        this.minimumPembelian = minimumPembelian;
    }

    @Override
    public boolean pelangganMemenuhiSyarat(Pelanggan pelanggan) {
        return pelanggan.isAnggota();
    }

    @Override
    public boolean totalHargaMemenuhiSyarat(Pesanan pesanan) {
        return pesanan.getTotalHarga() >= minimumPembelian;
    }

    @Override
    public boolean ongkosKirimMemenuhiSyarat(Pesanan pesanan) {
        return true; // Implementasi logika syarat ongkos kirim
    }

    @Override
    public double hitungDiskon(Pesanan pesanan) throws Exception {
        if (totalHargaMemenuhiSyarat(pesanan)) {
            double potongan = pesanan.getTotalHarga() * diskon;
            return Math.min(potongan, maksimumPotongan);
        } else {
            throw new Exception("Total harga tidak memenuhi syarat promosi");
        }
    }

    @Override
    public double hitungCashback(Pesanan pesanan) throws Exception {
        throw new Exception("Promosi ini tidak mendukung cashback");
    }

    @Override
    public double hitungPotonganOngkosKirim(Pesanan pesanan) throws Exception {
        throw new Exception("Promosi ini tidak mendukung potongan ongkos kirim");
    }
    
    public String getKodePromo() {
        return kodePromo;
    }

    public void setKodePromo(String kodePromo) {
        this.kodePromo = kodePromo;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalBerakhir() {
        return tanggalBerakhir;
    }

    public void setTanggalBerakhir(Date tanggalBerakhir) {
        this.tanggalBerakhir = tanggalBerakhir;
    }

    public double getDiskon() {
        return diskon;
    }

    public void setDiskon(double diskon) {
        this.diskon = diskon/100;
    }

    public double getMinimumPembelian() {
        return minimumPembelian;
    }

    public void setMinimumPembelian(double minimumPembelian) {
        this.minimumPembelian = minimumPembelian;
    }

    public double getMaksimumPotongan() {
        return maksimumPotongan;
    }

    public void setMaksimumPotongan(double maksimumPotongan) {
        this.maksimumPotongan = maksimumPotongan;
    }

    public int dibandingkanDengan(Promosi promosi) {
        if (this.diskon > promosi.diskon) {
            return -1;
        } else if (this.diskon < promosi.diskon) {
            return 1;
        } else {
            return 0;
        }
    }
}

class PromoDiskon extends Promosi {
    
    public PromoDiskon(String kodePromo, Date tanggalMulai, Date tanggalBerakhir, double diskon, double maksimumPotongan, double minimumPembelian) {
        super(kodePromo, tanggalMulai, tanggalBerakhir, diskon, minimumPembelian, maksimumPotongan);
    }

    @Override
    public double hitungDiskon(Pesanan pesanan) throws Exception {
        if (totalHargaMemenuhiSyarat(pesanan)) {
            double potongan = pesanan.getTotalHarga() * getDiskon();
            return Math.min(potongan, getMaksimumPotongan());
        } else {
            throw new Exception("Total harga tidak memenuhi syarat promosi");
        }
    }

    @Override
    public boolean pelangganMemenuhiSyarat(Pelanggan pelanggan) {
        Anggota anggota = (Anggota) pelanggan;
        if (pelanggan.isAnggota() && anggota.getLamaMenjadiAnggota() > 30) {
            return true;
        } else {
            return false;
        }
    }

}

class PromoCashback extends Promosi {
    public PromoCashback(String kodePromo, Date tanggalMulai, Date tanggalBerakhir, double diskon, double maksimumPotongan, double minimumPembelian) {
        super(kodePromo, tanggalMulai, tanggalBerakhir, diskon, minimumPembelian, maksimumPotongan);
    }

    @Override
    public double hitungCashback(Pesanan pesanan) throws Exception {
        if (totalHargaMemenuhiSyarat(pesanan)) {
            return Math.min(pesanan.getTotalHarga() * getDiskon(), getMaksimumPotongan());
        } else {
            throw new Exception("Total harga tidak memenuhi syarat promosi");
        }
    }
}

class PromoPengantaran extends Promosi {
    public PromoPengantaran(String kodePromo, Date tanggalMulai, Date tanggalBerakhir, double diskon, double maksimumPotongan, double minimumPembelian) {
        super(kodePromo, tanggalMulai, tanggalBerakhir, diskon, minimumPembelian, maksimumPotongan);
    }

    @Override
    public double hitungPotonganOngkosKirim(Pesanan pesanan) throws Exception {
        // Implementasi perhitungan potongan ongkos kirim untuk promo delivery
        if (ongkosKirimMemenuhiSyarat(pesanan)) {
            return Math.min(pesanan.getOngkosKirim() * getDiskon(), getMaksimumPotongan());
        } else {
            throw new Exception("Ongkos kirim tidak memenuhi syarat promosi");
        }
    }
}





// import java.time.LocalDate;
// import java.util.Date;

// interface DapatDiterapkan {
//     boolean pelangganMemenuhiSyarat(Pelanggan pelanggan);
//     boolean totalHargaMemenuhiSyarat(Pesanan pesanan);
//     boolean ongkosKirimMemenuhiSyarat(Pesanan pesanan);
//     double hitungDiskon(Pesanan pesanan);
//     double hitungCashback(Pesanan pesanan);
//     double hitungPotonganOngkosKirim(Pesanan pesanan);
// }

// public abstract class Promosi implements DapatDiterapkan {
//     private String kodePromo;
//     private LocalDate berlakuMulai;
//     private LocalDate berlakuHingga;

//     public Promosi(String kodePromo, LocalDate berlakuMulai, LocalDate berlakuHingga) {
//         this.kodePromo = kodePromo;
//         this.berlakuMulai = berlakuMulai;
//         this.berlakuHingga = berlakuHingga;
//     }

//     public String getKodePromo() {
//         return kodePromo;
//     }

//     public LocalDate getBerlakuMulai() {
//         return berlakuMulai;
//     }

//     public LocalDate getBerlakuHingga() {
//         return berlakuHingga;
//     }

//     @Override
//     public int compareTo(Promosi o) {
//         return o.getBerlakuHingga().compareTo(berlakuHingga);
//     }
// }

// class PromoDiskonPersen extends Promosi {
//     private double persenDiskon;

//     public PromoDiskonPersen(String kodePromo, LocalDate berlakuMulai, LocalDate berlakuHingga, double persenDiskon) {
//         super(kodePromo, berlakuMulai, berlakuHingga);
//         this.persenDiskon = persenDiskon;
//     }

//     @Override
//     public boolean pelangganMemenuhiSyarat(Pelanggan x) {
//         if (x instanceof Tamu) {
//             return false;
//         } else {
//             Member member = (Member) x;
//             LocalDate tigaPuluhHariLalu = LocalDate.now().minusDays(30);
//             return member.getTanggalMenjadiAnggota().isBefore(tigaPuluhHariLalu);
//         }
//     }

//     @Override
//     public boolean totalHargaMemenuhiSyarat(Pesanan x) {
//         return x.getTotalHarga() >= 100000;
//     }

//     @Override
//     public boolean ongkosKirimMemenuhiSyarat(Pesanan x) {
//         return false;
//     }

//     @Override
//     public double hitungDiskon(Pesanan x, Pelanggan y) {
//         if (pelangganMemenuhiSyarat(y.getJenisPelanggan()) && totalHargaMemenuhiSyarat(x)) {
//             return x.getTotalHarga() * persenDiskon / 100;
//         } else {
//             return 0;
//         }
//     }

//     @Override
//     public double hitungCashback(Pesanan x) {
//         return 0;
//     }

//     @Override
//     public double hitungPotonganOngkosKirim(Pesanan x) {
//         return 0;
//     }
// }

// class PromoCashback extends Promosi {
//     private int totalMinimum;
//     private int jumlahCashback;

//     public PromoCashback(String kodePromo, LocalDate berlakuMulai, LocalDate berlakuHingga, int totalMinimum, int jumlahCashback) {
//         super(kodePromo, berlakuMulai, berlakuHingga);
//         this.totalMinimum = totalMinimum;
//         this.jumlahCashback = jumlahCashback;
//     }

//     public int getTotalMinimum() {
//         return totalMinimum;
//     }

//     public int getJumlahCashback() {
//         return jumlahCashback;
//     }

//     @Override
//     public boolean pelangganMemenuhiSyarat(Pelanggan pelanggan) {
//         if (pelanggan instanceof Tamu) {
//             return false;
//         } else {
//             Member member = (Member) pelanggan;
//             LocalDate tigaPuluhHariLalu = LocalDate.now().minusDays(30);
//             return member.getTanggalMenjadiAnggota().isBefore(tigaPuluhHariLalu);
//         }
//     }

//     @Override
//     public boolean totalHargaMemenuhiSyarat(Pesanan pesanan) {
//         return pesanan.getTotalHarga() >= totalMinimum;
//     }

//     @Override
//     public boolean ongkosKirim


// // import java.time.*;

// // interface Applicable {
// //     boolean isCustomerEligible(Pelanggan pelanggan);
// //     boolean isMinimumPriceEligible(Pesanan pesanan);
// //     boolean isShippingFeeEligible(Pesanan pesanan);
// //     double calculateDiscount(Pesanan pesanan);
// //     double calculateCashback(Pesanan pesanan);
// //     double calculateShippingFeeDiscount(Pesanan pesanan);
// // }

// // public abstract class Promosi implements Applicable {
// //     private String promoCode;
// //     private Date validFrom;
// //     private Date validUntil;

// //     public Promosi(String promoCode, LocalDate validFrom, LocalDate validUntil) {
// //         this.promoCode = promoCode;
// //         this.validFrom = validFrom;
// //         this.validUntil = validUntil;
// //     }

// //     public String getPromoCode() {
// //         return promoCode;
// //     }

// //     public LocalDate getValidFrom() {
// //         return validFrom;
// //     }

// //     public LocalDate getValidUntil() {
// //         return validUntil;
// //     }

// //     @Override
// //     public int compareTo(Promosi o) {
// //         return o.getValidUntil().compareTo(validUntil);
// //     }
// // }

// // class PercentOffPromo extends Promosi {
// //     private double percentOff;

// //     public PercentOffPromo(String promoCode, LocalDate validFrom, LocalDate validUntil, double percentOff) {
// //         super(promoCode, validFrom, validUntil);
// //         this.percentOff = percentOff;
// //     }

// //     @Override
// //     public boolean isCustomerEligible(Pelanggan x) {
// //         if (x instanceof Guest) {
// //             return false;
// //         } else {
// //             Member member = (Member) x;
// //             LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
// //             return member.getTanggalMenjadiAnggota().isBefore(thirtyDaysAgo);
// //         }
// //     }

// //     @Override
// //     public boolean isMinimumPriceEligible(Pesanan x) {
// //         return x.getTotalHarga() >= 100000;
// //     }

// //     @Override
// //     public boolean isShippingFeeEligible(Pesanan x) {
// //         return false;
// //     }

// //     @Override
// //     public double calculateDiscount(Pesanan x, Pelanggan y) {
// //         if (isCustomerEligible(y.getJenisPelanggan()) && isMinimumPriceEligible(x)) {
// //             return x.getTotalHarga() * percentOff / 100;
// //         } else {
// //             return 0;
// //         }
// //     }

// //     @Override
// //     public double calculateCashback(Pesanan x) {
// //         return 0;
// //     }

// //     @Override
// //     public double calculateShippingFeeDiscount(Pesanan x) {
// //         return 0;
// //     }
// // }


// // class CashbackPromo extends Promosi {
// //     private int minimumPrice;
// //     private int cashbackAmount;

// //     public CashbackPromo(String promoCode, LocalDate validFrom, LocalDate validUntil, int minimumPrice, int cashbackAmount) {
// //         super(promoCode, validFrom, validUntil);
// //         this.minimumPrice = minimumPrice;
// //         this.cashbackAmount = cashbackAmount;
// //     }

// //     public int getMinimumPrice() {
// //         return minimumPrice;
// //     }

// //     public int getCashbackAmount() {
// //         return cashbackAmount;
// //     }

// //     @Override
// //     public boolean isCustomerEligible(Pelanggan pelanggan) {
// //         if (pelanggan instanceof Guest) {
// //             return false;
// //         } else {
// //             Member member = (Member) pelanggan;
// //             LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
// //             return member.getTanggalMenjadiAnggota().isBefore(thirtyDaysAgo);
// //         }
// //     }

// //     @Override
// //     public boolean isMinimumPriceEligible(Pesanan pesanan) {
// //         return pesanan.getTotalHarga() >= minimumPrice;
// //     }

// //     @Override
// //     public boolean isShippingFeeEligible(Pesanan pesanan) {
// //         return true;
// //     }

// //     @Override
// //     public double calculateDiscount(Pesanan pesanan) {
// //         if (isCustomerEligible(pesanan.getJenisPelanggan()) && isMinimumPriceEligible(pesanan)) {
// //             return cashbackAmount;
// //         } else {
// //             return 0;
// //         }
// //     }

// //     @Override
// //     public double calculateCashback(Pesanan pesanan) {
// //         return 0;
// //     }

// //     @Override
// //     public double calculateShippingFeeDiscount(Pesanan pesanan) {
// //         return 0;
// //     }
// // }

// // class FreeShippingPromo extends Promosi {
// //     private int minimumPrice;

// //     public FreeShippingPromo(String promoCode, LocalDate validFrom, LocalDate validUntil, int minimumPrice) {
// //         super(promoCode, validFrom, validUntil);
// //         this.minimumPrice = minimumPrice;
// //     }

// //     public int getMinimumPrice() {
// //         return minimumPrice;
// //     }

// //     @Override
// //     public boolean isCustomerEligible(Pelanggan x) {
// //         if (x instanceof Guest) {
// //             return false;
// //         } else {
// //             Member member = (Member) x;
// //             LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
// //             return member.getTanggalMenjadiAnggota().isBefore(thirtyDaysAgo);
// //         }
// //     }

// //     @Override
// //     public boolean isMinimumPriceEligible(Pesanan x) {
// //         return x.getTotalHarga() >= minimumPrice;
// //     }

// //     @Override
// //     public boolean isShippingFeeEligible(Pesanan x) {
// //         return true;
// //     }

// //     @Override
// //     public double calculateDiscount(Pesanan x) {
// //         return 0;
// //     }

// //     @Override
// //     public double calculateCashback(Pesanan x) {
// //         return 0;
// //     }

// //     @Override
// //     public double calculateShippingFeeDiscount(Pesanan x) {
// //         if (isCustomerEligible(x.getJenisPelanggan()) && isMinimumPriceEligible(x)) {
// //             return x.getOngkosKirim();
// //         } else {
// //             return 0;
// //         }
// //     }
// // }