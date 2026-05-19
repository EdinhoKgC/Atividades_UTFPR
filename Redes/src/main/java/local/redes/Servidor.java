// @author Edilson Torres
package local.redes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static ServerSocket server;
    public static Socket socket;

    public static DataInputStream entrada;
    public static DataOutputStream saida;

    public static boolean cpfValido(String cpf) {
        if (cpf == null) {
            return false;
        }

        cpf = cpf.replace(".", "").replace("-", "").trim();

        if (cpf.length() != 11) {
            return false;
        }

        if (!cpf.matches("\\d{11}")) {
            return false;
        }

        if (cpf.equals("00000000000")) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }

        int resto = soma % 11;
        int primeiroDigito = (resto < 2) ? 0 : 11 - resto;

        soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        soma += primeiroDigito * 2;

        resto = soma % 11;
        int segundoDigito = (resto < 2) ? 0 : 11 - resto;

        return primeiroDigito == Character.getNumericValue(cpf.charAt(9))
                && segundoDigito == Character.getNumericValue(cpf.charAt(10));
    }

    public static void main(String[] args) {
        try {
            server = new ServerSocket(50000);
            System.out.println("Servidor aguardando conexao na porta 50000...");

            socket = server.accept();
            System.out.println("Cliente conectado.");

            entrada = new DataInputStream(socket.getInputStream());
            saida = new DataOutputStream(socket.getOutputStream());

            String cpf = entrada.readUTF();
            System.out.println("CPF recebido: " + cpf);

            boolean cpfIsValid = cpfValido(cpf);

            if (cpfIsValid) {
                System.out.println("Este CPF é válido.");
                saida.writeUTF("Este CPF é válido.");
            } else {
                System.out.println("Este CPF é inválido.");
                saida.writeUTF("Este CPF é inválido.");
            }
        } catch (Exception e) {
            System.out.println("Erro ao abrir socket no servidor: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) entrada.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar entrada.");
            }

            try {
                if (saida != null) saida.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar saida.");
            }

            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
                System.out.println("Erro ao finalizar socket no servidor.");
            }

            try {
                if (server != null) server.close();
            } catch (Exception e) {
                System.out.println("Erro ao fechar servidor.");
            }
        }
    }
}