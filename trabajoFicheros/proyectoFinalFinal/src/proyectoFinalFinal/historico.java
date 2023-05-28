package proyectoFinalFinal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class historico extends menuPrincipal {
	

	
	public void existeFichero(File fichero) {//compruebo si existe el fichero
        try {
            // verifico si existe el fichero, en el caso de que no lo crea
            if (!fichero.exists()) {
                fichero.createNewFile(); // creo el archivo
                System.out.println("Se acaba de crear el fichero");
            } else {
                System.out.println("El fichero existe así que no se va a crear");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
    }
	public void registrarPartida(String partida) {//registro la partida
	    try {
	        FileWriter writer = new FileWriter("src/proyectoFinalFinal//historico.txt", true); // true indica que se añadirá al archivo existente
	        writer.write(partida + "\n\n"); // escribir la partida en el archivo
	        writer.close(); // cerrar el archivo
	    } catch (IOException e) {
	        System.out.println("Error al escribir en el archivo de historial: " + e.getMessage());
	    }
	}
	

}

