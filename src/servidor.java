import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidor {

    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {

            System.out.println("servidor iniciado en el puerto " + PUERTO);

            try (Socket cliente = servidor.accept()) {

                System.out.println("cliente conectado: "
                        + cliente.getInetAddress());

            }

        } catch (IOException e) {
            System.out.println("error en el servidor: " + e.getMessage());
        }
    }
}