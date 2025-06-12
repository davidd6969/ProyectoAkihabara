package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ClienteOtaku;

public class ClienteDAO {

    // Método para agregar un nuevo cliente a la base de datos
    public void agregarCliente(ClienteOtaku cliente) {
        String sql = "INSERT INTO clientes (nombre, email, telefono, fecha_registro) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Se asignan los valores del cliente a los parámetros de la consulta
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setDate(4, Date.valueOf(cliente.getFechaRegistro())); // Convierte la fecha a tipo Date

            pstmt.executeUpdate(); // Ejecuta la inserción
        } catch (SQLException e) {
            e.printStackTrace(); // Muestra el error si ocurre
        }
    }

    // Método para obtener un cliente por su ID
    public ClienteOtaku obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        ClienteOtaku cliente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asigna el ID como parámetro
            ResultSet rs = pstmt.executeQuery(); // Ejecuta la consulta

            if (rs.next()) {
                // Si hay resultados, crea un objeto ClienteOtaku con los datos obtenidos
                cliente = new ClienteOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_registro").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    // Método para obtener todos los clientes registrados
    public List<ClienteOtaku> obtenerTodosLosClientes() {
        String sql = "SELECT * FROM clientes";
        List<ClienteOtaku> listaClientes = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ClienteOtaku cliente = new ClienteOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_registro").toString()
                );
                listaClientes.add(cliente); // Agrega el cliente a la lista
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaClientes; 
    }

    // Método para actualizar la información de un cliente
    public boolean actualizarCliente(ClienteOtaku cliente) {
        String sql = "UPDATE clientes SET nombre = ?, email = ?, telefono = ?, fecha_registro = ? WHERE id = ?";
        boolean actualizado = false;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Asigna los valores actualizados al PreparedStatement
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setDate(4, Date.valueOf(cliente.getFechaRegistro()));
            pstmt.setInt(5, cliente.getId());

            int filas = pstmt.executeUpdate(); // Ejecuta la actualización
            actualizado = filas > 0; // Verifica si se modificó alguna fila

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actualizado; // Devuelve true si se actualizó correctamente
    }

    // Método para eliminar un cliente por su ID
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        boolean eliminado = false;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id); // Asigna el ID del cliente a eliminar

            int filas = pstmt.executeUpdate(); // Ejecuta la eliminación
            eliminado = filas > 0; // Verifica si se eliminó alguna fila

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eliminado; // Devuelve true si se eliminó correctamente
    }

    // Método opcional para buscar un cliente por su email
    public ClienteOtaku buscarPorEmail(String email) {
        String sql = "SELECT * FROM clientes WHERE email = ?";
        ClienteOtaku cliente = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email); // Asigna el email como parámetro
            ResultSet rs = pstmt.executeQuery(); // Ejecuta la consulta

            if (rs.next()) {
                // Si encuentra un cliente, lo crea con los datos obtenidos
                cliente = new ClienteOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getDate("fecha_registro").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente; // Retorna el cliente encontrado o null si no existe
    }
}
