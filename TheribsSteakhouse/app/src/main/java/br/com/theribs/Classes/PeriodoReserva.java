package br.com.theribs.Classes;

/**
 * Created by Jessica on 29/11/2017.
 */

public class PeriodoReserva {

    private int id_periodo;
    private String nome_periodo;

    @Override
    public String toString() {
        return nome_periodo;
    }

    public PeriodoReserva(int id_periodo, String nome_periodo) {
        this.id_periodo = id_periodo;
        this.nome_periodo = nome_periodo;
    }

    public int getId_periodo() {
        return id_periodo;
    }

    public void setId_periodo(int id_periodo) {
        this.id_periodo = id_periodo;
    }

    public String getNome_periodo() {
        return nome_periodo;
    }

    public void setNome_periodo(String nome_periodo) {
        this.nome_periodo = nome_periodo;
    }
}
