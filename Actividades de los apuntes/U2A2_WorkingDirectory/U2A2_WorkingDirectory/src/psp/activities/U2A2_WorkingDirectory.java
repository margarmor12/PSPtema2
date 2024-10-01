/* Activity psp.activities.U2A2_WorkingDirectory
 *
 * Create a new Java application project (package psp.activities & main class U2A2_WorkingDirectory) Using the command line, ask the user for the action he wants to do with the computer (shutdown ,restart or suspend) and how much time he needs before shutting down the system.
 *
 * Find information about the shutdown command in GNU/Linux and make your app work in both systems.
 * Your app has to prepare the right command for the answers the user has given and for the OS it is running on.
 *
 * Get the ProcessBuilder.command() result and show it on the console in a readable format.
 */
package psp.activities;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author VicenteMartínez
 */
public class U2A2_WorkingDirectory {

    public static void main(String[] args) throws IOException, InterruptedException {
        // Prepare the command
        String command;
        if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
            command = "cmd /c dir";
        } else {
            command = "sh -c ls";
        }
               
        //1st - Default working directory
        
        // Prepare the process 
        ProcessBuilder commander = new ProcessBuilder(command.split("\\s"));       
        commander.inheritIO();
        
        // Show Process and System properties
        System.out.println("Working directory: " + commander.directory());
        System.out.println("user.dir variable: " + System.getProperty("user.dir"));
        
        // Launch the process and show its result
        // Working directory is null but the process is run on the current dir
        Process process = commander.start();
        process.waitFor();
        
        ProcessHandle childProcessHandle = process.toHandle();
        ProcessHandle.Info childprocessInfo = childProcessHandle.info();

        
        
        //2nd - Change user.dir but not the working directory
        
        // Change the user.dir system property
        System.setProperty("user.dir", System.getProperty("user.home"));
        
        // Prepare the process
        commander = new ProcessBuilder(command.split("\\s"));       
        commander.inheritIO();
        
        // Show Process and System properties
        System.out.println("Working directory: " + commander.directory());
        System.out.println("user.dir variable: " + System.getProperty("user.dir"));
        
        // Launch the process and show its result
        // Working directory is null but the process is run on the current dir        
        commander.start().waitFor();
        
        
        // 3rd - Change the working directory
        
        // Prepare the process
        commander = new ProcessBuilder(command.split("\\s"));       
        commander.inheritIO();
        
        // Show Process and System properties
        commander.directory(new File(System.getProperty("user.home")));             
        System.out.println("Working directory: " + commander.directory());
        System.out.println("user.dir variable: " + System.getProperty("user.dir"));

        // Launch the process and show its result
        // Working directory is user.home and the process is run on it
        commander.start().waitFor();        
    }
}
