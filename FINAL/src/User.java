class User {
    private int id;
    private String namaPengguna;
    private String kataSandi;

    public User(int id, String namaPengguna, String kataSandi) {
        this.id = id;
        this.namaPengguna = namaPengguna;
        this.kataSandi = kataSandi;
    }

    public int getId() {
        return id;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public String getKataSandi() {
        return kataSandi;
    }
}
