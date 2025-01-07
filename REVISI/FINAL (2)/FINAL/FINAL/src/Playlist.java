import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String nama;
    private List<Lagu> laguList;

    public Playlist(String nama) {
        this.nama = nama;
        this.laguList = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public List<Lagu> getLaguList() {
        return laguList;
    }

    public void addLagu(Lagu lagu) {
        laguList.add(lagu);
    }

    public int getTotalDurasi() {
        int total = 0;
        for (Lagu lagu : laguList) {
            total += lagu.getWaktu();
        }
        return total;
    }
    public void addActivity(String activity) {
    }
    
}