/*
 * Este es un programa propio (una clase Java con un main) que hara de proceso hijo
 * Por ser proceso hijo no tiene que cambiar absolutamente nada en su código
 * El programa leerá y escribirá normalmente, de hecho se puede ejecutar 
 * y probar de forma independiente (Shitf + F6)
 */
package psp.u2.ejemplos;

/**
 *
 * @author Vicente Martínez
 */
public class Lanzado {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Mostrar mensaje por la salida ESTÁNDAR
        System.out.println("Mensaje desde el proceso hijo");
        
        // Lectura de información desde los parámetros de entrada 
        // Se supone que recibimos: args[0] --> número y args[1] --> cadena
        int numero = Integer.parseInt(args[0]);
        String cadena = args[1];
        
        // Mostrar mensaje por la salida ESTÁNDAR
        System.out.println("He leído la siguiente información: " + numero + " " + cadena);
        
        // Mostrar mensaje por la salida de ERROR ESTÁNDAR
        System.err.println("Esto podría ser un mensaje de error");
        
        // Mostrar el cuadrado del número por la salida ESTÁNDAR
        System.out.println(numero * numero);        
    }
    
}
