package local.redes;

import java.io.DataOutputStream;

// @author Edilson Torres

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static Socket conexao;
    public static ServerSocket servidor;
    public static DataOutputStream saida;
    public static ObjectInputStream entrada;

    public static void main(String[] args) {

        try {
            servidor = new ServerSocket(50000);

            System.out.println("Servidor aguardando conexão...");

            conexao = servidor.accept();

            System.out.println("Cliente conectado: " + conexao.getInetAddress().getHostName());

            entrada = new ObjectInputStream(conexao.getInputStream());

            PessoaModel pessoa = (PessoaModel) entrada.readObject();
            System.out.println("Pessoa recebida: " + pessoa.getNome() + ", " + pessoa.getIdade() + " anos");

            saida = new DataOutputStream(conexao.getOutputStream());

            saida.writeUTF("Pessoa recebida com sucesso!");

            conexao.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}