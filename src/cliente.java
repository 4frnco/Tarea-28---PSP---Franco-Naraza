import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class cliente {

    private static final String HOST = "localhost";
    private static final int PUERTO = 6666;

    public static void main(String[] args) {

        try (Socket socket = new Socket(HOST, PUERTO);
             Scanner teclado = new Scanner(System.in);
             BufferedReader entrada = new BufferedReader(
                     new InputStreamReader(socket.getInputStream(),
                             StandardCharsets.UTF_8));
             PrintWriter salida = new PrintWriter(
                     new OutputStreamWriter(socket.getOutputStream(),
                             StandardCharsets.UTF_8), true)) {

            System.out.println("conectado al servidor");
            System.out.println("adivina el numero del 1 al 100");
            System.out.println("escribe ADIOS para salir");

            // mantenemos la conexion durante la partida
            while (true) {

                System.out.print("introduce un numero: ");

                if (!teclado.hasNextLine()) {
                    break;
                }

                String mensaje = teclado.nextLine();

                // enviamos el mensaje al servidor
                salida.println(mensaje);

                // esperamos la respuesta del servidor
                String respuesta = entrada.readLine();

                if (respuesta == null) {

                    System.out.println("conexion cerrada por el servidor");
                    break;

                }

                System.out.println("servidor: " + respuesta);

                // comprobamos si ha terminado la partida
                if (respuesta.startsWith("ACIERTO")
                        || respuesta.equals("OK Hasta luego")) {

                    break;

                }
            }

        } catch (ConnectException e) {

            System.out.println("Servidor no disponible");

        } catch (SocketException e) {

            System.out.println("conexion interrumpida");

        } catch (IOException e) {

            System.out.println("error en el cliente: " + e.getMessage());

        }
    }
}