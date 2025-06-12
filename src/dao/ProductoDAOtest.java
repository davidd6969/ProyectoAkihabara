package dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import model.ProductoOtaku;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductoDAOtest {

    private static ProductoDAO dao;

    @BeforeAll
    public static void setup() {
        dao = new ProductoDAO();
        dao.borrarTodosLosProductos(); // Limpiar tabla antes de pruebas
    }

    private static int productoId;

    @Test
    @Order(1)
    public void testAgregarProducto() {
        ProductoOtaku producto = new ProductoOtaku("Figura Goku", "Figuras", 59.99, 10);
        dao.agregarProducto(producto);

        // Obtener todos y verificar que uno existe
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        assertFalse(productos.isEmpty(), "La lista no debería estar vacía después de agregar");

        // Guardar ID para usar luego
        productoId = productos.get(0).getId();
        assertEquals("Figura Goku", productos.get(0).getNombre());
    }

    @Test
    @Order(2)
    public void testObtenerProductoPorId() {
        ProductoOtaku p = dao.obtenerProductoPorId(productoId);
        assertNotNull(p, "Producto no debería ser null");
        assertEquals("Figura Goku", p.getNombre());
    }

    @Test
    @Order(3)
    public void testActualizarProducto() {
        ProductoOtaku producto = dao.obtenerProductoPorId(productoId);
        producto.setPrecio(49.99);
        producto.setStock(15);
        boolean actualizado = dao.actualizarProducto(producto);

        assertTrue(actualizado, "Debería actualizar correctamente");

        ProductoOtaku actualizado1 = dao.obtenerProductoPorId(productoId);
        assertEquals(49.99, actualizado1.getPrecio());
        assertEquals(15, actualizado1.getStock());
    }

    @Test
    @Order(4)
    public void testObtenerTodosLosProductos() {
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        assertTrue(productos.size() > 0, "Debe haber al menos un producto");
    }

    @Test
    @Order(5)
    public void testEliminarProducto() {
        boolean eliminado = dao.eliminarProducto(productoId);
        assertTrue(eliminado, "Debería eliminar el producto");

        ProductoOtaku p = dao.obtenerProductoPorId(productoId);
        assertNull(p, "Producto debería ser null luego de eliminar");
    }
}
