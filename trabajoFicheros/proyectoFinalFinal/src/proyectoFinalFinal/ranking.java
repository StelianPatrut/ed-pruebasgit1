package proyectoFinalFinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ranking extends menuPrincipal {
	private ArrayList<jugadores> jugadores;
	ArrayList<jugadores> jugadoresRanking = new ArrayList<>();

	public ranking(ArrayList<jugadores> jugadores) {
		this.jugadores = jugadores;
		 this.jugadoresRanking = new ArrayList<>();
	}

	public void generarRanking() {
	    String nombreFichero = "ranking.txt";
	    String ruta = "src/proyectoFinalFinal/";
	    File fichero = new File(ruta + nombreFichero);

	    // Crear un mapa para almacenar los nombres de los jugadores y su puntuación acumulada
	    Map<String, Integer> rankingMap = new HashMap<>();

	    // Leer el archivo de ranking y actualizar las puntuaciones acumuladas en el mapa
	    try (Scanner scanner = new Scanner(fichero)) {
	        while (scanner.hasNextLine()) {
	            String linea = scanner.nextLine();  // Lee una línea del archivo
	            String[] partes = linea.split(" "); // Divide la línea en partes utilizando el espacio como separador
	            String nombre = partes[0];           // Obtiene el nombre desde la primera parte
	            int puntuacion = Integer.parseInt(partes[1]);  // Obtiene la puntuación desde la segunda parte y convierte a entero

	            if (rankingMap.containsKey(nombre)) {
	                int puntuacionAcumulada = rankingMap.get(nombre);  // Obtiene la puntuación acumulada anterior para el nombre
	                rankingMap.put(nombre, puntuacionAcumulada + puntuacion);  // Actualiza la puntuación acumulada sumando la nueva puntuación
	            } else {
	                rankingMap.put(nombre, puntuacion);  // Agrega una nueva entrada al mapa de ranking con el nombre y la puntuación
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
	    }

	    // Agregar las puntuaciones de los jugadores al mapa
	    for (int i = 0; i < jugadores.size(); i++) {
	        jugadores jugador = jugadores.get(i);  // Obtiene un jugador de la lista de jugadores
	        String nombre = jugador.getNombre();   // Obtiene el nombre del jugador
	        int puntuacion = jugador.getPuntuacion();  // Obtiene la puntuación del jugador

	        if (!nombre.startsWith("cpu")) {  // Excluye los jugadores cuyo nombre comienza con "cpu"
	            if (rankingMap.containsKey(nombre)) {
	                // Verifica si el nombre ya existe en el mapa de ranking
	                int puntuacionExistente = rankingMap.get(nombre);  // Obtiene la puntuación existente en el ranking para el nombre

	                // Actualiza la puntuación en el ranking sumando la nueva puntuación
	                rankingMap.put(nombre, puntuacionExistente + puntuacion);
	            } else {
	                rankingMap.put(nombre, puntuacion);  // Agrega una nueva entrada al mapa de ranking con el nombre y la puntuación
	            }
	        }
	    }

	    // Convertir el mapa en una lista de objetos jugadores para escribir en el archivo
	    List<jugadores> jugadoresRanking = new ArrayList<>();
	    for (Map.Entry<String, Integer> entry : rankingMap.entrySet()) {
	        String nombre = entry.getKey();  // Obtiene el nombre del jugador desde la entrada del mapa de ranking
	        int puntuacion = entry.getValue();  // Obtiene la puntuación del jugador desde la entrada del mapa de ranking

	        jugadores jugador = new jugadores(nombre);  // Crea un nuevo objeto jugador con el nombre
	        jugador.setPuntuacion(puntuacion);  // Establece la puntuación del jugador

	        jugadoresRanking.add(jugador);  // Agrega el jugador a la lista de jugadoresRanking
	    }

	    // Ordenar la lista de jugadoresRanking por puntuación (de mayor a menor)
	    Collections.sort(jugadoresRanking, new Comparator<jugadores>() {
	        public int compare(jugadores jugador1, jugadores jugador2) {
	            return Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion());
	        }
	    });

	    // Crear una nueva lista que contiene los registros actualizados y los registros anteriores
	    List<jugadores> nuevoRanking = new ArrayList<>(jugadoresRanking);

	    try (FileWriter writer = new FileWriter(fichero, true)) {
	        // Limpiar el archivo antes de escribir el nuevo ranking
	        PrintWriter pw = new PrintWriter(fichero);
	        pw.close();

	        for (int i = 0; i < nuevoRanking.size(); i++) {
	            jugadores jugador = nuevoRanking.get(i);  // Obtiene un jugador de la lista de nuevoRanking
	            writer.write(jugador.getNombre() + " " + jugador.getPuntuacion() + "\n");  // Escribe el nombre y la puntuación del jugador en el archivo
	        }
	        System.out.println("Se ha generado el ranking y se ha guardado en el archivo " + nombreFichero);
	    } catch (IOException e) {
	        System.out.println("Error al escribir en el archivo de ranking: " + e.getMessage());
	    }
	}

	public void mostrarRanking() {
		File ranking1 = new File("src/proyectoFinalFinal/ranking.txt");

		System.out.println("Ranking:");

		try {
			// Crea una instancia de la clase Scanner para leer el archivo
			Scanner scanner = new Scanner(ranking1);

			// Crear un mapa para almacenar los nombres de los jugadores y su puntuación
			// acumulada
			Map<String, Integer> rankingMap = new HashMap<>();

			// Leer el archivo línea por línea y actualizar la puntuación acumulada en el
			// mapa
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				String[] partes = linea.split(" ");
				String nombre = partes[0];
				int puntuacion = Integer.parseInt(partes[1]);

				if (rankingMap.containsKey(nombre)) {
					// Si el jugador ya existe en el mapa, se suma la puntuación
					int puntuacionAcumulada = rankingMap.get(nombre);
					rankingMap.put(nombre, puntuacionAcumulada + puntuacion);
				} else {
					// Si el jugador no existe en el mapa, se agrega con su puntuación
					rankingMap.put(nombre, puntuacion);
				}
			}

			// Convertir el mapa en una lista de objetos jugadores
			List<jugadores> jugadoresRanking = new ArrayList<>();
			for (Map.Entry<String, Integer> entry : rankingMap.entrySet()) {
				String nombre = entry.getKey();
				int puntuacion = entry.getValue();

				jugadores jugador = new jugadores(nombre);
				jugador.setPuntuacion(puntuacion);

				jugadoresRanking.add(jugador);
			}

			// Ordenar la lista de jugadoresRanking por puntuación (de mayor a menor)
			Collections.sort(jugadoresRanking, new Comparator<jugadores>() {
				@Override
				public int compare(jugadores jugador1, jugadores jugador2) {
					return Integer.compare(jugador2.getPuntuacion(), jugador1.getPuntuacion());
				}
			});

			// Imprimir el ranking ordenado
			for (jugadores jugador : jugadoresRanking) {
				System.out.println(jugador.getNombre() + " " + jugador.getPuntuacion());
			}

			// Cierra el scanner
			scanner.close();
		} catch (IOException e) {
			System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
		}
	}

}
