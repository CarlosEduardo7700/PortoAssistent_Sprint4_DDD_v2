package org.example.models;

public class Chamada {
    private Long id;
    private Cliente cliente;
    private Colaborador colaborador;
    private Veiculo veiculo;
    private Modal modal;
    private String dataInicio;
    private String dataFim;
    private String local;
    private String destino;
    private String descProblema;





    public Chamada(Long id, Cliente cliente, Colaborador colaborador, Veiculo veiculo, Modal modal, String dataInicio, String dataFim, String local, String destino, String descProblema) {
        this.id = id;
        this.cliente = cliente;
        this.colaborador = colaborador;
        this.veiculo = veiculo;
        this.modal = modal;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.local = local;
        this.destino = destino;
        this.descProblema = descProblema;
    }

    public Chamada() {}





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Modal getModal() {
        return modal;
    }

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDescProblema() {
        return descProblema;
    }

    public void setDescProblema(String descProblema) {
        this.descProblema = descProblema;
    }
}
