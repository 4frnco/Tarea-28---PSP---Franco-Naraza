import java.io.IOException;
import java.net.Socket;

public class cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        // nos conectamos al servidor
        try (Socket socket = new Socket(HOST, PUERTO)) {

            System.out.println("conectado al servidor");

        } catch (IOException e) {
            System.out.println("error en el cliente: " + e.getMessage());
        }
    }
}