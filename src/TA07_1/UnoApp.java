package TA07_1;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class UnoApp {

	static Scanner scnr = new Scanner(System.in);

	// Calcular la nota media de los alumnos
	public static void main(String[] args) {

		// Crear tabla alumnos
		Hashtable<String, Double> tablaAlumnos = new Hashtable<String, Double>();
		// Pedir el numero de alumnos del curso
		int nrAlumnos = numeroAlumnos();

		// Pedir las notas y calcular la media y añadirla a la tabla
		for (int i = 0; i < nrAlumnos; i++) {
			String nombreAlumno = crearAlumno();
			ArrayList<Double> notasAlumno = addNotas(nombreAlumno);
			double mediaAlumno = calcularMedia(notasAlumno);
			addDataToTablaAlumnos(tablaAlumnos, nombreAlumno, mediaAlumno);
		}

		// Mostrar los alumnos y sus notas medias
		System.out.println("----------------------------");
		System.out.println("Notas medias de los alumnos: ");
		System.out.println(tablaAlumnos);
		System.out.println("----------------------------");
		
		scnr.close();

	}

	/**
	 * 
	 * Pedir por teclado y devolver el numero de alumnos
	 * 
	 * @return Integer
	 */
	public static int numeroAlumnos() {
		System.out.println("Cuantos alumnos hay en el curso?");
		int numeroAlumnos = scnr.nextInt();
		scnr.nextLine();
		return numeroAlumnos;
	}

	/**
	 * Pedir por teclado y devolver la lista de las notas del alumno
	 * 
	 * @param nombreAlumno
	 * @return ArrayList<Double>
	 */
	public static ArrayList<Double> addNotas(String nombreAlumno) {
		String[] enumeracion = { "1ª", "2ª", "3ª" };
		ArrayList<Double> listaNotas = new ArrayList<Double>();

		for (int i = 0; i < 3; i++) {
			System.out.println("Introduzca la " + enumeracion[i] + " nota de " + nombreAlumno + " :");
			double nota = scnr.nextDouble();
			scnr.nextLine();
			listaNotas.add(nota);

		}

		System.out.println("Notas de " + nombreAlumno);
		System.out.println(listaNotas);

		return listaNotas;

	}

	/**
	 * Calcula la media de las notas del alumno
	 * 
	 * @param notasAlumno
	 * @return double
	 */
	public static double calcularMedia(ArrayList<Double> listaNotasAlumno) {
		Iterator<Double> iterator = listaNotasAlumno.iterator();
		double suma = 0;

		while (iterator.hasNext()) {
			suma = suma + iterator.next();
		}
		double notaMedia = suma / listaNotasAlumno.size();
		return notaMedia;
	}

	/**
	 * Añade un registo con el nombre del alumno y su nota media.
	 * 
	 * @param tablaAlumnos
	 * @param nombreAlumno
	 * @param mediaAlumno
	 */
	public static void addDataToTablaAlumnos(Hashtable<String, Double> tablaAlumnos, String nombreAlumno,
			Double mediaAlumno) {
		double roundedMediaAlumno = (double) Math.round(mediaAlumno * 10) / 10;
		tablaAlumnos.put(nombreAlumno, roundedMediaAlumno);
	}

	/**
	 * Introduce el nombre del alumno por teclado y lo devuelve
	 * 
	 * @return String
	 */
	public static String crearAlumno() {

		System.out.println("Introduzca el nombre del alumno:");
		String nombreAlumno = scnr.nextLine();
		return nombreAlumno;

	}

}
