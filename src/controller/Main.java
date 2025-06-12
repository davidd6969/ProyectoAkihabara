package controller;

import dao.ProductoDAO;
import dao.ClienteDAO;
import model.ProductoOtaku;
import model.ClienteOtaku;
import service.LlmService;
import view.InterfazConsola;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        InterfazConsola consola = new InterfazConsola();
        ProductoDAO productoDAO = new ProductoDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        LlmService llmService = new LlmService();

        boolean salir = false;

        while (!salir) {
            int opcion = consola.mostrarMenu();

            switch (opcion) {
                case 1: // Añadir un producto
                    try {
                        ProductoOtaku nuevoProducto = consola.pedirDatosProducto();
                        productoDAO.agregarProducto(nuevoProducto);
                        consola.mostrarMensaje("Producto añadido correctamente.");
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al añadir el producto: " + e.getMessage());
                    }
                    break;

                case 2: // Buscar producto por Id
                    try {
                        int id = consola.pedirIdProducto();
                        ProductoOtaku producto = productoDAO.obtenerProductoPorId(id);
                        consola.mostrarProducto(producto);
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al buscar el producto.");
                    }
                    break;

                case 3: // Ver todos los productos
                    List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();
                    consola.mostrarListaProductos(productos);
                    break;

                case 4: // Actualizar producto
                    try {
                        ProductoOtaku actualizado = consola.pedirDatosProductoConId();
                        boolean exito = productoDAO.actualizarProducto(actualizado);
                        if (exito) {
                            consola.mostrarMensaje("Producto actualizado.");
                        } else {
                            consola.mostrarMensaje("No se encontró un producto con ese ID.");
                        }
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al actualizar: " + e.getMessage());
                    }
                    break;

                case 5: // Eliminar producto
                    try {
                        int idEliminar = consola.pedirIdProducto();
                        boolean eliminado = productoDAO.eliminarProducto(idEliminar);
                        if (eliminado) {
                            consola.mostrarMensaje("Producto eliminado.");
                        } else {
                            consola.mostrarMensaje("Producto no encontrado.");
                        }
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al eliminar el producto.");
                    }
                    break;

                case 6: // Generar descripción con IA
                    try {
                        int id = consola.pedirIdProducto();
                        ProductoOtaku producto = productoDAO.obtenerProductoPorId(id);
                        if (producto != null) {
                            String prompt = "Genera una descripción de marketing breve y atractiva para el producto otaku en idioma castellano, dividido en varias lineas: "
                                    + producto.getNombre() + " de la categoría " + producto.getCategoria() + ".";
                            String respuesta = llmService.consultarLLM(prompt);
                            consola.mostrarRespuestaIA(respuesta);
                        } else {
                            consola.mostrarMensaje("Producto no encontrado.");
                        }
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al generar descripción con IA.");
                    }
                    break;

                case 7: // Sugerir categoría IA
                    try {
                        String nombreProducto = consola.pedirNombreProducto();
                        String prompt = "Para un producto otaku llamado '" + nombreProducto
                                + "', sugiere una categoría adecuada de esta lista: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.";
                        String sugerencia = llmService.consultarLLM(prompt);
                        consola.mostrarRespuestaIA(sugerencia);
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al sugerir categoría.");
                    }
                    break;

                case 8: // Gestión de clientes
                    gestionarClientes(consola, clienteDAO);
                    break;

                case 9: // Salir
                    consola.mostrarMensaje("Saliendo...");
                    salir = true;
                    break;

                default:
                    consola.mostrarMensaje("Opción no válida. Intenta nuevamente.");
                    break;
            } 
        }
    }

    private static void gestionarClientes(InterfazConsola consola, ClienteDAO clienteDAO) {
        boolean salirClientes = false;

        while (!salirClientes) {
            int opcion = consola.mostrarMenuClientes();

            switch (opcion) {
                case 1: // Añadir nuevo cliente
                    try {
                        ClienteOtaku nuevoCliente = consola.pedirDatosCliente();
                        clienteDAO.agregarCliente(nuevoCliente);
                        consola.mostrarMensaje("Cliente agregado con éxito.");
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al agregar cliente: " + e.getMessage());
                    }
                    break;

                case 2: // Buscar cliente por ID
                    try {
                        int id = consola.pedirIdCliente();
                        ClienteOtaku cliente = clienteDAO.obtenerClientePorId(id);
                        consola.mostrarCliente(cliente);
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al buscar el cliente.");
                    }
                    break;

                case 3: // Ver todos los clientes registrados
                    try {
                        List<ClienteOtaku> clientes = clienteDAO.obtenerTodosLosClientes();
                        consola.mostrarListaClientes(clientes);
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al obtener la lista de clientes.");
                    }
                    break;

                case 4: // Actualizar datos de un cliente
                    try {
                        ClienteOtaku clienteActualizar = consola.pedirDatosClienteConId();
                        boolean actualizado = clienteDAO.actualizarCliente(clienteActualizar);
                        if (actualizado) {
                            consola.mostrarMensaje("Cliente actualizado correctamente.");
                        } else {
                            consola.mostrarMensaje("No se encontró un cliente con ese ID.");
                        }
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al actualizar el cliente: " + e.getMessage());
                    }
                    break;

                case 5: // Eliminar un cliente del sistema
                    try {
                        int idEliminar = consola.pedirIdCliente();
                        boolean eliminado = clienteDAO.eliminarCliente(idEliminar);
                        if (eliminado) {
                            consola.mostrarMensaje("Cliente eliminado correctamente.");
                        } else {
                            consola.mostrarMensaje("No se encontró un cliente con ese ID.");
                        }
                    } catch (Exception e) {
                        consola.mostrarMensaje("Error al eliminar el cliente.");
                    }
                    break;

                case 6: // Volver al menú principal
                    salirClientes = true;
                    break;

                default:
                    consola.mostrarMensaje("Opción inválida, intenta nuevamente.");
                    break;
            }
        }
    }
}
