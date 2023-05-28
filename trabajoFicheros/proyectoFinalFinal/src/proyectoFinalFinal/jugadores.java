package proyectoFinalFinal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.script.ScriptException;

public class jugadores extends menuPrincipal {
	private ArrayList<jugadores> jugadores;
	private String nombre;
	private int puntuacion;

	public jugadores(String nombre) {
		this.nombre = nombre;
		this.puntuacion = 0;
	}

	public ArrayList<jugadores> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<jugadores> jugadores) {
		this.jugadores = jugadores;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public void mostrarDatos() {
		System.out.println("Nombre: " + nombre);
	}

	public void addJugadores(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
	    Scanner teclado = new Scanner(System.in);
	    System.out.println("¿Qué quieres añadir, jugadores humano o jugadores CPU?");
	    String respuesta2 = teclado.nextLine().toLowerCase();
	    if (respuesta2.equals("humano")) {
	        System.out.println("¿Cuántos jugadores deseas agregar?");
	        int numJugadores = teclado.nextInt();
	        teclado.nextLine();

	        // Comprobar si el archivo de ranking existe
	        File rankingFile = new File("src/proyectoFinalFinal/ranking.txt");
	        boolean existeRanking = rankingFile.exists();

	        int jugadoresAgregados = 0; // Contador de jugadores agregados

	        for (int i = 0; i < numJugadores; i++) {
	            System.out.println("Ingrese el nombre del jugador " + (i + 1) + ":");
	            String nombre = teclado.nextLine().replaceAll("\\s+", "");// elimino los espacios en blanco en el caso de que el jugador añada nombres con espacios.

	            // Verificar si el jugador ya está en la lista de jugadores
	            if (jugadorEnLista(jugadores, nombre)) {
	                System.out.println("El jugador " + nombre + " ya está en la lista de jugadores.");
	                continue; // Pasar al siguiente jugador
	            }

	            // Comprobar si el jugador existe en el ranking
	            if (existeRanking && jugadorEnRanking(nombre)) {
	                System.out.println("El jugador " + nombre + " ya existe en el ranking.");

	                // Preguntar si desea jugar con el jugador existente
	                System.out.println("¿Deseas jugar con este jugador? (s/n)");
	                String respuesta = teclado.nextLine().toLowerCase();

	                if (respuesta.equals("s") || respuesta.equals("si")) {
	                    jugadores jugador = new jugadores(nombre);
	                    jugadores.add(jugador);
	                    jugadoresAgregados++;
	                }
	            } else {
	                // Comprobar si se ha alcanzado el límite de jugadores
	                if (jugadores.size() >= 4) {
	                    System.out.println("Ya se ha alcanzado el límite de jugadores para la partida.");
	                    break; // Salir del bucle
	                }

	                jugadores jugador = new jugadores(nombre);
	                jugadores.add(jugador);
	                jugadoresAgregados++;
	            }
	        }

	        if (jugadoresAgregados > 0) {
	            System.out.println("Se han agregado " + jugadoresAgregados + " jugadores humanos.");
	        }
	    } else if (respuesta2.equals("cpu")) {
	        System.out.println("¿Cuántas CPUs quieres añadir?");
	        int numCpus = teclado.nextInt();
	        teclado.nextLine();
	        for (int i = 0; i < numCpus; i++) {
	            String nombre = "CPU" + (i + 1);
	            jugadores cpu = new jugadores(nombre.toLowerCase());
	            jugadores.add(cpu);
	        }
	        System.out.println("Se han agregado " + numCpus + " CPUs.");
	    }
	    jugadores(jugadores);
	}

	public void verJugadores(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
	    File rankingFile = new File("src/proyectoFinalFinal/ranking.txt");
	    if (!rankingFile.exists()) {
	        System.out.println("No hay jugadores en el ranking");
	      
	    }
	    /*
	    Lectura del archivo de ranking:
	   Abre un archivo de texto llamado rankingFile utilizando BufferedReader y FileReader.
	   Lee cada línea del archivo.
	   Divide cada línea en partes utilizando el espacio como separador y almacena las partes en un array de String llamado parts.
	   Si la línea contiene al menos dos partes, se asume que la primera parte es el nombre del jugador y la segunda parte es la puntuación.
	   Se muestra en la consola el mensaje "Jugador en el ranking: " seguido del nombre del jugador.


	   Mostrar jugadores agregados:
	   Verifica si la lista de jugadores (jugadores) está vacía.
	   Si la lista está vacía, se muestra el mensaje "No tienes jugadores agregados."
	   Si la lista no está vacía, se muestra el mensaje "Jugadores agregados:" y luego se recorre la lista de jugadores utilizando un bucle for.
	   En cada iteración, se llama al método mostrarDatos() del objeto jugadores.get(i) para mostrar los datos del jugador.*/
	    try (BufferedReader reader = new BufferedReader(new FileReader(rankingFile))) {
	        String line;
	        while ((line = reader.readLine()) != null) {
	            String[] parts = line.split(" ");
	            if (parts.length >= 2) {
	                String nombre = parts[0];
	                int puntuacion = Integer.parseInt(parts[1]);
	                System.out.println("Jugador en el ranking: " + nombre);
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
	    }

	    if (jugadores.size() == 0) {
	        System.out.println("No tienes jugadores agregados.");
	    } else {
	        System.out.println("Jugadores agregados:");
	        for (int i = 0; i < jugadores.size(); i++) {
	            jugadores.get(i).mostrarDatos();
	        }
	    }
	    jugadores(jugadores);
	}

	public boolean jugadorEnLista(ArrayList<jugadores> jugadores, String nombre) {
	    for (int i = 0; i < jugadores.size(); i++) {
	        jugadores jugador = jugadores.get(i);
	        if (jugador.getNombre().equalsIgnoreCase(nombre)) {
	            return true;
	        }
	    }
	    return false;
	}


	public boolean jugadorEnRanking(String nombre) throws FileNotFoundException {
	    // Crear un objeto File que representa el archivo de ranking
	    File rankingFile = new File("src/proyectoFinalFinal/ranking.txt");

	    // Verificar si el archivo de ranking no existe
	    if (!rankingFile.exists()) {
	        return false; // El jugador no está en el ranking (archivo no existe)
	    }

	    try (Scanner scanner = new Scanner(rankingFile)) {
	        // Leer el archivo de ranking línea por línea
	        while (scanner.hasNextLine()) {
	            String linea = scanner.nextLine();

	            // Dividir la línea en partes utilizando el espacio como separador
	            String[] partes = linea.split(" ");

	            // Verificar si hay al menos una parte en la línea
	            if (partes.length >= 1) {
	                String nombreRanking = partes[0];

	                // Verificar si el nombre del ranking coincide con el nombre proporcionado
	                if (nombreRanking.equalsIgnoreCase(nombre)) {
	                    return true; // El jugador está en el ranking
	                }
	            }
	        }
	    } catch (IOException e) {
	        System.out.println("Error al leer el archivo de ranking: " + e.getMessage());
	    }

	    return false; // El jugador no está en el ranking
	}

	public void eliminarJugadores(String jugador) throws IOException, ScriptException {
	    // Definir el nombre del archivo y la ruta del directorio
	    String nombreFichero = "ranking.txt";
	    String ruta = "src/proyectoFinalFinal/";

	    // Crear un objeto File que representa el archivo de ranking
	    File fichero = new File(ruta + nombreFichero);

	    // Verificar si el archivo de ranking no existe
	    if (!fichero.exists()) {
	        System.out.println("El archivo de ranking no existe.");
	        return;
	    }

	    // Crear una lista temporal para almacenar los jugadores a mantener
	    ArrayList<String> jugadoresTemp = new ArrayList<>();

	    // Leer el archivo de ranking línea por línea
	    Scanner scanner = new Scanner(fichero);
	    while (scanner.hasNextLine()) {
	        String linea = scanner.nextLine();

	        // Dividir la línea en partes utilizando el espacio como separador
	        String[] partes = linea.split(" ");

	        // Verificar si hay al menos dos partes en la línea
	        if (partes.length >= 2) {
	            String nombre = partes[0];

	            // Verificar si el nombre del jugador en la línea es diferente al jugador a eliminar
	            if (!nombre.equalsIgnoreCase(jugador)) {
	                jugadoresTemp.add(linea); // Agregar la línea a la lista temporal
	            }
	        }
	    }
	    scanner.close();

	    // Reescribir el archivo con los jugadores restantes
	    FileWriter writer = new FileWriter(fichero);
	    for (int i = 0; i < jugadoresTemp.size(); i++) {
	        String jugadorLinea = jugadoresTemp.get(i);
	        writer.write(jugadorLinea + "\n"); // Escribir la línea en el archivo
	    }
	    writer.close();

	    System.out.println("El jugador " + jugador + " ha sido eliminado del ranking.");
	    jugadores(jugadores);
	}
	
	public void sumarPuntos() {
	    this.puntuacion = this.puntuacion+1;
	}



		
	}
	

