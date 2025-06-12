package dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import model.ClienteOtaku;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClienteDAOtest {

    private static ClienteDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new ClienteDAO();
    }

    // Variable para guardar ID generado durante pruebas
    private static int clienteId;

    @Test
    @Order(1)
    public void testAgregarCliente() {
        ClienteOtaku cliente = new ClienteOtaku(0, "Test User", "testuser@example.com", "555-1234", "2025-06-10");
        dao.agregarCliente(cliente);

        ClienteOtaku clienteBuscado = dao.buscarPorEmail("testuser@example.com");
        assertNotNull(clienteBuscado, "El cliente debería existir después de agregarlo");
        assertEquals("Test User", clienteBuscado.getNombre());

        // Guardar id para siguientes pruebas
        clienteId = clienteBuscado.getId();
    }

    @Test
    @Order(2)
    public void testObtenerClientePorId() {
        ClienteOtaku cliente = dao.obtenerClientePorId(clienteId);
        assertNotNull(cliente);
        assertEquals("Test User", cliente.getNombre());
    }

    @Test
    @Order(3)
    public void testActualizarCliente() {
        ClienteOtaku cliente = dao.obtenerClientePorId(clienteId);
        cliente.setNombre("Usuario Actualizado");
        boolean actualizado = dao.actualizarCliente(cliente);

        assertTrue(actualizado, "La actualización debería ser exitosa");

        ClienteOtaku actualizadoCliente = dao.obtenerClientePorId(clienteId);
        assertEquals("Usuario Actualizado", actualizadoCliente.getNombre());
    }

    @Test
    @Order(4)
    public void testObtenerTodosLosClientes() {
        List<ClienteOtaku> clientes = dao.obtenerTodosLosClientes();
        assertFalse(clientes.isEmpty(), "Debería haber al menos un cliente en la lista");
    }

    @Test
    @Order(5)
    public void testEliminarCliente() {
        boolean eliminado = dao.eliminarCliente(clienteId);
        assertTrue(eliminado, "El cliente debería eliminarse correctamente");

        ClienteOtaku cliente = dao.obtenerClientePorId(clienteId);
        assertNull(cliente, "El cliente no debería existir tras eliminarlo");
    }
}
