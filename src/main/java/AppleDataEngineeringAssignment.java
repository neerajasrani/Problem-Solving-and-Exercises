import javax.net.ssl.SSLServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Neeraj
 * This class will -
 * 1. Create server socket
2. Read data from csv
3. Load data from csv to memory, join by id
4. Accept input id from cient socket
5. Return o/p
6. Accept Special Command to stop server socket
 */

public class AppleDataEngineeringAssignment {
  public static void main(String[] args) throws Exception {
    // Start server socket
    int portNumber = 4444; // @ TODO Read from property file

    try (
        ServerSocket serverSocket = new ServerSocket(portNumber);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out =
            new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(clientSocket.getInputStream()));
    ) {
  }

}
