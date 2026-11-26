import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Random;

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

                Random aleatorio = new Random();
                int numeroSecreto = aleatorio.nextInt(100) + 1;

                int intentos = 0;

                System.out.println("numero secreto generado: " + numeroSecreto);

                String mensaje;

                while ((mensaje = entrada.readLine()) != null) {

                    int numero;

                    // comprobamos que el mensaje sea un numero
                    try {

                        numero = Integer.parseInt(mensaje.trim());

                    } catch (NumberFormatException e) {

                        salida.println("ERR Comando no válido");
                        continue;

                    }

                    intentos++;

                    if (numero < numeroSecreto) {

                        salida.println("MAYOR");

                    } else if (numero > numeroSecreto) {

                        salida.println("MENOR");

                    } else {

                        salida.println("ACIERTO en " + intentos + " intentos");
                        break;

                    }
                }

            }

        } catch (IOException e) {
            System.out.println("error en el servidor: " + e.getMessage());
        }
    }
}