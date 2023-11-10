package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Medida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedidaRepository {
    public List<Medida> findAll() throws SQLException {
        List<Medida> medidas = new ArrayList<Medida>();
        String query = "SELECT * FROM T_PA_MEDIDA";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Medida medida = new Medida(
                        rs.getLong("ID_MEDIDA"),
                        rs.getDouble("ALTURA"),
                        rs.getDouble("LARGURA"),
                        rs.getDouble("COMPRIMENTO"),
                        rs.getDouble("PESO"),
                        rs.getDouble("PESO_SUPORTADO")
                );

                medidas.add(medida);
            }

            return medidas;
        }
        catch (SQLException e) {
            if(e.getErrorCode() == 1017) {
                throw new SQLException("Falha de autenticação ao conectar ao banco de dados.", e);
            } else if(e.getErrorCode() == 904) {
                throw new SQLException("A query contém uma coluna inválida.", e);
            } else {
                throw new SQLException("Erro ao executar a query.", e);
            }
        }
    }

    public Optional<Medida> find(Long id) throws SQLException {
        String query = "SELECT * FROM T_PA_MEDIDA WHERE ID_MEDIDA = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Medida medida = new Medida(
                            rs.getLong("ID_MEDIDA"),
                            rs.getDouble("ALTURA"),
                            rs.getDouble("LARGURA"),
                            rs.getDouble("COMPRIMENTO"),
                            rs.getDouble("PESO"),
                            rs.getDouble("PESO_SUPORTADO")
                    );

                    return Optional.ofNullable(medida);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        catch (SQLException e) {
            if(e.getErrorCode() == 1017) { // Erro de login/senha inválido
                throw new SQLException("Falha de autenticação ao conectar ao banco de dados.", e);
            } else if(e.getErrorCode() == 904) { // Erro de coluna inválida
                throw new SQLException("A query contém uma coluna inválida.", e);
            } else {
                throw new SQLException("Erro ao executar a query.", e);
            }
        }
        return Optional.empty();
    }

    public void add(Medida medida) throws SQLException {
        String query = "INSERT INTO T_PA_MEDIDA (ID_MEDIDA, ALTURA, LARGURA, COMPRIMENTO, PESO, PESO_SUPORTADO, DT_CADASTRO, NM_USUARIO) VALUES (?, ?, ?, ?, ?, ?, SYSDATE, USER)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, medida.getId());
            ps.setDouble(2, medida.getAltura());
            ps.setDouble(3, medida.getLargura());
            ps.setDouble(4, medida.getComprimento());
            ps.setDouble(5, medida.getPesoVeiculo());
            ps.setDouble(6, medida.getPesoSuportadoModal());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(Medida medida) throws SQLException {
        String query = "UPDATE T_PA_MEDIDA SET ALTURA = ?, LARGURA = ?, COMPRIMENTO = ?, PESO = ?, PESO_SUPORTADO = ? WHERE ID_MEDIDA = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setDouble(1, medida.getAltura());
            ps.setDouble(2, medida.getLargura());
            ps.setDouble(3, medida.getComprimento());
            ps.setDouble(4, medida.getPesoVeiculo());
            ps.setDouble(5, medida.getPesoSuportadoModal());
            ps.setLong(6, medida.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM T_PA_MEDIDA WHERE ID_MEDIDA = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
