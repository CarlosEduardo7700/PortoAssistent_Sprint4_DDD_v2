package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Modal;
import org.example.models.repositories.ModalRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ModalService {

    private ModalRepository repository = new ModalRepository();

    public Response getAllService() throws SQLException {
        List<Modal> modais = repository.findAll();

        if (modais.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(modais).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Modal modal = repository.find(id).orElse(null);

        if (modal == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(modal).build();
    }



    public Response insertService(Modal modal) throws SQLException {

        if (modal == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(modal);

            return Response.status(Response.Status.CREATED).build();
        }
    }



    public Response updateService(Long id, Modal modal) throws SQLException {
        if (repository.find(id).isPresent()) {
            modal.setId(id);
            repository.update(modal);
            Optional<Modal> modalAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(modalAtualizado).build();
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
