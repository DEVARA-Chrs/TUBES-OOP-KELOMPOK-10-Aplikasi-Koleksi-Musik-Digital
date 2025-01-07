import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AplikasiMusik extends JFrame {
    private static LaguManager laguManager = new LaguManager();
    private DefaultTableModel tableModel;
    private static List<Playlist> daftarPlaylist = new ArrayList<>();

    public AplikasiMusik() {
        setTitle("APLIKASI MUSIK_KELOMPOK 10");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.darkGray);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 10, 10));
        loginPanel.setBackground(Color.darkGray);
        JLabel welcomeLabel = new JLabel("MP3 RASA SPOTIFY", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.MAGENTA);
        loginPanel.add(welcomeLabel);

        JButton btnAdmin = new JButton("Login sebagai Admin");
        JButton btnUser = new JButton("Login sebagai Pengguna");
        btnAdmin.setBackground(Color.darkGray);
        btnAdmin.setForeground(Color.MAGENTA);
        btnUser.setBackground(Color.DARK_GRAY);
        btnUser.setForeground(Color.MAGENTA);

        loginPanel.add(btnAdmin);
        loginPanel.add(btnUser);

        add(loginPanel, BorderLayout.CENTER);

        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuAdmin();
            }
        });

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPengguna();
            }
        });

        setVisible(true);
    }

    private void menuAdmin() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(7, 1, 10, 10));
        menuPanel.setBackground(Color.darkGray);

        JButton btnAdd = new JButton("Tambah Lagu");
        btnAdd.setBackground(Color.darkGray);
        btnAdd.setForeground(Color.MAGENTA);
        JButton btnView = new JButton("Lihat Daftar Lagu");
        btnView.setBackground(Color.darkGray);
        btnView.setForeground(Color.MAGENTA);
        JButton btnExit = new JButton("Keluar");
        btnExit.setBackground(Color.darkGray);
        btnExit.setForeground(Color.MAGENTA);

        menuPanel.add(btnAdd);
        menuPanel.add(btnView);
        menuPanel.add(btnExit);

        add(menuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        btnAdd.addActionListener(e -> tambahLagu());
        btnView.addActionListener(e -> lihatLagu());
        btnExit.addActionListener(e -> kembaliKeLogin());
    }

    private void menuPengguna() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());
    
        JPanel penggunaPanel = new JPanel();
        penggunaPanel.setLayout(new GridLayout(5, 1, 10, 10));
        penggunaPanel.setBackground(Color.darkGray);
    
        JButton btnView = new JButton("Lihat Daftar Lagu");
        btnView.setBackground(Color.darkGray);
        btnView.setForeground(Color.MAGENTA);
        JButton btnCreatePlaylist = new JButton("Buat Playlist");
        btnCreatePlaylist.setBackground(Color.darkGray);
        btnCreatePlaylist.setForeground(Color.MAGENTA);
        JButton btnViewAllPlaylists = new JButton("Lihat Semua Playlist Anda");
        btnViewAllPlaylists.setBackground(Color.darkGray);
        btnViewAllPlaylists.setForeground(Color.MAGENTA);
        JButton btnExit = new JButton("Keluar");
        btnExit.setBackground(Color.darkGray);
        btnExit.setForeground(Color.MAGENTA);
    
        penggunaPanel.add(btnView);
        penggunaPanel.add(btnCreatePlaylist);
        penggunaPanel.add(btnViewAllPlaylists);
        penggunaPanel.add(btnExit);
    
        add(penggunaPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    
        btnView.addActionListener(e -> lihatLaguPengguna()); 
        btnCreatePlaylist.addActionListener(e -> buatPlaylist());
        btnViewAllPlaylists.addActionListener(e -> lihatSemuaPlaylist());
        btnExit.addActionListener(e -> kembaliKeLogin());
    }

    private void lihatLaguPengguna() {
        String[] columns = {"ID", "Judul", "Artis", "Album", "Genre", "Waktu"};
        List<Lagu> laguList = laguManager.readAll();
        Object[][] data = new Object[laguList.size()][6];
    
        for (int i = 0; i < laguList.size(); i++) {
            Lagu lagu = laguList.get(i);
            data[i][0] = lagu.getId();
            data[i][1] = lagu.getJudul();
            data[i][2] = lagu.getArtis();
            data[i][3] = lagu.getAlbum();
            data[i][4] = lagu.getGenre();
            data[i][5] = lagu.getWaktu();
        }
    
        tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
    
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setForeground(Color.magenta);
        JButton btnBack = new JButton("Kembali");
        btnBack.setBackground(Color.darkGray);
        btnBack.setForeground(Color.MAGENTA);
        JButton btnAddToPlaylist = new JButton("Tambah ke Playlist");
        btnAddToPlaylist.setBackground(Color.darkGray);
        btnAddToPlaylist.setForeground(Color.MAGENTA);

        buttonPanel.add(btnBack);
        buttonPanel.add(btnAddToPlaylist);
    
        add(buttonPanel, BorderLayout.SOUTH);
    
        revalidate();
        repaint();
    
        btnBack.addActionListener(e -> menuPengguna());
        btnAddToPlaylist.addActionListener(e -> tambahLaguKePlaylist(table));
    }
    
    private void kembaliKeLogin() {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 10, 10));
        loginPanel.setBackground(Color.darkGray);
        JLabel welcomeLabel = new JLabel("MP3 RASA SPOTIFY", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        welcomeLabel.setForeground(Color.MAGENTA);
        loginPanel.add(welcomeLabel);

        JButton btnAdmin = new JButton("Login sebagai Admin");
        JButton btnUser = new JButton("Login sebagai Pengguna");
        btnAdmin.setBackground(Color.darkGray);
        btnAdmin.setForeground(Color.magenta);
        btnUser.setBackground(Color.darkGray);
        btnUser.setForeground(Color.magenta);

        loginPanel.add(btnAdmin);
        loginPanel.add(btnUser);

        add(loginPanel, BorderLayout.CENTER);

        btnAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuAdmin();
            }
        });

        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuPengguna();
            }
        });

        revalidate();
        repaint();
    }

    private void tambahLagu() {
        boolean validInput = false;
        String judul = "";
        String artis = "";
        String album = "";
        String genre = "";
        int waktu = 0;
    
        while (!validInput) {
            JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
    
            JTextField txtJudul = new JTextField(judul);
            JTextField txtArtis = new JTextField(artis);
            JTextField txtAlbum = new JTextField(album);
            JTextField txtGenre = new JTextField(genre);
            JTextField txtWaktu = new JTextField(String.valueOf(waktu));
    
            formPanel.add(new JLabel("Judul:"));
            formPanel.add(txtJudul);
            formPanel.add(new JLabel("Artis:"));
            formPanel.add(txtArtis);
            formPanel.add(new JLabel("Album:"));
            formPanel.add(txtAlbum);
            formPanel.add(new JLabel("Genre:"));
            formPanel.add(txtGenre);
            formPanel.add(new JLabel("Waktu (detik):"));
            formPanel.add(txtWaktu);
    
            int result = JOptionPane.showConfirmDialog(this, formPanel, "Tambah Lagu", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    judul = txtJudul.getText();
                    artis = txtArtis.getText();
                    album = txtAlbum.getText();
                    genre = txtGenre.getText();
                    waktu = Integer.parseInt(txtWaktu.getText());
    
                    Lagu lagu = new Lagu(0, judul, waktu, artis, album, genre);
                    if (laguManager.create(lagu)) {
                        JOptionPane.showMessageDialog(this, "Lagu berhasil ditambahkan.");
                        validInput = true; 
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal menambahkan lagu.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Waktu harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                    
                }
            } else {
                
                break;
            }
        }
    }

    private void lihatLagu() {
        String[] columns = {"ID", "Judul", "Artis", "Album", "Genre", "Waktu"};
        List<Lagu> laguList = laguManager.readAll();
        Object[][] data = new Object[laguList.size()][6];
        

        for (int i = 0; i < laguList.size(); i++) {
            Lagu lagu = laguList.get(i);
            data[i][0] = lagu.getId();
            data[i][1] = lagu.getJudul();
            data[i][2] = lagu.getArtis();
            data[i][3] = lagu.getAlbum();
            data[i][4] = lagu.getGenre();
            data[i][5] = lagu.getWaktu();
        }


        tableModel = new DefaultTableModel(data, columns);

        JTable table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        
        scrollPane.setBackground(Color.darkGray);

        getContentPane().removeAll();
        setLayout(new BorderLayout());

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setForeground(Color.magenta);

        JButton btnBack = new JButton("Kembali");
        btnBack.setBackground(Color.darkGray);
        btnBack.setForeground(Color.magenta);

        JButton btnAddToPlaylist = new JButton("Tambah ke Playlist");
        btnAddToPlaylist.setBackground(Color.darkGray);
        btnAddToPlaylist.setForeground(Color.magenta);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.setBackground(Color.darkGray);
        btnDelete.setForeground(Color.magenta);

        JButton btnEdit = new JButton("Edit");
        btnEdit.setBackground(Color.darkGray);
        btnEdit.setForeground(Color.magenta);


        buttonPanel.add(btnBack);
        buttonPanel.add(btnAddToPlaylist);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);

        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();


        btnBack.addActionListener(e -> menuAdmin());
        btnAddToPlaylist.addActionListener(e -> tambahLaguKePlaylist(table));
        btnDelete.addActionListener(e -> hapusLagu(table));
        btnEdit.addActionListener(e -> editLagu(table));
    }

    private void hapusLagu(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            if (laguManager.delete(id)) {
                JOptionPane.showMessageDialog(this, "Lagu berhasil dihapus.");
                lihatLagu();
            } else {
                JOptionPane.showMessageDialog(this, "Lagu tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih lagu terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editLagu(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            Lagu laguLama = laguManager.read(id);
            if (laguLama != null) {
                JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

                JTextField txtJudul = new JTextField(laguLama.getJudul());
                JTextField txtArtis = new JTextField(laguLama.getArtis());
                JTextField txtAlbum = new JTextField(laguLama.getAlbum());
                JTextField txtGenre = new JTextField(laguLama.getGenre());
                JTextField txtWaktu = new JTextField(String.valueOf(laguLama.getWaktu()));

                formPanel.add(new JLabel("Judul:"));
                formPanel.add(txtJudul);
                formPanel.add(new JLabel("Artis:"));
                formPanel.add(txtArtis);
                formPanel.add(new JLabel("Album:"));
                formPanel.add(txtAlbum);
                formPanel.add(new JLabel("Genre:"));
                formPanel.add(txtGenre);
                formPanel.add(new JLabel("Waktu (detik):"));
                formPanel.add(txtWaktu);

                int result = JOptionPane.showConfirmDialog(this, formPanel, "Edit Lagu", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    String judul = txtJudul.getText();
                    String artis = txtArtis.getText();
                    String album = txtAlbum.getText();
                    String genre = txtGenre.getText();
                    int waktu = Integer.parseInt(txtWaktu.getText());

                    Lagu laguBaru = new Lagu(id, judul, waktu, artis, album, genre);
                    if (laguManager.update(id, laguBaru)) {
                        JOptionPane.showMessageDialog(this, "Lagu berhasil diperbarui.");
                        lihatLagu();
                    } else {
                        JOptionPane.showMessageDialog(this, "Gagal memperbarui lagu.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Lagu tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih lagu terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tambahLaguKePlaylist(JTable table) {
        if (daftarPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada playlist.");
            return;
        }

        String[] playlistNames = new String[daftarPlaylist.size()];
        for (int i = 0; i < daftarPlaylist.size(); i++) {
            playlistNames[i] = daftarPlaylist.get(i).getNama();
        }

        String playlistName = (String) JOptionPane.showInputDialog(this, "Pilih Playlist:", "Tambah ke Playlist",
                JOptionPane.QUESTION_MESSAGE, null, playlistNames, playlistNames[0]);

        if (playlistName != null) {
            Playlist selectedPlaylist = null;
            for (Playlist p : daftarPlaylist) {
                if (p.getNama().equals(playlistName)) {
                    selectedPlaylist = p;
                    break;
                }
            }

            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int id = (int) table.getValueAt(selectedRow, 0);
                Lagu lagu = laguManager.read(id);
                selectedPlaylist.addLagu(lagu);
                JOptionPane.showMessageDialog(this, "Lagu berhasil ditambahkan ke playlist.");
            } else {
                JOptionPane.showMessageDialog(this, "Pilih lagu terlebih dahulu.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void buatPlaylist() {
        String namaPlaylist = JOptionPane.showInputDialog(this, "Masukkan Nama Playlist:");
        if (namaPlaylist != null && !namaPlaylist.trim().isEmpty()) {
            Playlist playlistBaru = new Playlist(namaPlaylist);
            daftarPlaylist.add(playlistBaru);
            JOptionPane.showMessageDialog(this, "Playlist \"" + namaPlaylist + "\" berhasil dibuat.");
        } else {
            JOptionPane.showMessageDialog(this, "Nama Playlist tidak boleh kosong.");
        }
    }

    private void lihatPlaylist(Playlist playlist) {
        String[] columns = {"ID", "Judul", "Artis", "Album", "Genre", "Waktu"};
        List<Lagu> laguList = playlist.getLaguList();
        Object[][] data = new Object[laguList.size()][6];
    
        for (int i = 0; i < laguList.size(); i++) {
            Lagu lagu = laguList.get(i);
            data[i][0] = lagu.getId();
            data[i][1] = lagu.getJudul();
            data[i][2] = lagu.getArtis();
            data[i][3] = lagu.getAlbum();
            data[i][4] = lagu.getGenre();
            data[i][5] = lagu.getWaktu();
        }
    
        tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);
    
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.darkGray);
        buttonPanel.setForeground(Color.magenta);
        JButton btnBack = new JButton("Kembali");
        btnBack.setBackground(Color.darkGray);
        btnBack.setForeground(Color.magenta);

    
        buttonPanel.add(btnBack);
    
        add(buttonPanel, BorderLayout.SOUTH);
    
        revalidate();
        repaint();
    
        btnBack.addActionListener(e -> menuPengguna());
    }


    private void lihatSemuaPlaylist() {
        if (daftarPlaylist.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Belum ada playlist.");
            return;
        }
    
        String[] playlistNames = new String[daftarPlaylist.size()];
        for (int i = 0; i < daftarPlaylist.size(); i++) {
            playlistNames[i] = daftarPlaylist.get(i).getNama();
        }
    
        String playlistName = (String) JOptionPane.showInputDialog(this, "Pilih Playlist:", "Lihat Playlist",
                JOptionPane.QUESTION_MESSAGE, null, playlistNames, playlistNames[0]);
    
        if (playlistName == null) {
            return;
        }
    
        Playlist selectedPlaylist = null;
        for (Playlist p : daftarPlaylist) {
            if (p.getNama().equals(playlistName)) {
                selectedPlaylist = p;
                break;
            }
        }
    
        if (selectedPlaylist != null) {
            lihatPlaylist(selectedPlaylist);
        }
    }

    public static void main(String[] args) {
        new AplikasiMusik();
    }
}
