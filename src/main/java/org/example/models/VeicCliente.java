package org.example.models;

public class VeicCliente {
    private Long id;
    private Cliente cliente;
    private Veiculo veiculo;

    public VeicCliente(Long id, Cliente cliente, Veiculo veiculo) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
    }

    public VeicCliente(){}

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
