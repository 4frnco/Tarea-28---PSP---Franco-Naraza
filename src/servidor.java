import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class servidor {

    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {

            System.out.println("servidor iniciado en el puerto " + PUERTO);

            try (Socket cliente = servidor.accept();
                 BufferedReader entrada = new BufferedReader(
                         new InputStreamReader(cliente.getInputStream(),
                                 StandardCharsets.UTF_8));
                 PrintWriter salida = new PrintWriter(
                         new OutputStreamWriter(cliente.getOutputStream(),
                                 StandardCharsets.UTF_8), true)) {

                System.out.println("cliente conectado: "
                        + cliente.getInetAddress());

                String mensaje = entrada.readLine();

                System.out.println("mensaje recibido: " + mensaje);

                // enviamos una respuesta al cliente
                salida.println("numero recibido: " + mensaje);

            }

        } catch (IOException e) {
            System.out.println("error en el servidor: " + e.getMessage());
        }
    }
}