import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PUERTO);
             Scanner teclado = new Scanner(System.in);
             PrintWriter salida = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(),
                             StandardCharsets.UTF_8), true)) {

            System.out.println("conectado al servidor");

            System.out.print("introduce un numero del 1 al 100: ");
            String mensaje = teclado.nextLine();

            // enviamos el mensaje al servidor
            salida.println(mensaje);

            System.out.println("mensaje enviado: " + mensaje);

        } catch (IOException e) {
            System.out.println("error en el cliente: " + e.getMessage());
        }
    }
}