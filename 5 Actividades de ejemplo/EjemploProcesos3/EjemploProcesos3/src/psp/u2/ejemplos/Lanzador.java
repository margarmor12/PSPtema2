/*
 * Aplicación sencilla para la prueba de Process / ProcessBuilder
 * 
 * ¡¡¡NOVEDAD!!!: El proceso padre ahora ejecuta otra clase del proyecto 
 * como proceso hijo
 * A continuación se encarga de lanzar ese programa / comando como porceso hijo
 * 
 * ¡¡¡NOVEDAD!!!: El proceso padre envía información al proceso hijo a través
 * del stream getOutputStream para que cuando el hijo haga un Scanner.nextXXXXX
 * reciba la información que le pasa el padre
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
        String comando = "java psp.u2.ejemplos.Lanzado";
        
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

            // Antes de bloquear al proceso padre, le tenemos que enviar los 
            // datos que necesita el proceso hijo para que no se quede parardo
            // en le nextInt ni en el nextString
            String lineaOutput = "8 hola qué tal estás";
            // Enviamos la información por la ENTRADA ESTÁNDAR del proceso hijo
            PrintWriter pwInput = new PrintWriter(new BufferedWriter(new OutputStreamWriter(p.getOutputStream())), true);
            pwInput.println(lineaOutput);                                   
            
            // Esperamos a que acabe para recoger el valor de salida
            int exitValue = p.waitFor();

            // Comprobamos, otra vez, si el proceso está vivo (no debería)
            isAlive = p.isAlive();
            System.out.println("El proceso: " + (isAlive ? "sigue activo" : "ha terminado"));

            
            String lineaInput;
            // Recogemos la SALIDA ESTÁNDAR del proceso hijo            
            // Hay problemas con la codificación:
            // - Lo que yo envío se envía/recibe con UTF-8 por defecto
            // - Lo que el hijo envía lo envía en CP1252/CP850) 
            // Como hay una cadena con dos codificaciones deiferentes, 
            // uaa de las dos se ve mal.
            BufferedReader brInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((lineaInput = brInput.readLine())!=null) {
                System.out.println("stdout(hijo)>> " + lineaInput);
            }
            
            String lineaError;
            // Recogemos la SALIDA ERROR ESTÁNDAR del proceso hijo
            // El encoding CP850 se usa para leer bien los mensajes de la consola
            BufferedReader brError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
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
