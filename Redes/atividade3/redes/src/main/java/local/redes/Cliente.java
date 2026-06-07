package local.redes;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// @author Edilson Torres

public class Cliente {

    public String enviarPessoa(PessoaModel pessoa) {
        try {
            Socket conexao = new Socket("localhost", 50000);
            ObjectOutputStream saida = new ObjectOutputStream(conexao.getOutputStream());
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());

            saida.writeObject(pessoa);

            String resposta = entrada.readUTF();

            conexao.close();

            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao conectar com o servidor: " + e.getMessage();
        }
    }
}
