/*
 * Este es un programa propio (una clase Java con un main) que hara de proceso hijo
 * Por ser proceso hijo no tiene que cambiar absolutamente nada en su código
 * El programa leerá y escribirá normalmente, de hecho se puede ejecutar 
 * y probar de forma independiente (Shitf + F6)
 */
package psp.u2.ejemplos;

import java.util.Scanner;

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
        
        // Lectura de información desde la entrada ESTÄNDAR
        Scanner sc = new Scanner (System.in);
        // Se supone que escribimos: número cadena --> todo en la misma línea
        int numero = sc.nextInt();
        // Como el entero y la cadena están en líneas diferentes, dentro del 
        // fichero entrada.txt, hay que hacer sc.nextLine(); para consumir 
        // el \n del final de la línea o bien 
        // int numero = Integer.parseInt(sc.nextLine());
        sc.nextLine();
        String cadena = sc.nextLine();
        
        // Mostrar mensaje por la salida ESTÁNDAR
        System.out.println("He leído la siguiente información: " + numero + " " + cadena);
        
        // Mostrar mensaje por la salida de ERROR ESTÁNDAR
        System.err.println("Esto podría ser un mensaje de error");
        
        // Mostrar el cuadrado del número por la salida ESTÁNDAR
        System.out.println(numero * numero);        
    }
    
}
