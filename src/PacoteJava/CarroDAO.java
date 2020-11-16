package PacoteJava;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarroDAO {

    private Connection con;

    public CarroDAO(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String inserirCarro(CarroBean carro) {
        String sql = "insert into tbveiculo(placa,cor,descricao)values(?,?,?)";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, carro.getPlaca());
            ps.setString(2, carro.getCor());
            ps.setString(3, carro.getDescricao());

            if (ps.executeUpdate() > 0) {
                return "Inserido com sucesso!!!";
            } else {
                return "Erro ao inserir!!!";
            }

        } catch (SQLException e) {
            return e.getMessage();

        }
    }

    public String alterarCarro(CarroBean carro) {
        String sql = "update tbveiculo set cor = ?,descricao = ? where placa = ?";

        try {

            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, carro.getCor());
            ps.setString(2, carro.getDescricao());
            ps.setString(3, carro.getPlaca());

            if (ps.executeUpdate() > 0) {

                return "Alterado com sucesso!!!";

            } else {
                return "Erro ao alterar!!!";
            }
        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public String deletarCarro(CarroBean carro) {
        String sql = "delete from tbveiculo where placa = ?";

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ps.setString(1, carro.getPlaca());

            if (ps.executeUpdate() > 0) {
                return "Excluido com sucesso!!!";
            } else {
                return "Erro ao escluir!!!";
            }

        } catch (SQLException ex) {
            return ex.getMessage();
        }
    }

    public List<CarroBean> listarTodos() {
        String sql = "select * from tbveiculo";

        List<CarroBean> listaCarro = new ArrayList<CarroBean>();

        try {
            PreparedStatement ps = getCon().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CarroBean cb = new CarroBean();
                    cb.setPlaca(rs.getString(1));
                    cb.setCor(rs.getString(2));
                    cb.setDescricao(rs.getString(3));
                    listaCarro.add(cb);
                }
                return listaCarro;
            } else {
                return null;
            }
        }catch(SQLException e){
            return null;
        }
    }
}
