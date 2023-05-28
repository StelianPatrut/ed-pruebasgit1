package proyectoFinalFinal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.script.ScriptException;

public class partida extends menuPrincipal {

	
	public static void jugarPartida(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
	    Scanner teclado = new Scanner(System.in);

	    // Preguntar al usuario si quiere practicar o jugar
	    System.out.println("¿Qué quieres hacer: Practica o Jugar?");
	    String respuesta = teclado.nextLine().toLowerCase();

	    if (respuesta.equals("practica")) {
	        // Modo de práctica

	        System.out.println("La práctica constará de una ronda de 10 preguntas.");
	        int numRondas = 9; // Número de rondas restantes

	        while (numRondas >= 0) {
	            ArrayList<jugadores> practica = new ArrayList<>();
	            jugadores practica1 = new jugadores(" ");
	            practica.add(practica1); // Agregar un jugador de práctica

	            preguntas pr = new preguntas();
	            pr.generarPreguntas(practica); // Generar preguntas para la práctica

	            System.out.println("Dame tu respuesta: ");
	            String respuesta1 = teclado.nextLine();

	            if (pr.compararRespuestas(respuesta1) == true) {
	                System.out.println("¡Felicidades! Siguiente punto.");
	            }

	            numRondas--;
	        }

	        menuPrincipal(); // Volver al menú principal después de la práctica
	    } else {
	        // Modo de juego

	        System.out.println("Lista de jugadores:");

	        if (jugadores.size() == 0) {
	            // No hay jugadores agregados
	            System.out.println("Lo siento, no tienes jugadores todavía.");
	            System.out.println("¿Quieres ir a añadir jugadores? - SI o NO");
	            String resp = teclado.nextLine();

	            if (resp.equalsIgnoreCase("si")) {
	                jugadores(jugadores); // Ir a la función para añadir jugadores
	            } else {
	                menuPartida(jugadores); // Ir al menú de partida
	            }
	        } else {
	            // Mostrar la lista de jugadores
	            for (int i = 0; i < jugadores.size(); i++) {
	                System.out.println(jugadores.get(i).getNombre());
	            }
	        }

	        menuPartida(jugadores); // Ir al menú de partida
	    }
	}

	public static void partidaRapida(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
	    Scanner teclado = new Scanner(System.in);

	    int numRonda = 3; // Número de rondas en la partida rápida
	    int numJugadores = jugadores.size(); // Número de jugadores en la lista

	    int ronda = 1; // Contador de rondas
	    while (ronda <= numRonda) {
	        System.out.println("Ronda " + ronda);

	        for (int i = 0; i < numJugadores; i++) {
	            jugadores jugador = jugadores.get(i);
	            System.out.println("Pregunta para: " + jugador.getNombre());

	            if (jugador.getNombre().startsWith("cpu")) {
	                // Jugador CPU, genera una pregunta para la CPU y compara la respuesta
	                preguntas pregunta = new preguntas();
	                pregunta.generarpreguntasCpu();

	                if (pregunta.compararRespuestasCpu()) {
	                    jugador.sumarPuntos();
	                }
	            } else {
	                // Jugador humano, genera una pregunta y espera la respuesta del usuario
	                preguntas pregunta = new preguntas();
	                pregunta.generarPreguntas(jugadores);
	                System.out.println("Dame una respuesta:");
	                String respuesta = teclado.nextLine().trim();

	                if (pregunta.compararRespuestas(respuesta)) {
	                    jugador.sumarPuntos();
	                }
	            }

	            System.out.println("Puntos de " + jugador.getNombre() + ": " + jugador.getPuntuacion());
	        }

	        ronda++;
	    }

	    System.out.println("Puntos de cada jugador:");
	    for (int i = 0; i < numJugadores; i++) {
	        System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntuacion());
	    }

	    historico historico = new historico();
	    StringBuilder partida = new StringBuilder();

	    for (int i = 0; i < numJugadores; i++) {
	        partida.append(jugadores.get(i).getNombre()).append(" ").append(jugadores.get(i).getPuntuacion()).append(" ");
	    }

	    historico.registrarPartida(partida.toString().trim()); // Registrar la partida en el historial
	    ranking ranking = new ranking(jugadores);
	    ranking.generarRanking(); // Generar el ranking actualizado

	    menuPartida(jugadores); // Volver al menú de partida
	}


	public static void partidaCorta(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
		  Scanner teclado = new Scanner(System.in);
		    int numRonda = 5;
		    int numJugadores = jugadores.size();

		    int ronda = 1;
		    while (ronda <= numRonda) {
		        System.out.println("Ronda " + ronda);

		        for (int i = 0; i < numJugadores; i++) {
		            jugadores jugador = jugadores.get(i);
		            System.out.println("Pregunta para: " + jugador.getNombre());
		            if (jugador.getNombre().startsWith("cpu")) {
		                preguntas pregunta = new preguntas();
		                pregunta.generarpreguntasCpu();

		                if (pregunta.compararRespuestasCpu()) {
		                    jugador.sumarPuntos();
		                }
		            } else {
		                preguntas pregunta = new preguntas();
		                pregunta.generarPreguntas(jugadores);
		                System.out.println("Dame una respuesta:");
		                String respuesta = teclado.nextLine().trim();
		                if (pregunta.compararRespuestas(respuesta)) {
		                    jugador.sumarPuntos();
		                }
		            }
		            System.out.println("Puntos de " + jugador.getNombre() + ": " + jugador.getPuntuacion());
		        }
		        ronda++;
		    }

		    System.out.println("Puntos de cada jugador:");
		    for (int i = 0; i < numJugadores; i++) {
		        System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntuacion());
		    }

		    historico historico = new historico();
		    StringBuilder partida = new StringBuilder();
		    for (int i = 0; i < numJugadores; i++) {
		        partida.append(jugadores.get(i).getNombre()).append(" ").append(jugadores.get(i).getPuntuacion()).append(" ");
		    }
		    historico.registrarPartida(partida.toString().trim());
		    ranking ranking = new ranking(jugadores);
		    ranking.generarRanking();

		    menuPartida(jugadores);	}

	public static void partidaNormal(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
		  Scanner teclado = new Scanner(System.in);
		    int numRonda = 10;
		    int numJugadores = jugadores.size();

		    int ronda = 1;
		    while (ronda <= numRonda) {
		        System.out.println("Ronda " + ronda);

		        for (int i = 0; i < numJugadores; i++) {
		            jugadores jugador = jugadores.get(i);
		            System.out.println("Pregunta para: " + jugador.getNombre());
		            if (jugador.getNombre().startsWith("cpu")) {
		                preguntas pregunta = new preguntas();
		                pregunta.generarpreguntasCpu();

		                if (pregunta.compararRespuestasCpu()) {
		                    jugador.sumarPuntos();
		                }
		            } else {
		                preguntas pregunta = new preguntas();
		                pregunta.generarPreguntas(jugadores);
		                System.out.println("Dame una respuesta:");
		                String respuesta = teclado.nextLine().trim();
		                if (pregunta.compararRespuestas(respuesta)) {
		                    jugador.sumarPuntos();
		                }
		            }
		            System.out.println("Puntos de " + jugador.getNombre() + ": " + jugador.getPuntuacion());
		        }
		        ronda++;
		    }

		    System.out.println("Puntos de cada jugador:");
		    for (int i = 0; i < numJugadores; i++) {
		        System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntuacion());
		    }

		    historico historico = new historico();
		    StringBuilder partida = new StringBuilder();
		    for (int i = 0; i < numJugadores; i++) {
		        partida.append(jugadores.get(i).getNombre()).append(" ").append(jugadores.get(i).getPuntuacion()).append(" ");
		    }
		    historico.registrarPartida(partida.toString().trim());
		    ranking ranking = new ranking(jugadores);
		    ranking.generarRanking();

		    menuPartida(jugadores);
	}

	public static void partidaLarga(ArrayList<jugadores> jugadores) throws ScriptException, IOException {
		  Scanner teclado = new Scanner(System.in);
		    int numRonda = 20;
		    int numJugadores = jugadores.size();

		    int ronda = 1;
		    while (ronda <= numRonda) {
		        System.out.println("Ronda " + ronda);

		        for (int i = 0; i < numJugadores; i++) {
		            jugadores jugador = jugadores.get(i);
		            System.out.println("Pregunta para: " + jugador.getNombre());
		            if (jugador.getNombre().startsWith("cpu")) {
		                preguntas pregunta = new preguntas();
		                pregunta.generarpreguntasCpu();

		                if (pregunta.compararRespuestasCpu()) {
		                    jugador.sumarPuntos();
		                }
		            } else {
		                preguntas pregunta = new preguntas();
		                pregunta.generarPreguntas(jugadores);
		                System.out.println("Dame una respuesta:");
		                String respuesta = teclado.nextLine().trim();
		                if (pregunta.compararRespuestas(respuesta)) {
		                    jugador.sumarPuntos();
		                }
		            }
		            System.out.println("Puntos de " + jugador.getNombre() + ": " + jugador.getPuntuacion());
		        }
		        ronda++;
		    }

		    System.out.println("Puntos de cada jugador:");
		    for (int i = 0; i < numJugadores; i++) {
		        System.out.println(jugadores.get(i).getNombre() + ": " + jugadores.get(i).getPuntuacion());
		    }

		    historico historico = new historico();
		    StringBuilder partida = new StringBuilder();
		    for (int i = 0; i < numJugadores; i++) {
		        partida.append(jugadores.get(i).getNombre()).append(" ").append(jugadores.get(i).getPuntuacion()).append(" ");
		    }
		    historico.registrarPartida(partida.toString().trim());
		    ranking ranking = new ranking(jugadores);
		    ranking.generarRanking();

		    menuPartida(jugadores);
	}
}
