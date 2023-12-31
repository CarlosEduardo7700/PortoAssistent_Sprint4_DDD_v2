package org.example.models.repositories;

import org.example.infrastructure.database.DataBaseFactory;
import org.example.models.Cliente;
import org.example.models.Colaborador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ColaboradorRepository {

    public List<Colaborador> findAll() throws SQLException {
        List<Colaborador> colaboradores = new ArrayList<Colaborador>();
        String query = "SELECT * FROM T_PA_COLABORADOR";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while(rs.next()){
                Colaborador colaborador = new Colaborador(
                        rs.getLong("ID_COLAB"),
                        rs.getString("NM_COLAB"),
                        rs.getString("CPF_COLAB"),
                        rs.getString("GENERO_COLAB"),
                        rs.getLong("TEL_COLAB"),
                        rs.getString("DT_NASC_COLAB"),
                        rs.getString("ENDERECO_COLAB"),
                        rs.getString("EMAIL_COLAB"),
                        rs.getString("SENHA_COLAB")
                );

                colaboradores.add(colaborador);
            }

            return colaboradores;
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

    public Optional<Colaborador> find(Long id) throws SQLException {
        String query = "SELECT * FROM T_PA_COLABORADOR WHERE ID_COLAB = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setLong(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Colaborador colaborador = new Colaborador(
                            rs.getLong("ID_COLAB"),
                            rs.getString("NM_COLAB"),
                            rs.getString("CPF_COLAB"),
                            rs.getString("GENERO_COLAB"),
                            rs.getLong("TEL_COLAB"),
                            rs.getString("DT_NASC_COLAB"),
                            rs.getString("ENDERECO_COLAB"),
                            rs.getString("EMAIL_COLAB"),
                            rs.getString("SENHA_COLAB")
                    );

                    return Optional.ofNullable(colaborador);
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

    public Optional<Colaborador> findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM T_PA_COLABORADOR WHERE EMAIL_COLAB = ?";

        try(Connection connection = DataBaseFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement(query)){

            ps.setString(1, email);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    Colaborador colaborador = new Colaborador(
                            rs.getLong("ID_COLAB"),
                            rs.getString("NM_COLAB"),
                            rs.getString("CPF_COLAB"),
                            rs.getString("GENERO_COLAB"),
                            rs.getLong("TEL_COLAB"),
                            rs.getString("DT_NASC_COLAB"),
                            rs.getString("ENDERECO_COLAB"),
                            rs.getString("EMAIL_COLAB"),
                            rs.getString("SENHA_COLAB")
                    );

                    return Optional.ofNullable(colaborador);
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

    public void add(Colaborador colaborador) throws SQLException {
        String query = "INSERT INTO T_PA_COLABORADOR (ID_COLAB, NM_COLAB, CPF_COLAB, GENERO_COLAB, TEL_COLAB, DT_NASC_COLAB, ENDERECO_COLAB, EMAIL_COLAB, SENHA_COLAB, DT_CADASTRO, NM_USUARIO) VALUES (SQ_PA_COLABORADOR.nextval, ?, ?, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, SYSDATE, USER)";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, colaborador.getNome());
            ps.setString(2, colaborador.getCpf());
            ps.setString(3, colaborador.getGenero());
            ps.setLong(4, colaborador.getTelefone());
            ps.setString(5, colaborador.getDataNascimento());
            ps.setString(6, colaborador.getEndereco());
            ps.setString(7, colaborador.getEmail());
            ps.setString(8, colaborador.getSenha());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void update(Colaborador colaborador) throws SQLException {
        String query = "UPDATE T_PA_COLABORADOR SET NM_COLAB = ?, CPF_COLAB = ?, GENERO_COLAB = ?,  TEL_COLAB = ?, DT_NASC_COLAB = TO_DATE(?, 'DD/MM/YYYY'), ENDERECO_COLAB = ?, EMAIL_COLAB = ?, SENHA_COLAB = ? WHERE ID_COLAB = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, colaborador.getNome());
            ps.setString(2, colaborador.getCpf());
            ps.setString(3, colaborador.getGenero());
            ps.setLong(4, colaborador.getTelefone());
            ps.setString(5, colaborador.getDataNascimento());
            ps.setString(6, colaborador.getEndereco());
            ps.setString(7, colaborador.getEmail());
            ps.setString(8, colaborador.getSenha());
            ps.setLong(9, colaborador.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }

    public void delete(Long id) throws SQLException {
        String query = "DELETE FROM T_PA_COLABORADOR WHERE ID_COLABORADOR = ?";

        try (Connection connection = DataBaseFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException(e);
        }
    }
}
