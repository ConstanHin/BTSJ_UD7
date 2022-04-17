package TA07_2;

import java.util.Hashtable;

import javax.swing.JOptionPane;

public class DosApp {
	// {nombre,iva,precio}
	private static final String[][] productos = { { "patata", "4", "1" }, { "zanahoria", "4", "0.75" },
			{ "tomate", "4", "1.29" }, { "jabon", "21", "2.95" } };
	private static int totalCantidadArticulos = 0;

	public static void main(String[] args) {

		boolean addingProducts = true;
		String listaProductos = "";
		double totalSinIva = 0;
		double totalConIva = 0;

		while (addingProducts) {
			// Añadir producto
			String[] producto = escogerProducto(productos);
			if (producto == null) {
				continue;
			}

			// Añadir cantidad
			double cantidad = escogerCantidad();
			totalCantidadArticulos += cantidad;
			// Calcular precio total
			double precioSinIva = calcularPrecioSinIva(producto, cantidad);
			// Calcular precio total con iva
			double precioConIva = calcularPrecioConIva(precioSinIva, producto[1]);

			// Total sin iva
			totalSinIva += precioSinIva;
			// Total con iva
			totalConIva += precioConIva;

			// Formatear string para mostrar
			listaProductos += formatListString(producto[0], producto[1], producto[2], precioSinIva, cantidad,
					precioConIva);

			// Preguntar si quieren añadir mas productos
			addingProducts = addMoreProducts();

		}
		String cantidadPagadaString = JOptionPane.showInputDialog(null, "Cantidad pagada", "Cantidad pagada",
				JOptionPane.PLAIN_MESSAGE);
		double cantidadPagada = Double.parseDouble(cantidadPagadaString);
		double cambio = cantidadPagada - totalConIva;
		cambio = roundTwoDecimals(cambio);

		listaProductos = formatTicketString(listaProductos, totalSinIva, totalConIva, cantidadPagada, cambio);

		JOptionPane.showMessageDialog(null, listaProductos, "Ticket", JOptionPane.PLAIN_MESSAGE);
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
	private static String formatTicketString(String listaProductos, double totalSinIva, double totalConIva,
			double cantidadPagada, double cambio) {
		listaProductos += "------------------------------------------------- \n";
		listaProductos += "Nr total productos:" + totalCantidadArticulos + "\n";
		listaProductos += "Precio total sin IVA- " + totalSinIva + "€ \n";
		listaProductos += "Precio total con IVA- " + totalConIva + "€ \n";
		listaProductos += "------------------------------------------------- \n";
		listaProductos += "Cantidad pagada: " + cantidadPagada + "€ \n";
		listaProductos += "Cambio: " + cambio + "€ \n";
		return listaProductos;
	}

	/**
	 * Formatear lista de productos añadidos
	 * 
	 * @param nombreProducto
	 * @param iva
	 * @param precio
	 * @param precioSinIva
	 * @param cantidad
	 * @param precioConIva
	 * @return
	 */
	private static String formatListString(String nombreProducto, String iva, String precio, double precioSinIva,
			double cantidad, double precioConIva) {
		String formatedString = nombreProducto + "- " + cantidad + " u x " + precio + "€/u " + precioSinIva + "€ "
				+ " iva " + iva + "% " + precioConIva + "€ \n";
		return formatedString;
	}

	/**
	 * Calcular el precio total con iva
	 * 
	 * @param precioSinIva
	 * @param ivaString
	 * @return
	 */
	private static double calcularPrecioConIva(double precioSinIva, String ivaString) {
		double iva = Double.parseDouble(ivaString);
		double precioIva = precioSinIva * 21 / 100;
		double totalConIva = precioSinIva + precioIva;
		totalConIva = roundTwoDecimals(totalConIva);
		return totalConIva;
	}

	/**
	 * Calcular el precio total sin iva
	 * 
	 * @param producto
	 * @param cantidad
	 * @return
	 */
	private static double calcularPrecioSinIva(String[] producto, double cantidad) {
		double precioProducto = Double.parseDouble(producto[2]);
		double totalSinIva = precioProducto * cantidad;
		totalSinIva = roundTwoDecimals(totalSinIva);
		return totalSinIva;
	}

	/**
	 * Redondear a dos decimales
	 * 
	 * @param numero
	 * @return double
	 */
	public static double roundTwoDecimals(double numero) {
		return (double) Math.round(numero * 100) / 100;
	}

	/**
	 * 
	 * 
	 * @param productos
	 */
	public static String[] escogerProducto(String[][] productos) {

		String messageString = "Introduce el nombre de un producto: ";
		for (int i = 0; i < productos.length; i++) {
			messageString = messageString + "\n" + productos[i][0] + ":  " + productos[i][2] + "€";

		}
		// Input Dialog
		String nombreProducto = JOptionPane.showInputDialog(null, messageString, "Nombre producto",
				JOptionPane.PLAIN_MESSAGE);
		String[] producto = validarProducto(nombreProducto, productos);

		return producto;
	}

	/**
	 * 
	 * @param producto
	 * @param productos
	 * @return
	 */
	public static String[] validarProducto(String producto, String[][] productos) {
		String productoFormated = producto.toLowerCase();
		// Comprobar si el producto existe en la lista de productos
		for (int i = 0; i < productos.length; i++) {

			if (productoFormated.equals(productos[i][0])) {
				return productos[i];
			}
		}
		JOptionPane.showMessageDialog(null, "El producto no existe. Intentalo de nuevo");
		return null;

	}

	/**
	 * 
	 * @return
	 */
	public static double escogerCantidad() {
		String inputCantidad = JOptionPane.showInputDialog(null, "Introduzca la cantidad: ", "Cantidad",
				JOptionPane.PLAIN_MESSAGE);
		double cantidad = Double.parseDouble(inputCantidad);
		return cantidad;
	}

	/**
	 * 
	 * @return
	 */
	private static boolean addMoreProducts() {
		int response = JOptionPane.showConfirmDialog(null, "¿Quiere añadir mas productos?");

		if (response == 1) {
			return false;
		} else {
			return true;
		}

	}

}
