package util;

import dao.ProductoDAO;
import model.ProductoOtaku;

// Clase para iniciar la base de datos con productos de ejemplo
public class SetupDatos {
    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        // Borrar todos los productos antes de insertar
        dao.borrarTodosLosProductos();

        // Insertar los productos de ejemplo
        dao.agregarProducto(new ProductoOtaku("Camiseta Nankatsu FC - Oliver Atom", "Ropa", 24.99, 10));
        dao.agregarProducto(new ProductoOtaku("Balón edición especial Captain Tsubasa", "Accesorio", 34.50, 5));
        dao.agregarProducto(new ProductoOtaku("Figura Tsubasa Ozora", "Figura", 45.00, 7));
        dao.agregarProducto(new ProductoOtaku("Manga Captain Tsubasa Vol.1", "Manga", 8.99, 12));
        dao.agregarProducto(new ProductoOtaku("Póster Captain Tsubasa vs Hyuga", "Póster", 12.00, 20));

        System.out.println("Base de datos reiniciada e insertados productos de Captain Tsubasa.");
    }
}
