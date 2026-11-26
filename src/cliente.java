import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PUERTO);
             Scanner teclado = new Scanner(System.in)) {

            System.out.println("conectado al servidor");

            // pedimos un numero al usuario
            System.out.print("introduce un numero del 1 al 100: ");
            String mensaje = teclado.nextLine();

            System.out.println("numero introducido: " + mensaje);

        } catch (IOException e) {
            System.out.println("error en el cliente: " + e.getMessage());
        }
    }
}