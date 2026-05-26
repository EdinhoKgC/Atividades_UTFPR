// @author Edilson Torres

package local.redes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

    public static Socket socket;
    public static DataInputStream entrada;
    public static DataOutputStream saida;

    public static void main(String[] args) {

        try {

            socket = new Socket("127.0.0.1", 50000);

            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Digite um cpf válido: ");
            String cpf = br.readLine();

            saida.writeUTF(cpf);

            String msg = entrada.readUTF();

            System.out.println(msg);

        }
        catch (Exception e) {
            System.out.println("Erro ao abrir socket  no cliente");
        }

        finally {
            try {
                socket.close();
            }
            catch (Exception e) {
                System.out.println("Erro ao tentar fechar socket");
            }

        }

    }
}
