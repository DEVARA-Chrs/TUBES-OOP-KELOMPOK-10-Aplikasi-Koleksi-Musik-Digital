import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

class LaguManager {
    public boolean create(Lagu lagu) {
        String query = "INSERT INTO lagu (judul, artis, album, genre, waktu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lagu.getJudul());
            stmt.setString(2, lagu.getArtis());
            stmt.setString(3, lagu.getAlbum());
            stmt.setString(4, lagu.getGenre());
            stmt.setInt(5, lagu.getWaktu());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Lagu read(int id) {
        String query = "SELECT * FROM lagu WHERE id = ?";
        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Lagu(
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getInt("waktu"),
                    rs.getString("artis"),
                    rs.getString("album"),
                    rs.getString("genre")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Lagu> readAll() {
        List<Lagu> laguList = new ArrayList<>();
        String query = "SELECT * FROM lagu";
        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                laguList.add(new Lagu(
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getInt("waktu"),
                    rs.getString("artis"),
                    rs.getString("album"),
                    rs.getString("genre")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laguList;
    }

    public boolean update(int id, Lagu laguUpdate) {
        String query = "UPDATE lagu SET judul = ?, artis = ?, album = ?, genre = ?, waktu = ? WHERE id = ?";
        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, laguUpdate.getJudul());
            stmt.setString(2, laguUpdate.getArtis());
            stmt.setString(3, laguUpdate.getAlbum());
            stmt.setString(4, laguUpdate.getGenre());
            stmt.setInt(5, laguUpdate.getWaktu());
            stmt.setInt(6, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int id) {
        String query = "DELETE FROM lagu WHERE id = ?";
        try (Connection conn = Database.connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
