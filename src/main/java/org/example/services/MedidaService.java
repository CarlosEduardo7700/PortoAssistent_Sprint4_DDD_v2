package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Medida;
import org.example.models.repositories.MedidaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MedidaService {

    private MedidaRepository repository = new MedidaRepository();

    public Response getAllService() throws SQLException {
        List<Medida> medidas = repository.findAll();

        if (medidas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(medidas).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Medida medida = repository.find(id).orElse(null);

        if (medida == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(medida).build();
    }



    public Response insertService(Medida medida) throws SQLException {

        if (medida == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(medida);

            return Response.status(Response.Status.CREATED).build();
        }
    }



    public Response updateService(Long id, Medida medida) throws SQLException {
        if (repository.find(id).isPresent()) {
            medida.setId(id);
            repository.update(medida);
            Optional<Medida> medidaAtualizada = repository.find(id);
            return Response.status(Response.Status.OK).entity(medidaAtualizada).build();
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
