import java.io.IOException;
import java.net.ServerSocket;

public class servidor {

    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        // creamos el socket del servidor
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {

            System.out.println("servidor iniciado en el puerto " + PUERTO);

        } catch (IOException e) {
            System.out.println("eror en el servidor: " + e.getMessage());
        }
    }
}