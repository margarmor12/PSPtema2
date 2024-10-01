/* 
 * Activity U2A4_Launcher
 *
 * Create a new Java application project (package psp.activities & main class U2A4_Launcher).
 * Into the project create another class, U2A4_Commander with a main method that receives a program name 
 * as a unique parameter in the main. Make this application to create and run a process for the program 
 * and wait until that process has finished.
 * 
 * This class will return always the same value the launched program did.
 * 
 * System.exit() method
 * 
 * Zero. The zero status code should be used when the program execution went fine, i.e., the program is terminated successfully.
 * Non-Zero. A nonzero status code indicates abnormal termination. Java allows us to use different values for different kinds of errors.
 * Now, make the U2A4_Launcher class ask the user for an application name and launch the Commander class passing it the name of the application entered by the user.
 * 
 * Get the exitValue from Commander and show it's value, telling if the process worked fine or if it failed. 
 */
package psp.activities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author VicenteMartínez
 */
public class U2A4_Launcher {

    public static void main(String[] args) {

        // Código para pedir un programa/comando a ejecutar
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el programa / comando que quieres ejecutar: ");
        String comando = teclado.nextLine();

        try {
            // Preparamos el entrono de ejecución del proceso
            // Como no sabemos el contenido del comando, forzamos su conversión
            // a una lista para que no haya problemas con su ejecución
            comando = "java psp.activities.U2A4_Commander " + comando;
            ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));
            pb.directory(new File("build/classes"));

            // Lanzamos el proceso hijo
            Process p = pb.start();          

            // Esperamos a que acabe para recoger el valor de salida
            int exitValue = p.waitFor();

            if (exitValue == 0) {
                System.out.println("El comando " + pb.command().toString() + " ha finalizado bien");
            } else {
                System.out.println("El comando " + pb.command().toString() + " ha finalizado con errores. Código (" + exitValue + ")");                
            }
        } catch (InterruptedException | IOException ex) {
            System.err.println(ex.getLocalizedMessage());
            ex.printStackTrace();
        }                       
    }
}
