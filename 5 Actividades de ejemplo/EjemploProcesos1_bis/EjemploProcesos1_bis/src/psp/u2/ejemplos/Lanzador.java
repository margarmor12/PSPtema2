/*
 * Aplicación sencilla para la prueba de Process / ProcessBuilder
 * La aplicación solicita al usuario que introduzca un programa / comando
 * A continuación se encarga de lanzar ese programa / comando como porceso hijo
 * Desde el proceso padre se espera a que el programa / comando termine 
 + Por último, el proceso padre muestra el valor de salida del proceso hijo
*/
package psp.u2.ejemplos;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Vicente Martínez
 */
public class Lanzador {
    public static void main(String[] args) {
        
      
        
        try {
            // Preparamos el entrono de ejecución del proceso
            // Como no sabemos el contenido del comando, forzamos su conversión
            // a una lista para que no haya problemas con su ejecución
            ProcessBuilder pb = new ProcessBuilder("java psp.u2.ejemplos.NewClass".split("\\s"));
            pb.directory(new File ("build/classes"));

            // Ejecutamos el proceso hijo
            Process p = pb.start();          
            
            // Comprobamos si el proceso está vivo
            boolean isAlive = p.isAlive();
            System.out.println("El proceso: " + (isAlive ? "sigue activo" : "ha terminado"));
            
            // Esperamos a que acabe para recoger el valor de salida
            int exitValue = p.waitFor();

            // Comprobamos, otra vez, si el proceso está vivo (no debería)
            isAlive = p.isAlive();
            System.out.println("El proceso: " + (isAlive ? "sigue activo" : "ha terminado"));
            
            if (exitValue == 0) {
                System.out.println("El comando " + pb.command().toString() + " ha finalizado bien");
            } else {
                System.out.println("El comando " + pb.command().toString() + " ha finalizado con errores");
            }
            
        } catch (InterruptedException | IOException ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }                       
    }
}
