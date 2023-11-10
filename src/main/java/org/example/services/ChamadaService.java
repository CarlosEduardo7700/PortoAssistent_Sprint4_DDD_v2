package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Chamada;
import org.example.models.repositories.ChamadaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChamadaService {
    private ChamadaRepository repository = new ChamadaRepository();

    public Response getAllService() throws SQLException {
        List<Chamada> chamadas = repository.findAll();

        if (chamadas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(chamadas).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Chamada chamada = repository.find(id).orElse(null);

        if (chamada == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(chamada).build();
    }



    public Response insertService(Chamada chamada) throws SQLException {

        if (chamada == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(chamada);

            return Response.status(Response.Status.CREATED).build();
        }
    }



    public Response updateService(Long id, Chamada chamada) throws SQLException {
        if (repository.find(id).isPresent()) {
            chamada.setId(id);
            repository.update(chamada);
            Optional<Chamada> chamadaAtualizada = repository.find(id);
            return Response.status(Response.Status.OK).entity(chamadaAtualizada).build();
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
