package PacoteJava;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class PessoaDAO {

    private Connection con;

    public PessoaDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String inserirUsuario(PessoaBean pessoa) {
        String sql = "insert into tbusuario(id,nomeUsu,loginUsu,senhaUsu,nivelAcesso)values(?,?,?,?,?)";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);
            
            ps.setInt(1, pessoa.getId());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getUsuario());
            ps.setString(4, pessoa.getSenha());
            ps.setString(5, pessoa.getAcesso());

            if (ps.executeUpdate() > 0) {
                return "Usuário cadastrado com sucesso!!!";
            } else {
                return "Erro ao cadastrar!!!";
            }

        } catch (SQLException e) {
            return e.getMessage();

        }
    }

    public String alterarPessoa(PessoaBean pessoa) {
        String sql = "update tbusuario set nomeUsu = ?,loginUsu = ?, senhaUsu = ?, nivelAcesso = ? where id = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setInt(5, pessoa.getId());
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getUsuario());
            ps.setString(3, pessoa.getSenha());
            ps.setString(4, pessoa.getAcesso());
            

            if (ps.executeUpdate() > 0) {

                return "Alterado com sucesso!!!";

            } else {
                return "Erro ao alterar!!!";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public String deletarPessoa(PessoaBean pessoa) {
        String sql = "delete from tbusuario where id = ?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setInt(1, pessoa.getId());

            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso!!!";
            } else {
                return "Erro ao excluir!!!";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public List<PessoaBean> listarTodos() {
        String sql = "select * from tbusuario";

        List<PessoaBean> listaPessoa = new ArrayList<PessoaBean>();

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            

            if (rs != null) {
                while (rs.next()) {
                    PessoaBean pb = new PessoaBean();
                    pb.setId(rs.getInt(1));
                    pb.setNome(rs.getString(2));
                    pb.setUsuario(rs.getString(3));
                    pb.setSenha(rs.getString(4));
                    pb.setAcesso(rs.getString(5));
                    listaPessoa.add(pb);
                }
                return listaPessoa;
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
    }
    
    public boolean verificarLogin(String loginUsu, String senhaUsu){
        boolean logado = false;
        String sql ="select * from tbusuario where loginUsu = ? and senhaUsu = ?";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, loginUsu);
            ps.setString(2, senhaUsu);
            
            
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                logado = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return logado;
        
    }
    
    public boolean verificarAcesso(String loginUsu, String nivelAcesso){
        boolean acesso = false;
        String sql = "select * from tbusuario where loginUsu = ? and nivelAcesso = 'Usuário'";
        
        try{
            PreparedStatement ps = getCon().prepareStatement(sql);
            ps.setString(1, loginUsu);
            
            
            ResultSet rs = ps.executeQuery();
                
            if(rs.next()){
                acesso = true;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return acesso;
        
    }

}
