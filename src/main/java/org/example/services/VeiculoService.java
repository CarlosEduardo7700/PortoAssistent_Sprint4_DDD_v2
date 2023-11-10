package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Veiculo;
import org.example.models.repositories.VeiculoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VeiculoService {

    private VeiculoRepository repository = new VeiculoRepository();

    public Response getAllService() throws SQLException {
        List<Veiculo> veiculos = repository.findAll();

        if (veiculos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(veiculos).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Veiculo veiculo = repository.find(id).orElse(null);

        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(veiculo).build();
    }



    public Response insertService(Veiculo veiculo) throws SQLException {

        if (veiculo == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(veiculo);

            return Response.status(Response.Status.CREATED).build();
        }
    }



    public Response updateService(Long id, Veiculo veiculo) throws SQLException {
        if (repository.find(id).isPresent()) {
            veiculo.setId(id);
            repository.update(veiculo);
            Optional<Veiculo> veiculoAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(veiculoAtualizado).build();
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
