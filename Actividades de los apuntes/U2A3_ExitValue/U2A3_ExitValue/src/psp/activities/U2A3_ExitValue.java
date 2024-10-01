/* 
 * Create a new Java application project (package psp.activities & main class U2A3_ExitValue) 
 * Prepare a process to run different commands (notepad, calc, shell commands) one after each other, 
 * and make your application get their exit code. Print it.
 *
 * Commands can be hardcoded. As an optional improvement for this activity you can ask the user 
 * for the command and make your app interactive. There must be an option to exit the app 
 * (empty command for instance).
 *
 * Try with non-existing applications or using wrong arguments/parameters for commands.
 */
package psp.activities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author VicenteMartínez
 */
public class U2A3_ExitValue {

    public static void main(String[] args) {
        do {
            // Código para pedir un programa/comando a ejecutar
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce el programa / comando que quieres ejecutar (intro para acabar): ");
            String comando = teclado.nextLine();
            
            if (comando.equals("")) System.exit(0);

            try {
                // Preparamos el entrono de ejecución del proceso
                // Como no sabemos el contenido del comando, forzamos su conversión
                // a una lista para que no haya problemas con su ejecución
                ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));

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
        } while (true);
    }
}
