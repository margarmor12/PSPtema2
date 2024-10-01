/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psp.actividades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;

/**
 *
 * @author VicenteMartínez
 */
public class U2A5_LanzadorIpconfig {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int valorSalidaProceso;
        String linea;
        
        try {          
            // Preparamos el entorno de ejecución del proceso
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "ipconfig");
            // Lanzamos el proceso
            Process p = pb.start();

            // Esperamos a que el proceso termine y recogemos su estado de salida
            // 0 --> OK
            // <> 0 --> ERROR
            valorSalidaProceso = p.waitFor();

            // Mostramos la información sobre cómo ha acabado el proceso
            if (valorSalidaProceso != 0) {
                System.out.println("El proceso lanzado ha terminado con errores: " + valorSalidaProceso);
            } else {
                // Si el proceso se ha ejecutado sin errores
                // Leemos la información que el proceso ha escrito en su salida estándar
                BufferedReader salidaProceso = new BufferedReader(new InputStreamReader(p.getInputStream(),"UTF-8"));
                while ((linea = salidaProceso.readLine()) != null) {
                    System.out.println(linea);
                }            
            }
        } catch (InterruptedException | IOException ex) {
            ex.printStackTrace();
            System.out.println("ERROR > " + ex.getLocalizedMessage());
        }
    }
    
}
