package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Modal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModalRepository {
    private MedidaRepository medidaRepository = new MedidaRepository();

    public List<Modal> findAll() throws SQLException {
        List<Modal> modais = new ArrayList<Modal>();
        String query = "SELECT * FROM T_PA_MODAL";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Modal modal = new Modal(
                        rs.getLong("ID_MODAL"),
                        medidaRepository.find(rs.getLong("ID_MEDIDA")).orElse(null),
                        rs.getString("MODELO_MODAL"),
                        rs.getString("PLACA_MODAL"),
                        rs.getString("MARCA_MODAL"),
                        rs.getInt("ANO_MODAL"),
                        rs.getString("TP_MODAL")
                );

                modais.add(modal);
            }

            return modais;
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

    public Optional<Modal> find(Long id) throws SQLException {
        String query = "SELECT * FROM T_PA_MODAL WHERE ID_MODAL = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Modal modal = new Modal(
                            rs.getLong("ID_MODAL"),
                            medidaRepository.find(rs.getLong("ID_MEDIDA")).orElse(null),
                            rs.getString("MODELO_MODAL"),
                            rs.getString("PLACA_MODAL"),
                            rs.getString("MARCA_MODAL"),
                            rs.getInt("ANO_MODAL"),
                            rs.getString("TP_MODAL")
                    );

                    return Optional.ofNullable(modal);
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

    public void add(Modal modal) throws SQLException {
        String query = "INSERT INTO T_PA_MODAL (ID_MODAL, ID_MEDIDA, MODELO_MODAL, PLACA_MODAL, MARCA_MODAL, ANO_MODAL, TP_MODAL, DT_CADASTRO, NM_USUARIO) VALUES (SQ_PA_MODAL.nextval, ?, ?, ?, ?, ?, ?, SYSDATE, USER)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, modal.getMedidaModal().getId());
            ps.setString(2, modal.getModelo());
            ps.setString(3, modal.getPlaca());
            ps.setString(4, modal.getMarca());
            ps.setInt(5, modal.getAnoFabricacao());
            ps.setString(6, modal.getTipoModal());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(Modal modal) throws SQLException {
        String query = "UPDATE T_PA_MODAL SET ID_MEDIDA = ?, MODELO_MODAL = ?, PLACA_MODAL = ?, MARCA_MODAL = ?, ANO_MODAL = ?, TP_MODAL = ? WHERE ID_MODAL = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, modal.getMedidaModal().getId());
            ps.setString(2, modal.getModelo());
            ps.setString(3, modal.getPlaca());
            ps.setString(4, modal.getMarca());
            ps.setInt(5, modal.getAnoFabricacao());
            ps.setString(6, modal.getTipoModal());
            ps.setLong(7, modal.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM T_PA_MODAL WHERE ID_MODAL = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
