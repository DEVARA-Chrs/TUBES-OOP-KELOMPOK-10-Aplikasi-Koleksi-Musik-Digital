class Lagu extends Media {

    private String artis;
    private String album;
    private String genre;

    public Lagu(int id, String judul, int waktu, String artis, String album, String genre) {
        super(id, judul, waktu);
        this.artis = artis;
        this.album = album;
        this.genre = genre;
    }

    public String getArtis() {
        return artis;
    }

    public String getAlbum() {
        return album;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public void play() {
        System.out.println("Mulai nihhh: " + getJudul() + " sama si " + artis);
    }
}
