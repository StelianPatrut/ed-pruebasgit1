package proyectoFinalFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.script.ScriptException;

public class menuPrincipal {
	static ArrayList<jugadores> jugadores = new ArrayList();
	static int[] puntos;

	public static void main(String[] args) throws ScriptException, IOException {

		menuPrincipal();

	}

	public static void menuPrincipal() throws ScriptException, IOException {//Menu principal

		Scanner teclado = new Scanner(System.in);
		System.out.println("¿Hola, que deseas hacer?");
		System.out.println("-------------------------");
		System.out.println("|-----1-JUGAR PARTIDA---|");
		System.out.println("|-----2-RANKIGN---------|");
		System.out.println("|-----3-HISTÓRICO-------|");
		System.out.println("|-----4-JUGADORES-------|");
		System.out.println("|-----5-SALIR-----------|");
		System.out.println("-------------------------");

		int respuesta = teclado.nextInt();
		switch (respuesta) {
		case 1:
			jugarPartida(jugadores);
			break;
		case 2:
			ranking(jugadores);
			break;
		case 3:
			historico();
			break;
		case 4:
			jugadores(jugadores);
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("No has elegido la opcion correcta, prueba otra vez");
			menuPrincipal();
			break;
		}

	}

	public static void jugadores(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//menu jugadores
		Scanner teclado = new Scanner(System.in);

		jugadores j = new jugadores("");
		System.out.println("¿Hola, que deseas hacer?");
		System.out.println("----------------------------");
		System.out.println("|-----1-VER JUGADORES------|");
		System.out.println("|-----2-AÑADIR JUGADORES---|");
		System.out.println("|-----3-ELIMINAR JUGADORES-|");
		System.out.println("|-----4-VOLVER-------------|");
		System.out.println("---------------------------");
		int respuesta = teclado.nextInt();

		switch (respuesta) {
		case 1:
			j.verJugadores(jugadores);
			break;
		case 2:
			j.addJugadores(jugadores);
			break;
		case 3:
			j.eliminarJugador(jugadores);
			break;
		case 4:
			menuPrincipal();
			break;
		default:
			System.out.println("No has elegido la opcion correcta, prueba otra vez");
			jugadores(jugadores);

			break;
		}

	}

	public static void menuPartida(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//menu partida
		Scanner teclado = new Scanner(System.in);
		int numJugadores = jugadores.size();

		System.out.println("¿Que nivel quieres jugar?");
		System.out.println("-------------------------");
		System.out.println("|-----1-PARTIDA RAPIDA---|");
		System.out.println("|-----2-PARTIDA CORTA----|");
		System.out.println("|-----3-PARTIDA NORMAL---|");
		System.out.println("|-----4-PARTIDA LARGA----|");
		System.out.println("|-----5-VOLVER-----------|");
		System.out.println("-------------------------");

		int respuesta = teclado.nextInt();
		switch (respuesta) {
		case 1:
			partidaRapida(jugadores);
			break;
		case 2:
			partidaCorta(jugadores);
			break;
		case 3:
			partidaNormal(jugadores);
			break;
		case 4:
			partidaLarga(jugadores);
			break;
		case 5:
			menuPrincipal();
			break;
		default:
			System.out.println("No has elegido la opcion correcta, prueba otra vez");
			menuPartida(jugadores);
			break;
		}

	}
	public static void jugarPartida(ArrayList<jugadores> jugadores) throws ScriptException, IOException{//llamo al metodo jugar partida
		partida p = new partida();
		p.jugarPartida(jugadores);
	}
	public static void partidaRapida(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//llamo al metodo partida rapida
		partida p = new partida();
		p.partidaRapida(jugadores);
	}
	public static void partidaCorta(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//llamo al metodo partida Corta
		
		partida p = new partida();
		p.partidaCorta(jugadores);
		
	}
	public static void partidaNormal(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//llamo al metodo partida normal
		partida p = new partida();
		p.partidaNormal(jugadores);
	}
	public static void partidaLarga(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//llamo al metodo partida larga
		partida p = new partida();
		p.partidaLarga(jugadores);
		
	}


	public static void ranking(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//muestro el ranking
		ranking ranking = new ranking(jugadores);
		ranking.mostrarRanking();
		menuPrincipal();
	}

	public static void historico() throws ScriptException, IOException {//muestro el historico
		String nombreFichero = "historico.txt";
		String ruta = "src/proyectoFinalFinal/";
		File fichero = new File(ruta + nombreFichero);

		historico historico = new historico();

		historico.existeFichero(fichero);
//		File historico1 = new File("historico.txt");

		try {
			// Crea una instancia de la clase Scanner para leer el archivo
			Scanner scanner = new Scanner(fichero);

			// Lee el archivo línea por línea y lo imprime en la consola
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				System.out.println(linea);
			}

			// Cierra el scanner
			scanner.close();

		} catch (IOException e) {
			System.out.println("Error al leer el archivo de historial: " + e.getMessage());
		}
		menuPrincipal();

	}

	public void addJugadores(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//añado jugadores
		jugadores j = new jugadores("");
		j.addJugadores(jugadores);

		
		puntos = new int[jugadores.size()];
	}

	public void verJugadores(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//veo los jugadores que tengo
		jugadores jugador = new jugadores("");
		jugador.verJugadores(jugadores);
		
		}

	public void eliminarJugador(ArrayList<jugadores> jugadores) throws ScriptException, IOException {//elimino un jugador
		Scanner teclado = new Scanner(System.in);
		System.out.println("Ingrese el nombre del jugador que desea eliminar:");
		String nombreJugador = teclado.nextLine();
		jugadores j = new jugadores("");
		j.eliminarJugadores(nombreJugador);
		
	}

}
