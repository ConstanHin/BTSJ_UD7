package TA07_3;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class tresApp {

	// Attributes

	static private boolean running = true; // Utilizado para terminar la ejecución
	static private Scanner scnr = new Scanner(System.in);
	static private Hashtable<String, Double> articulos = new Hashtable<String, Double>();
	static private Hashtable<String, Integer> cesta = new Hashtable<>();

	public static void main(String[] args) {
		// Crear base de datos de artículos con Hashtable
		articulos.put("patata", 1.25);
		articulos.put("zanahoria", 2.25);
		articulos.put("berenjena", 0.57);
		articulos.put("pimiento", 0.90);
		articulos.put("pan", 0.35);
		articulos.put("ajo", 0.32);
		articulos.put("jabon", 3.0);
		articulos.put("manzana", 1.99);
		articulos.put("tomate", 1.10);
		articulos.put("platano", 1.40);

		while (running) {
			// Opciones
			seleccionarOpcion(articulos);
		}

		// Mostrar nombre articulo
//		mostrarInfoArticulo(articulos);

		// Mostrar lista de articulos
//		consultarArticulos(articulos);

//		String cantidadPagadaString = JOptionPane.showInputDialog(null, "Cantidad pagada", "Cantidad pagada",
//				JOptionPane.PLAIN_MESSAGE);
//		double cantidadPagada = Double.parseDouble(cantidadPagadaString);
//		double cambio = cantidadPagada - totalConIva;
//		cambio = roundTwoDecimals(cambio);

//		listaProductos = formatTicketString(listaProductos, totalSinIva, totalConIva, cantidadPagada, cambio);

//		JOptionPane.showMessageDialog(null, listaProductos, "Ticket", JOptionPane.PLAIN_MESSAGE);
		scnr.close();
	}

	/**
	 * Formatear la lista de productos para mostrar
	 * 
	 * @param listaProductos
	 * @param totalSinIva
	 * @param totalConIva
	 * @param cantidadPagada
	 * @param cambio
	 * @return
	 */
//	private static String formatTicketString(String listaProductos, double totalSinIva, double totalConIva,
//			double cantidadPagada, double cambio) {
//		listaProductos += "------------------------------------------------- \n";
//		listaProductos += "Nr total productos:" + totalCantidadArticulos + "\n";
//		listaProductos += "Precio total sin IVA- " + totalSinIva + "€ \n";
//		listaProductos += "Precio total con IVA- " + totalConIva + "€ \n";
//		listaProductos += "------------------------------------------------- \n";
//		listaProductos += "Cantidad pagada: " + cantidadPagada + "€ \n";
//		listaProductos += "Cambio: " + cambio + "€ \n";
//		return listaProductos;
//	}

	/**
	 * Calcular el precio total con iva
	 * 
	 * @param precioSinIva
	 * @param ivaString
	 * @return
	 */
//	private static double calcularPrecioConIva(double precioSinIva, String ivaString) {
//		double iva = Double.parseDouble(ivaString);
//		double precioIva = precioSinIva * 21 / 100;
//		double totalConIva = precioSinIva + precioIva;
//		totalConIva = roundTwoDecimals(totalConIva);
//		return totalConIva;
//	}

	/**
	 * Redondear a dos decimales los numeros de tipo double
	 * 
	 * @param numero
	 * @return double
	 */
	public static double roundTwoDecimals(double numero) {
		return (double) Math.round(numero * 100) / 100;
	}

	/**
	 * Pedir input del nombre del articulo y mostrar sus detalles si existe
	 * 
	 * @param articulos
	 */
	public static void mostrarInfoArticulo(Hashtable<String, Double> articulos) {

		System.out.println("--------------------");
		System.out.println("Ver información del articulo");
		System.out.println("Introducir el nombre del articulo: ");

		// Input articulo
		String nombreArticulo = scnr.nextLine();
		nombreArticulo = nombreArticulo.toLowerCase();

		Double precioArticulo = articulos.get(nombreArticulo);

		// Output resultado
		if (precioArticulo != null) {
			System.out.println("Artículo:");
			System.out.print(nombreArticulo + " ");
			System.out.print(precioArticulo + "€");
		} else {
			System.out.println("No existe el articulo introducido");
		}

		System.out.println("--------------------");

	}

	/**
	 * Consultar toda la lista de articulos
	 * 
	 * @param articulos
	 */
	public static void mostrarInterfazArticulos(Hashtable<String, Double> articulos) {

		System.out.println("--------------------");
		System.out.println("Lista articulos: \n");

		Enumeration<String> enumerationKeys = articulos.keys();
		Enumeration<Double> enumerationElem = articulos.elements();

		while (enumerationElem.hasMoreElements()) {
			System.out.print(enumerationKeys.nextElement() + " ---- ");
			System.out.println(enumerationElem.nextElement() + "€");
		}

		System.out.println("--------------------");

		continuar();

	}

	/**
	 * Mostrar la interfaz para añadir un producto
	 */
	public static void mostrarInterfazAddProducto() {
		System.out.println("--------------------");
		// Introducir nombre articulo
		System.out.println("Introducir el nombre del producto:");
		String nombreArticulo = scnr.nextLine();

		// Comprobar nombre articulo
		Double articuloPrecio = articulos.get(nombreArticulo);

		if (articuloPrecio != null) {
			// Introducir cantidad
			System.out.println("Escriba la cantidad:");
			String cantidadString = scnr.nextLine();

			int cantidad = Integer.parseInt(cantidadString);

			addCesta(nombreArticulo, articuloPrecio, cantidad);

		} else {
			System.out.println("El producto no existe");
		}

	}

	/**
	 * Mostrar la interfaz para ver la cesta
	 */
	public static void mostrarInterfazCesta() {
		Enumeration<String> enumNombre = cesta.keys(); 
		Enumeration<Integer> enumCantidad = cesta.elements(); 
		
		System.out.println("--------------------");
		
		if (cesta.size() > 0) {
			
			System.out.println("Tu cesta: ");
			while(enumNombre.hasMoreElements()) {
				System.out.print(enumNombre.nextElement() + " -- ");
				System.out.println(enumCantidad.nextElement() + " unidades");
			}
			
		}else {
			System.out.println("Tu cesta está vacía.");
		}
		
		
		
		System.out.println("--------------------");
		continuar();
	}

	/**
	 * Añadir un producto a la cesta
	 * 
	 * @param nombre
	 * @param precio
	 * @param cantidad
	 */
	public static void addCesta(String nombre, Double precio, int cantidad) {
		cesta.put(nombre, cantidad);
	}

	/**
	 * Utilizado para hacer una pausa en la ejecución
	 */
	public static void continuar() {
		System.out.println("Pulsa ENTER para continuar");
		scnr.nextLine();
	}

	/**
	 * Termina el programa
	 */
	public static void terminarPrograma() {
		running = false;
	}

	public static void seleccionarOpcion(Hashtable<String, Double> productos) {
		System.out.println("--------------------");
		System.out.println("1. Mostrar productos");
		System.out.println("2. Informacón del producto");
		System.out.println("3. Añadir producto a la cesta");
		System.out.println("4. Cesta");
		System.out.println("5. Salir");

		String inputString = scnr.nextLine();
		int opcion = Integer.parseInt(inputString);

		switch (opcion) {
		case 1:
			mostrarInterfazArticulos(productos);
			break;
		case 2:
			//
			mostrarInfoArticulo(productos);
			break;
		case 3:
			mostrarInterfazAddProducto();
			break;
		case 4:
			mostrarInterfazCesta();

			break;
		case 5:
			terminarPrograma();

			break;

		default:
			System.out.println("La opción seleccionada no existe.");
			break;
		}

	}

}
