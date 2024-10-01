/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp.actividades;

import java.io.IOException;
import java.io.InterruptedIOException;

/**
 *
 * @author VicenteMartínez
 */
public class U2A2_LanzadorEjecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int valorSalidaProceso;
        
        try {
            // En las propiedades del proyecto he puesto como ARGUMENTO: mspaint
            // Ese argumento se recoge en el array args[]
            if (args.length == 0) System.exit(-1);
            
            // Preparamos el entorno de ejecución del proceso
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", args[0]);
            // Lanzamos el proceso
            Process p = pb.start();

            // Esperamos a que el proceso termine y recogemos su estado de salida
            // 0 --> OK
            // <> 0 --> ERROR
            valorSalidaProceso = p.waitFor();

            // Mostramos la información sobre cómo ha acabado el proceso
            if (valorSalidaProceso == 0) {
                System.out.println("El proceso lanzado ha terminado correctamente: " + valorSalidaProceso);
            } else {
                System.out.println("El proceso lanzado ha terminado con errores: " + valorSalidaProceso);
            }
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR > " + ex.getLocalizedMessage());
        }
    }
    
}
