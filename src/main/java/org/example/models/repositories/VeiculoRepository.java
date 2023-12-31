package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VeiculoRepository {
    private MedidaRepository medidaRepository = new MedidaRepository();

    public List<Veiculo> findAll() throws SQLException {
        List<Veiculo> veiculos = new ArrayList<Veiculo>();
        String query = "SELECT * FROM T_PA_VEICULO";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Veiculo veiculo = new Veiculo(
                        rs.getLong("ID_VEICULO"),
                        medidaRepository.find(rs.getLong("ID_MEDIDA")).orElse(null),
                        rs.getLong("APOLICE_VEICULO"),
                        rs.getString("MODELO_VEICULO"),
                        rs.getString("PLACA_VEICULO"),
                        rs.getInt("ANO_VEICULO"),
                        rs.getInt("QTD_EIXOS_VEICULO"),
                        rs.getString("RENAVAN_VEICULO"),
                        rs.getLong("NR_CHASSI"),
                        rs.getString("TP_CHASSI"),
                        rs.getString("TP_EIXO")
                );

                veiculos.add(veiculo);
            }

            return veiculos;
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

    public Optional<Veiculo> find(Long id) throws SQLException {
        String query = "SELECT * FROM T_PA_VEICULO WHERE id_veiculo = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Veiculo veiculo = new Veiculo(
                            rs.getLong("ID_VEICULO"),
                            medidaRepository.find(rs.getLong("ID_MEDIDA")).orElse(null),
                            rs.getLong("APOLICE_VEICULO"),
                            rs.getString("MODELO_VEICULO"),
                            rs.getString("PLACA_VEICULO"),
                            rs.getInt("ANO_VEICULO"),
                            rs.getInt("QTD_EIXOS_VEICULO"),
                            rs.getString("RENAVAN_VEICULO"),
                            rs.getLong("NR_CHASSI"),
                            rs.getString("TP_CHASSI"),
                            rs.getString("TP_EIXO")
                    );

                    return Optional.ofNullable(veiculo);
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

    public void add(Veiculo veiculo) throws SQLException {
        String query = "INSERT INTO T_PA_VEICULO (ID_VEICULO, ID_MEDIDA, APOLICE_VEICULO, MODELO_VEICULO, PLACA_VEICULO, ANO_VEICULO, QTD_EIXOS_VEICULO, RENAVAN_VEICULO, NR_CHASSI, TP_CHASSI, TP_EIXO, DT_CADASTRO, NM_USUARIO) VALUES (SQ_PA_VEICULO.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE, USER)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, veiculo.getMedidaVeiculo().getId());
            ps.setLong(2, veiculo.getApolice());
            ps.setString(3, veiculo.getModelo());
            ps.setString(4, veiculo.getPlaca());
            ps.setInt(5, veiculo.getAnoFabricacao());
            ps.setInt(6, veiculo.getQuantidadeEixos());
            ps.setString(7, veiculo.getRenavan());
            ps.setLong(8, veiculo.getNumChassi());
            ps.setString(9, veiculo.getTipoChassi());
            ps.setString(10, veiculo.getTipoEixo());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(Veiculo veiculo) throws SQLException {
        String query = "UPDATE T_PA_VEICULO SET ID_MEDIDA = ?, APOLICE_VEICULO = ?, MODELO_VEICULO = ?, PLACA_VEICULO = ?, ANO_VEICULO = ?, QTD_EIXOS_VEICULO = ?, RENAVAN_VEICULO = ?, NR_CHASSI = ?, TP_CHASSI = ?, TP_EIXO = ? WHERE ID_VEICULO = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, veiculo.getMedidaVeiculo().getId());
            ps.setLong(2, veiculo.getApolice());
            ps.setString(3, veiculo.getModelo());
            ps.setString(4, veiculo.getPlaca());
            ps.setInt(5, veiculo.getAnoFabricacao());
            ps.setInt(6, veiculo.getQuantidadeEixos());
            ps.setString(7, veiculo.getRenavan());
            ps.setLong(8, veiculo.getNumChassi());
            ps.setString(9, veiculo.getTipoChassi());
            ps.setString(10, veiculo.getTipoEixo());
            ps.setLong(11, veiculo.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM T_PA_VEICULO WHERE ID_VEICULO = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
