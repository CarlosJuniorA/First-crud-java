package CRUDZ;

import java.sql.*;

public class UsuarioCRUD {
    private static final String URL = "jdbc:mysql://localhost:3306/exemplo_cruud";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Creat user
    public void criarUsuario(String nome, String email) {
        String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            System.out.println("Usuário criado com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // user reading
    public void lerUsuarios() {
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Uptade user
    public void atualizarUsuario(int id, String nome, String email) {
        String sql = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setString(2, email);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("User updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // delete user
    public void deletarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("User deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UsuarioCRUD crud = new UsuarioCRUD();

        // create user
        crud.criarUsuario("João Silva", "joao@coconut.com");

        // reader user
        crud.lerUsuarios();

        // update user assuming his id
        crud.atualizarUsuario(1, "João Silva Atualizado", "joao_atualizado@coconut.com");

        // delete user assuming his id
        crud.deletarUsuario(1);
    }
}
