import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AplikasiMusik extends JFrame {
    private static LaguManager laguManager = new LaguManager();
    private DefaultTableModel tableModel;

    public AplikasiMusik() {
        setTitle("Aplikasi Musik");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel welcomeLabel = new JLabel("MP3 Rasa Spotify", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Georgia", Font.BOLD, 18));
        loginPanel.add(welcomeLabel);

        JButton btnAdmin = new JButton("Login sebagai Admin");
        JButton btnUser = new JButton("Login sebagai Pengguna");

        loginPanel.add(btnAdmin);
        loginPanel.add(btnUser);

        add(loginPanel, BorderLayout.CENTER);

        // Button Actions
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
        // Clear current panel and create new menu
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // Admin Menu Panel
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton btnAdd = new JButton("Tambah Lagu");
        JButton btnView = new JButton("Lihat Daftar Lagu");
        JButton btnExit = new JButton("Keluar");

        menuPanel.add(btnAdd);
        menuPanel.add(btnView);

        menuPanel.add(btnExit);

        add(menuPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Button Actions
        btnAdd.addActionListener(e -> tambahLagu());
        btnView.addActionListener(e -> lihatLagu());
       // btnDelete.addActionListener(e -> hapusLagu());
        //btnEdit.addActionListener(e -> editLagu());
        btnExit.addActionListener(e -> kembaliKeLogin());
    }

    private void menuPengguna() {
        // Clear current panel and create new menu
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // Pengguna Menu Panel
        JPanel penggunaPanel = new JPanel();
        penggunaPanel.setLayout(new GridLayout(2, 1, 10, 10));

        JButton btnView = new JButton("Lihat Daftar Lagu");
        JButton btnExit = new JButton("Keluar");

        penggunaPanel.add(btnView);
        penggunaPanel.add(btnExit);

        add(penggunaPanel, BorderLayout.CENTER);
        revalidate();
        repaint();

        // Button Actions
        btnView.addActionListener(e -> lihatLagu());
        btnExit.addActionListener(e -> kembaliKeLogin());
    }

    private void kembaliKeLogin() {
        // Clear the current panel and show the login screen again
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        // Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 1, 10, 10));
        JLabel welcomeLabel = new JLabel("Selamat datang di Aplikasi Musik", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(welcomeLabel);

        JButton btnAdmin = new JButton("Login sebagai Admin");
        JButton btnUser = new JButton("Login sebagai Pengguna");

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
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JTextField txtJudul = new JTextField();
        JTextField txtArtis = new JTextField();
        JTextField txtAlbum = new JTextField();
        JTextField txtGenre = new JTextField();
        JTextField txtWaktu = new JTextField();

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
                String judul = txtJudul.getText();
                String artis = txtArtis.getText();
                String album = txtAlbum.getText();
                String genre = txtGenre.getText();
                int waktu = Integer.parseInt(txtWaktu.getText());

                Lagu lagu = new Lagu(0, judul, waktu, artis, album, genre);
                if (laguManager.create(lagu)) {
                    JOptionPane.showMessageDialog(this, "Lagu berhasil ditambahkan.");
                } else {
                    JOptionPane.showMessageDialog(this, "Gagal menambahkan lagu.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Waktu harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void lihatLagu() {
        // Create a table model with column names and empty data
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

        // Create JTable
        tableModel = new DefaultTableModel(data, columns);
        JTable table = new JTable(tableModel);

        // Add scroll pane to the table
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().removeAll();
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // Add buttons
        JPanel buttonPanel = new JPanel();
        JButton btnBack = new JButton("Kembali");
        JButton btnDelete = new JButton("Hapus");
        JButton btnEdit = new JButton("Edit");

        buttonPanel.add(btnBack);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnEdit);

        add(buttonPanel, BorderLayout.SOUTH);

        revalidate();
        repaint();

        btnBack.addActionListener(e -> menuAdmin());
        btnDelete.addActionListener(e -> hapusLagu(table));
        btnEdit.addActionListener(e -> editLagu(table));
    }

    private void hapusLagu(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) table.getValueAt(selectedRow, 0);
            if (laguManager.delete(id)) {
                JOptionPane.showMessageDialog(this, "Lagu berhasil dihapus.");
                lihatLagu(); // Refresh table
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
                        lihatLagu(); // Refresh table
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

    public static void main(String[] args) {
        new AplikasiMusik();
    }
} 
