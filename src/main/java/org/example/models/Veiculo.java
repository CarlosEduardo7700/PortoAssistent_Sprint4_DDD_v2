package org.example.models;

public class Veiculo {
    private Long id;
    private Medida medidaVeiculo;
    private Long apolice;
    private String modelo;
    private String placa;
    private int anoFabricacao;
    private int quantidadeEixos;
    private String renavan;
    private Long numChassi;
    private String tipoChassi;
    private String tipoEixo;





    public Veiculo(Long id, Medida medidaVeiculo, Long apolice, String modelo, String placa, int anoFabricacao, int quantidadeEixos, String renavan, Long numChassi, String tipoChassi, String tipoEixo) {
        this.id = id;
        this.medidaVeiculo = medidaVeiculo;
        this.apolice = apolice;
        this.modelo = modelo;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.quantidadeEixos = quantidadeEixos;
        this.renavan = renavan;
        this.numChassi = numChassi;
        this.tipoChassi = tipoChassi;
        this.tipoEixo = tipoEixo;
    }
    public Veiculo() {}






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medida getMedidaVeiculo() {
        return medidaVeiculo;
    }

    public void setMedidaVeiculo(Medida medidaVeiculo) {
        this.medidaVeiculo = medidaVeiculo;
    }

    public Long getApolice() {
        return apolice;
    }

    public void setApolice(Long apolice) {
        this.apolice = apolice;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getQuantidadeEixos() {
        return quantidadeEixos;
    }

    public void setQuantidadeEixos(int quantidadeEixos) {
        this.quantidadeEixos = quantidadeEixos;
    }

    public String getRenavan() {
        return renavan;
    }

    public void setRenavan(String renavan) {
        this.renavan = renavan;
    }

    public Long getNumChassi() {
        return numChassi;
    }

    public void setNumChassi(Long numChassi) {
        this.numChassi = numChassi;
    }

    public String getTipoChassi() {
        return tipoChassi;
    }

    public void setTipoChassi(String tipoChassi) {
        this.tipoChassi = tipoChassi;
    }

    public String getTipoEixo() {
        return tipoEixo;
    }

    public void setTipoEixo(String tipoEixo) {
        this.tipoEixo = tipoEixo;
    }
}
