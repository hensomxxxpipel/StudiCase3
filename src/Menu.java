public abstract class Menu{
    private String IDMenu;
    private String namaMenu;
    private int harga;

    public Menu(String IDMenu, String namaMenu, int harga) {
        this.namaMenu = namaMenu;
        this.IDMenu = IDMenu;
        this.harga = harga;
    }
    

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }
    
    public void setIDMenu(String iDMenu) {
        IDMenu = iDMenu;
    }

    public String getIDMenu() {
        return IDMenu;
    }

   public void setHarga(int harga) {
       this.harga = harga;
   }

   public int getHarga() {
       return harga;
   }

    public void display() {
        System.out.printf("\n| %-20s | %-15s | %-13s | %-13s | %-13s | %-13s |\n", "Nama", "Ukuran","Harga Normal", "Harga Large", "Stok Normal", "Stok Large");
        
        System.out.printf("| %-20s | %-15s | %-13d |\n", getIDMenu(), getNamaMenu(), getHarga());
    }
}

class Makanan extends Menu{
    public Makanan(String IDMenu, String namaMenu, int harga){
        super(IDMenu, namaMenu, harga);
    }

    @Override
    public void display() {            
        System.out.printf("| %-20s | %-15s | %-15s |\n", getIDMenu(), getNamaMenu(), getHarga());
    }    
}

class Minuman extends Menu{
    private String tipeMinuman;

    public Minuman(String IDMenu, String namaMenu, int harga, String tipeMinuman){
        super(IDMenu, namaMenu, harga);
        this.tipeMinuman = tipeMinuman;
    }

    public void setTipeMinuman(String tipeMinuman){
        this.tipeMinuman = tipeMinuman;
    }

    public String getTipeMinuman() {
        return tipeMinuman;
    }

    @Override
    public void display() {        
        System.out.printf("| %-20s | %-15s | %-15s | %-13s |\n", getIDMenu(), getNamaMenu(), getHarga(), getTipeMinuman());
    }
}