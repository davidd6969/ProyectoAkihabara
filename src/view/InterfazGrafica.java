package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ProductoDAO;
import dao.ClienteDAO;
import model.ProductoOtaku;
import model.ClienteOtaku;
import service.LlmService;
import java.awt.Color;
import java.awt.Font;

public class InterfazGrafica {

	private JFrame frame;
	private JTable table;
	private JTable tableClientes;
	private ProductoDAO productoDAO = new ProductoDAO();
	private ClienteDAO clienteDAO = new ClienteDAO();
	private LlmService llmService = new LlmService();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				InterfazGrafica window = new InterfazGrafica();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public InterfazGrafica() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Akihabara Market");
		frame.setBounds(100, 100, 1281, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		//Botones de productos
		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(Color.GRAY);
		frame.getContentPane().add(panelProductos, BorderLayout.NORTH);

		JButton btnMostrar = new JButton("Mostrar Productos");
		btnMostrar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnMostrar.setBackground(Color.LIGHT_GRAY);
		btnMostrar.addActionListener(e -> cargarProductosEnTabla());
		panelProductos.add(btnMostrar);

		JButton btnBuscar = new JButton("Buscar Producto");
		btnBuscar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.addActionListener(e -> buscarProducto());
		panelProductos.add(btnBuscar);

		JButton btnAgregar = new JButton("Agregar Producto");
		btnAgregar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAgregar.setBackground(Color.LIGHT_GRAY);
		btnAgregar.addActionListener(e -> agregarProducto());
		panelProductos.add(btnAgregar);

		JButton btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnEliminar.setBackground(Color.LIGHT_GRAY);
		btnEliminar.addActionListener(e -> eliminarProducto());
		panelProductos.add(btnEliminar);

		JButton btnActualizar = new JButton("Actualizar Producto");
		btnActualizar.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnActualizar.setBackground(Color.LIGHT_GRAY);
		btnActualizar.addActionListener(e -> actualizarProducto());
		panelProductos.add(btnActualizar);

		JButton btnGenerarDescripcion = new JButton("Descripción IA");
		btnGenerarDescripcion.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnGenerarDescripcion.setBackground(Color.LIGHT_GRAY);
		btnGenerarDescripcion.addActionListener(e -> generarDescripcionIA());
		panelProductos.add(btnGenerarDescripcion);

		JButton btnSugerirCategoria = new JButton("Categoría IA");
		btnSugerirCategoria.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnSugerirCategoria.setBackground(Color.LIGHT_GRAY);
		btnSugerirCategoria.addActionListener(e -> sugerirCategoriaIA());
		panelProductos.add(btnSugerirCategoria);

		// Panel central con dos tablas
		JPanel panelCentral = new JPanel(new BorderLayout());
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);

		// Tabla de productos
		JScrollPane scrollProductos = new JScrollPane();
		table = new JTable();
		table.setBackground(Color.LIGHT_GRAY);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Nombre", "Categoría", "Precio", "Stock" }
		));
		scrollProductos.setViewportView(table);
		panelCentral.add(scrollProductos, BorderLayout.CENTER);

		// Tabla de clientes
		JScrollPane scrollClientes = new JScrollPane();
		tableClientes = new JTable();
		tableClientes.setBackground(Color.LIGHT_GRAY);
		tableClientes.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] { "ID", "Nombre", "Email", "Teléfono", "Fecha Registro" }
		));
		scrollClientes.setViewportView(tableClientes);
		panelCentral.add(scrollClientes, BorderLayout.SOUTH);

		//Botones para los clientes
		JPanel panelClientes = new JPanel();
		panelClientes.setBackground(Color.GRAY);
		frame.getContentPane().add(panelClientes, BorderLayout.SOUTH);

		JButton btnAnadirCliete = new JButton("Añadir Cliente");
		btnAnadirCliete.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnAnadirCliete.setBackground(Color.LIGHT_GRAY);
		btnAnadirCliete.addActionListener(e -> agregarCliente());
		panelClientes.add(btnAnadirCliete);

		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnBuscarCliente.setBackground(Color.LIGHT_GRAY);
		btnBuscarCliente.addActionListener(e -> buscarCliente());
		panelClientes.add(btnBuscarCliente);

		JButton btnMostrarClientes = new JButton("Mostrar Clientes");
		btnMostrarClientes.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnMostrarClientes.setBackground(Color.LIGHT_GRAY);
		btnMostrarClientes.addActionListener(e -> mostrarClientes());
		panelClientes.add(btnMostrarClientes);

		JButton btnActualizarCliente = new JButton("Actualizar Cliente");
		btnActualizarCliente.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnActualizarCliente.setBackground(Color.LIGHT_GRAY);
		btnActualizarCliente.addActionListener(e -> actualizarCliente());
		panelClientes.add(btnActualizarCliente);

		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 11));
		btnEliminarCliente.setBackground(Color.LIGHT_GRAY);
		btnEliminarCliente.addActionListener(e -> eliminarCliente());
		panelClientes.add(btnEliminarCliente);
	}

	//Métodos para productos

	private void cargarProductosEnTabla() {
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		modelo.setRowCount(0);
		List<ProductoOtaku> productos = productoDAO.obtenerTodosLosProductos();
		for (ProductoOtaku p : productos) {
			modelo.addRow(new Object[] {
					p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getStock()
			});
		}
	}

	private void buscarProducto() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
			ProductoOtaku producto = productoDAO.obtenerProductoPorId(id);
			if (producto != null) {
				JOptionPane.showMessageDialog(frame, producto.toString());
			} else {
				JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void agregarProducto() {
		try {
			String nombre = JOptionPane.showInputDialog("Nombre:");
			String categoria = JOptionPane.showInputDialog("Categoría:");
			double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
			int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));
			ProductoOtaku nuevo = new ProductoOtaku(nombre, categoria, precio, stock);
			productoDAO.agregarProducto(nuevo);
			cargarProductosEnTabla();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void eliminarProducto() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
			boolean eliminado = productoDAO.eliminarProducto(id);
			if (eliminado) {
				cargarProductosEnTabla();
				JOptionPane.showMessageDialog(frame, "Producto eliminado.");
			} else {
				JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void actualizarProducto() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a actualizar:"));
			String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
			String categoria = JOptionPane.showInputDialog("Nueva categoría:");
			double precio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:"));
			int stock = Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock:"));
			ProductoOtaku actualizado = new ProductoOtaku(nombre, categoria, precio, stock);
			boolean exito = productoDAO.actualizarProducto(actualizado);
			if (exito) {
				cargarProductosEnTabla();
				JOptionPane.showMessageDialog(frame, "Producto actualizado.");
			} else {
				JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void generarDescripcionIA() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
			ProductoOtaku producto = productoDAO.obtenerProductoPorId(id);
			if (producto != null) {
				String prompt = "Genera una descripción breve y atractiva del producto en español y dividelo en varias lineas: " +
						producto.getNombre() + " de la categoría " + producto.getCategoria();
				String descripcion = llmService.consultarLLM(prompt);
				JOptionPane.showMessageDialog(frame, descripcion);
			} else {
				JOptionPane.showMessageDialog(frame, "Producto no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al generar descripción.");
		}
	}

	private void sugerirCategoriaIA() {
		try {
			String nombre = JOptionPane.showInputDialog("Nombre del producto:");
			String prompt = "Para un producto otaku llamado '" + nombre +
					"', sugiere una categoría de esta lista. Solo en una palabra: Figura, Manga, Póster, Llavero, Ropa, Videojuego, Otro.";
			String categoria = llmService.consultarLLM(prompt);
			JOptionPane.showMessageDialog(frame, "Categoría sugerida: " + categoria);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error al sugerir categoría.");
		}
	}

	//Métodos para clientes

	private void mostrarClientes() {
		DefaultTableModel modelo = (DefaultTableModel) tableClientes.getModel();
		modelo.setRowCount(0);
		List<ClienteOtaku> clientes = clienteDAO.obtenerTodosLosClientes();
		for (ClienteOtaku c : clientes) {
			modelo.addRow(new Object[] {
					c.getId(), c.getNombre(), c.getEmail(), c.getTelefono(), c.getFechaRegistro()
			});
		}
	}

	private void buscarCliente() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente:"));
			ClienteOtaku cliente = clienteDAO.obtenerClientePorId(id);
			if (cliente != null) {
				JOptionPane.showMessageDialog(frame, cliente.toString());
			} else {
				JOptionPane.showMessageDialog(frame, "Cliente no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void agregarCliente() {
		try {
			String nombre = JOptionPane.showInputDialog("Nombre:");
			String email = JOptionPane.showInputDialog("Email:");
			String telefono = JOptionPane.showInputDialog("Teléfono:");
			String fecha = JOptionPane.showInputDialog("Fecha de registro (YYYY-MM-DD):");
			ClienteOtaku cliente = new ClienteOtaku(0, nombre, email, telefono, fecha);
			clienteDAO.agregarCliente(cliente);
			mostrarClientes();
			JOptionPane.showMessageDialog(frame, "Cliente agregado.");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void actualizarCliente() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente a actualizar:"));
			String nombre = JOptionPane.showInputDialog("Nuevo nombre:");
			String email = JOptionPane.showInputDialog("Nuevo email:");
			String telefono = JOptionPane.showInputDialog("Nuevo teléfono:");
			String fecha = JOptionPane.showInputDialog("Nueva fecha de registro (YYYY-MM-DD):");
			ClienteOtaku actualizado = new ClienteOtaku(id, nombre, email, telefono, fecha);
			boolean exito = clienteDAO.actualizarCliente(actualizado);
			if (exito) {
				mostrarClientes();
				JOptionPane.showMessageDialog(frame, "Cliente actualizado.");
			} else {
				JOptionPane.showMessageDialog(frame, "Cliente no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}

	private void eliminarCliente() {
		try {
			int id = Integer.parseInt(JOptionPane.showInputDialog("ID del cliente a eliminar:"));
			boolean eliminado = clienteDAO.eliminarCliente(id);
			if (eliminado) {
				mostrarClientes();
				JOptionPane.showMessageDialog(frame, "Cliente eliminado.");
			} else {
				JOptionPane.showMessageDialog(frame, "Cliente no encontrado.");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage());
		}
	}
}
