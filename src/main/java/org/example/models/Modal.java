package org.example.models;

public class Modal {
    private Long id;
    private Medida medidaModal;
    private String modelo;
    private String placa;
    private String marca;
    private int anoFabricacao;
    private String tipoModal;






    public Modal(Long id, Medida medidaModal, String modelo, String placa, String marca, int anoFabricacao, String tipoModal) {
        this.id = id;
        this.medidaModal = medidaModal;
        this.modelo = modelo;
        this.placa = placa;
        this.marca = marca;
        this.anoFabricacao = anoFabricacao;
        this.tipoModal = tipoModal;
    }

    public Modal() {}






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medida getMedidaModal() {
        return medidaModal;
    }

    public void setMedidaModal(Medida medidaModal) {
        this.medidaModal = medidaModal;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getTipoModal() {
        return tipoModal;
    }

    public void setTipoModal(String tipoModal) {
        this.tipoModal = tipoModal;
    }
}
