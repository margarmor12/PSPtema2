/*
 * Aplicación sencilla para la prueba de Process / ProcessBuilder
 * 
 * El proceso padre ahora ejecuta otra clase del proyecto como proceso hijo
 * A continuación se encarga de lanzar ese programa / comando como porceso hijo
 * 
 * ¡¡¡NOVEDAD!!!: El proceso padre envía información al proceso hijo a través
 * de los argumentos del main (String[] args)
 *
* Desde el proceso padre se espera a que el programa / comando termine 
 * El proceso padre recoge la información de salida que pueda
 * producir el proceso hijo, tanto salida normal (stdout) como salida de error
 * (stderr).
 *
 + Por último, el proceso padre muestra el valor de salida del proceso hijo
*/
package psp.u2.ejemplos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author Vicente Martínez
 */
public class Lanzador {
    public static void main(String[] args) {
        
        // Código para pedir un programa/comando a ejecutar
        String comando = "java psp.u2.ejemplos.Lanzado 8 \"hola qué tal estás\"";
        
        try {
            // Preparamos el entrono de ejecución del proceso
            // Como no sabemos el contenido del comando, forzamos su conversión
            // a una lista para que no haya problemas con su ejecución
            ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));
            /****** IMPORTANTE ******/
            // Esta línea se pone para cambiar el directorio de trabajo del 
            // proceso hijo (el proceso es java), para que encuentre la clase
            // en la ruta psp/u2/ejemplos/Lanzado
            pb.directory(new File("build/classes"));
            
            // Ejecutamos el proceso hijo
            Process p = pb.start();          
            
            // Comprobamos si el proceso está vivo
            boolean isAlive = p.isAlive();
            System.out.println("El proceso: " + (isAlive ? "sigue activo" : "ha terminado"));

            // Ahora no tenemos que enviar nada, los datos los hemos pasado 
            // como argumentos al programa en la llamada

            
            // Esperamos a que acabe para recoger el valor de salida
            int exitValue = p.waitFor();

            // Comprobamos, otra vez, si el proceso está vivo (no debería)
            isAlive = p.isAlive();
            System.out.println("El proceso: " + (isAlive ? "sigue activo" : "ha terminado"));

            
            String lineaInput;
            // Recogemos la SALIDA ESTÁNDAR del proceso hijo            
            // Todo el intercambio se hace usando CP1252 (iso-8859-1 --> latin1)
            BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream(), "CP1252"));
            while ((lineaInput = brInput.readLine())!=null) {
                System.out.println("stdout(hijo)>> " + lineaInput);
            }
            
            String lineaError;
            // Recogemos la SALIDA ERROR ESTÁNDAR del proceso hijo
            // El encoding CP850 se usa para leer bien los mensajes de la consola
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream(), "CP1252"));
            while ((lineaError = brError.readLine())!=null) {
                System.out.println("stderr(hijo)> " + lineaError);
            }
            
            
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
