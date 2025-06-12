package view;

import model.ProductoOtaku;
import model.ClienteOtaku;
import java.util.List;
import java.util.Scanner;

public class InterfazConsola {
    private Scanner scanner = new Scanner(System.in);

    //MENÚ PRINCIPAL
    public int mostrarMenu() {
        System.out.println("\n===== Menú Principal =====");
        System.out.println("1. Añadir nuevo producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Ver todos los productos");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Generar descripción con IA");
        System.out.println("7. Sugerir categoría con IA");
        System.out.println("8. Gestión de clientes");
        System.out.println("9. Salir");
        System.out.print("Elige una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //MENÚ CLIENTES
    public int mostrarMenuClientes() {
        System.out.println("\n===== Menú Gestión de Clientes =====");
        System.out.println("1. Añadir nuevo cliente");
        System.out.println("2. Consultar cliente por ID");
        System.out.println("3. Ver todos los clientes registrados");
        System.out.println("4. Actualizar datos de un cliente");
        System.out.println("5. Eliminar un cliente del sistema");
        System.out.println("6. Volver al menú principal");
        System.out.print("Elige una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    //MÉTODOS PARA PRODUCTOS
    public ProductoOtaku pedirDatosProducto() {
        System.out.println("=== Introduce los datos del producto ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();
        double precio = pedirDouble("Precio: ");
        int stock = pedirEntero("Stock: ");
        return new ProductoOtaku(nombre, categoria, precio, stock);
    }

    public ProductoOtaku pedirDatosProductoConId() {
        System.out.println("=== Actualización de Producto ===");
        int id = pedirEntero("ID del producto: ");
        ProductoOtaku producto = pedirDatosProducto();
        producto.setId(id);
        return producto;
    }
    
    public String pedirNombreProducto() {
        System.out.print("Introduce el nombre del nuevo producto: ");
        return scanner.nextLine();
    }


    public int pedirIdProducto() {
        return pedirEntero("Introduce el ID del producto: ");
    }

    public void mostrarProducto(ProductoOtaku producto) {
        if (producto != null) {
            System.out.println(producto);
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    public void mostrarListaProductos(List<ProductoOtaku> productos) {
        System.out.println("=== Productos Actuales ===");
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            for (ProductoOtaku p : productos) {
                System.out.println(p);
            }
        }
    }

    //MÉTODOS PARA CLIENTES

    public ClienteOtaku pedirDatosCliente() {
        System.out.println("=== Introduce los datos del cliente ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Fecha de registro (YYYY-MM-DD): ");
        String fechaRegistro = scanner.nextLine();
        return new ClienteOtaku(0, nombre, email, telefono, fechaRegistro); // id=0 para nuevo
    }

    public ClienteOtaku pedirDatosClienteConId() {
        System.out.println("=== Actualización de Cliente ===");
        int id = pedirEntero("ID del cliente: ");
        ClienteOtaku cliente = pedirDatosCliente();
        cliente.setId(id);
        return cliente;
    }

    public int pedirIdCliente() {
        return pedirEntero("Introduce el ID del cliente: ");
    }

    public void mostrarCliente(ClienteOtaku cliente) {
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    public void mostrarListaClientes(List<ClienteOtaku> clientes) {
        System.out.println("=== Clientes Registrados ===");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            for (ClienteOtaku c : clientes) {
                System.out.println(c);
            }
        }
    }

    //MÉTODOS
    private int pedirEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente con un número entero.");
            }
        }
    }

    private double pedirDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Intente con un número decimal.");
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarRespuestaIA(String respuesta) {
        System.out.println("Respuesta de la IA:\n" + respuesta);
    }
}
