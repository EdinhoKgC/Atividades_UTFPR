package local.redes;

// @author Edilson Torres

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PessoaForm extends JFrame {

    private JTextField txtNome;
    private JTextField txtIdade;
    private JTextArea txtRetorno;
    private JButton btnEnviar;

    public PessoaForm() {
        setTitle("Cadastro de Pessoa");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;

        painel.add(new JLabel("Nome"), gbc);

        gbc.gridy++;
        txtNome = new JTextField();
        painel.add(txtNome, gbc);

        gbc.gridy++;
        painel.add(new JLabel("Idade"), gbc);

        gbc.gridy++;
        txtIdade = new JTextField();
        painel.add(txtIdade, gbc);

        gbc.gridy++;
        painel.add(new JLabel("Retorno do Servidor"), gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        txtRetorno = new JTextArea(6, 20);
        txtRetorno.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtRetorno);
        painel.add(scroll, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.EAST;
        btnEnviar = new JButton("Enviar");
        painel.add(btnEnviar, gbc);

        btnEnviar.addActionListener((ActionEvent e) -> enviarDados());

        add(painel);
    }

    private void enviarDados() {
        try {
            String nome = txtNome.getText().trim();
            String idadeTexto = txtIdade.getText().trim();

            if (nome.isEmpty() || idadeTexto.isEmpty()) {
                txtRetorno.setText("Preencha nome e idade.");
                return;
            }

            int idade = Integer.parseInt(idadeTexto);

            PessoaModel pessoa = new PessoaModel(nome, idade);
            Cliente cliente = new Cliente();

            String resposta = cliente.enviarPessoa(pessoa);

            txtRetorno.setText(resposta);

        } catch (NumberFormatException ex) {
            txtRetorno.setText("Idade inválida. Digite apenas números.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PessoaForm().setVisible(true));
    }
}