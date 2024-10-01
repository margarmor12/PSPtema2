/* Activity psp.activities.U2A1_Shutdowner
 *
 * Create a new Java application project (package psp.activities & main class U2A1_Shutdowner) Using the command line, ask the user for the action he wants to do with the computer (shutdown ,restart or suspend) and how much time he needs before shutting down the system.
 *
 * Find information about the shutdown command in GNU/Linux and make your app work in both systems.
 * Your app has to prepare the right command for the answers the user has given and for the OS it is running on.
 *
 * Get the ProcessBuilder.command() result and show it on the console in a readable format.
 */
package psp.activities;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author VicenteMartínez
 */
public class U2A1_Shutdowner {

    public static void main(String[] args) throws IOException {
        // Ask for the required information to prepare the command
        Scanner keyboard = new Scanner(System.in);
        
        System.out.print("Select your option (s-shutdown / r-reboot / h-hibernate): ");
        String shutdownOption = keyboard.nextLine();        
        
        System.out.print("How much seconds will the command wait to be run? (0 means inmediately): ");
        String shutdownTime = keyboard.nextLine();        
        
        // Prepare the command
        String command;
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            command = "C:/Windows/System32/shutdown -" + shutdownOption + " -t " + shutdownTime;
        } else {
            command = "shutdown -" + shutdownOption + " -t " + shutdownTime;
        }
        
        // Prepare the process and launch it
        ProcessBuilder shutdowner = new ProcessBuilder(command.split("\\s"));
        //shutdowner.start();
        
        // Show the command to be run
        System.out.print("El comando a ejecutar es:  ");
        for (String commandPart: shutdowner.command()) {
            System.out.print(commandPart + " ");
        }
        System.out.println("");
    }    
}
