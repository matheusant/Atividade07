//Classe utilizada para testar a aplicação
package PacoteJava;

import java.sql.*;
import java.util.*;

public class TestePessoa {

    public static void main(String[] args) {

        Connection con = Conexao.abrirConexao();

        //Instanciando o CarroBean e CarroDAO
        PessoaBean pb = new PessoaBean();
        PessoaDAO pd = new PessoaDAO(con);

        //Inserindo valores na tabela
        /*pb.setNome("Matheus");
        pb.setUsuario("theus");
        pb.setSenha("12345");
        pb.setAcesso("Admin");*/

        //Executar o comando de insert
        //System.out.println(pd.inserirUsuario(pb));
        //Excluir um carro
        pb.setId(1);
        System.out.println(pd.deletarPessoa(pb));
        //Pesquisando os carros registrados no banco de dados
        List<PessoaBean> lista = pd.listarTodos();

        if (lista != null) {

            for (PessoaBean pessoa : lista) {
                System.out.println("ID: " + pessoa.getId());
                System.out.println("Nome: " + pessoa.getNome());
                System.out.println("Usuário: " + pessoa.getUsuario());
                System.out.println("Senha: " + pessoa.getSenha());
                System.out.println("Nivel de acesso: " + pessoa.getAcesso());
                System.out.println("");

            }
        }
    }

}

