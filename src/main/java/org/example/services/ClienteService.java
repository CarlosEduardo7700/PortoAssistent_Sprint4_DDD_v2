package org.example.services;

import jakarta.ws.rs.core.Response;
import org.example.models.Cliente;
import org.example.models.repositories.ClienteRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private ClienteRepository repository = new ClienteRepository();

    public Response getAllService() throws SQLException {
        List<Cliente> clientes = repository.findAll();

        if (clientes.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Nenhuma informação encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(clientes).build();
    }



    public Response getByIdService(Long id) throws SQLException {
        Cliente cliente = repository.find(id).orElse(null);

        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("A informação solicitada não foi encontrada!").build();
        }

        return Response.status(Response.Status.OK).entity(cliente).build();
    }




    public Response LoginService(Cliente credenciais) throws SQLException {
        Cliente cliente = repository.findByEmail(credenciais.getEmail()).orElse(null);

        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Email não encontrado! Tente novamente.").build();
        } else {
            if (credenciais.getSenha().equals(cliente.getSenha())) {
                return Response.status(Response.Status.ACCEPTED).entity(cliente).build();
            } else {
                return Response.status(Response.Status.NOT_ACCEPTABLE)
                        .entity("Senha incorreta! Tente novamente.").build();
            }
        }
    }



    public Response insertService(Cliente cliente) throws SQLException {

        if (cliente == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Dados inválidos! Reveja os dados da sua solicitação.").build();
        } else {
            repository.add(cliente);

            return Response.status(Response.Status.CREATED).entity(cliente).build();
        }
    }



    public Response updateService(Long id, Cliente cliente) throws SQLException {
        if (repository.find(id).isPresent()) {
            cliente.setId(id);
            repository.update(cliente);
            Optional<Cliente> clienteAtualizado = repository.find(id);
            return Response.status(Response.Status.OK).entity(clienteAtualizado).build();
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
