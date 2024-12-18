
abstract class Media {
    private int id;
    private String judul;
    private int waktu; // Waktu dalam detik

    public Media(int id, String judul, int waktu) {
        this.id = id;
        this.judul = judul;
        this.waktu = waktu;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public int getWaktu() {
        return waktu;
    }

    public abstract void play();
}
