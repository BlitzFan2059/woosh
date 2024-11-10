package isupportkazakhstan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Executors;
import javax.swing.SwingUtilities;
public class __gro {

	public static void main(String[] args) throws IOException {

	Scanner s = new Scanner(System.in);
	System.out.print("IP: ");
	String ip = s.nextLine();
	System.out.print("threads: ");
	int threads = s.nextInt();
	System.out.print("packet size: ");
	int packetsize = s.nextInt();
	s.nextLine();
	System.out.print("Options -t something something etc leave empty if none: ");
	String o = s.nextLine();

	try {
		 ExecutorService executorservice = Executors.newFixedThreadPool(threads);
	for (int i = 0; i < threads; i++) {
           final int pp = i + 1;
           
           executorservice.submit(() -> {
               try {
               	String cmd = "ping " + ip + " -l " + packetsize + " " + o;
                   ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd);
                   builder.redirectErrorStream(true);
                   Process p = builder.start();
                   BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                   String line;
                   System.out.println("Pinging " +ip +" packetsize: " + packetsize + " options:" +o) ;
                  
                   while ((line = reader.readLine()) != null) {
                       String outz = line;  
                       SwingUtilities.invokeLater(() -> System.out.append(outz + "\n"));
                   }
                   p.waitFor();
               } catch (IOException | InterruptedException e1) {
                   e1.printStackTrace();
               }
           });
       }

       executorservice.shutdown();
    
   } catch (NumberFormatException ex) {
       ex.printStackTrace();
   }
	} }


