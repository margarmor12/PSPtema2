/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp.actividades;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;

/**
 *
 * @author VicenteMartínez
 */
public class U2A3_LanzadorEjecutablesSimple {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Process> procesos = new ArrayList<>();
        
        try {
            // En las propiedades del proyecto he puesto como ARGUMENTOS: mspaint notepad SnippingTool
            // Ese argumento se recoge en el array args[]
            if (args.length == 0) System.exit(-1);
            
            for (String nombreProceso: args) {
                // Preparamos el entorno de ejecución del proceso
                ProcessBuilder pb = new ProcessBuilder(nombreProceso);
                // Lanzamos el proceso
                Process p = pb.start();
                
                // Añadimos el proceso a la lista
                procesos.add(p);
            }
            // Esperamos a que TODOS los procesos terminen
            // No controlamos el orden de finalización. Los esperamos en orden de lanzamiento.
            for (Process p: procesos) {
                p.waitFor();
            }

            System.out.println("Aplicaciones finalizadas");
            
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR > " + ex.getLocalizedMessage());
        }
    }
    
}
