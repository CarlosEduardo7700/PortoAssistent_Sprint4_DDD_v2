package org.example.models;

public class ModalColab {
    private Long id;
    private Modal modal;
    private Colaborador colaborador;

    public ModalColab(Long id, Modal modal, Colaborador colaborador) {
        this.id = id;
        this.modal = modal;
        this.colaborador = colaborador;
    }

    public ModalColab(){}

    public Modal getModal() {
        return modal;
    }

    public void setModal(Modal modal) {
        this.modal = modal;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
