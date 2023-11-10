package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.ModalColab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ModalColabRepository {
    private ModalRepository modalRepository = new ModalRepository();
    private ColaboradorRepository colaboradorRepository = new ColaboradorRepository();

    public List<ModalColab> findAll() throws SQLException {
        List<ModalColab> modaisColabs = new ArrayList<ModalColab>();
        String query = "SELECT * FROM T_PA_MODAL_COLAB";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                ModalColab modalColab = new ModalColab(
                        rs.getLong("ID_MODAL_COLAB"),
                        modalRepository.find(rs.getLong("ID_MODAL")).orElse(null),
                        colaboradorRepository.find(rs.getLong("ID_COLABORADOR")).orElse(null)
                );

                modaisColabs.add(modalColab);
            }

            return modaisColabs;
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

    public Optional<ModalColab> find(Long id) throws SQLException {
        String query = "SELECT * FROM T_PA_MODAL_COLAB WHERE ID_MODAL_COLAB = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    ModalColab modalColab = new ModalColab(
                            rs.getLong("ID_MODAL_COLAB"),
                            modalRepository.find(rs.getLong("ID_MODAL")).orElse(null),
                            colaboradorRepository.find(rs.getLong("ID_COLABORADOR")).orElse(null)
                    );

                    return Optional.ofNullable(modalColab);
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

    public void add(ModalColab modalColab) throws SQLException {
        String query = "INSERT INTO T_PA_MODAL_COLAB (ID_MODAL_COLAB, ID_COLABORADOR, ID_MODAL) VALUES (SQ_PA_MODAL_COLAB.nextval, ?, ?)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, modalColab.getColaborador().getId());
            ps.setLong(2, modalColab.getModal().getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(ModalColab modalColab) throws SQLException {
        String query = "UPDATE T_PA_MODAL_COLAB SET ID_MODAL = ?, ID_COLABORADOR = ? WHERE ID_MODAL_COLAB = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, modalColab.getModal().getId());
            ps.setLong(2, modalColab.getColaborador().getId());
            ps.setLong(3, modalColab.getId());


            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM T_PA_MODAL_COLAB WHERE ID_MODAL_COLAB = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
