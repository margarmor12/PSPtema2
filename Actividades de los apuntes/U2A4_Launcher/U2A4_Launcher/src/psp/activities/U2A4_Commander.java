/*
 * Este es un programa propio (una clase Java con un main) que hara de proceso hijo
 * Por ser proceso hijo no tiene que cambiar absolutamente nada en su código
 * El programa leerá y escribirá normalmente, de hecho se puede ejecutar 
 * y probar de forma independiente (Shitf + F6)
 *
 * El proggrama recibe un comando que ejecuta.
 * El código de retorno del programa es el mismo que el edel programa que lanza.
 */

package psp.activities;


/**
 *
 * @author Vicente Martínez
 */
public class U2A4_Commander {

    public static void main(String[] args) throws Exception {       
        // Lectura de información desde los parámetros de entrada 
        // Se supone que recibimos: args[0] args[1] args[2] ..... args[args.length-1 --> comando a ejecutar
        String comando = "";
        for (int i = 0; i < args.length; i++) {
            comando += args[i] + " ";
        }
        comando.trim();
                
        ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));            

        // Lanzamos el proceso hijo
        Process p = pb.start();          

        // Esperamos a que acabe para recoger el valor de salida
        int exitValue = p.waitFor();

        System.exit(exitValue);
    }    
}
