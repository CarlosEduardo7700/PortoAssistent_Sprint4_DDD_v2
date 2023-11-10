package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Cliente;
import org.example.models.Colaborador;
import org.example.models.repositories.ColaboradorRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ColaboradorService {
    private ColaboradorRepository repository = new ColaboradorRepository();

    public Response getAllService() throws SQLException {
        List<Colaborador> colaboradores = repository.findAll();

        if (colaboradores.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(colaboradores).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Colaborador colaborador = repository.find(id).orElse(null);

        if (colaborador == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(colaborador).build();
    }



    public Response LoginService(Colaborador credenciais) throws SQLException {
        Colaborador colaborador = repository.findByEmail(credenciais.getEmail()).orElse(null);

        if (colaborador == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Email não encontrado! Tente novamente.").build();
        } else {
            if (credenciais.getSenha().equals(colaborador.getSenha())) {
                return Response.status(Response.Status.ACCEPTED).entity(colaborador).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity("Senha incorreta! Tente novamente.").build();
            }
        }
    }



    public Response insertService(Colaborador colaborador) throws SQLException {

        if (colaborador == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(colaborador);

            return Response.status(Response.Status.CREATED).build();
        }
    }



    public Response updateService(Long id, Colaborador colaborador) throws SQLException {
        if (repository.find(id).isPresent()) {
            colaborador.setId(id);
            repository.update(colaborador);
            Optional<Colaborador> colaboradorAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(colaboradorAtualizado).build();
        }

        return Response.status(Response.Status.NOT_FOUND).entity("Informação não encontrada!").build();
    }



    public Response deleteService(Long id) throws SQLException {
        if (repository.find(id).isPresent()) {
            repository.delete(id);
            return Response.status(Response.Status.OK).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Informação não encontrada!").build();
    }
}
