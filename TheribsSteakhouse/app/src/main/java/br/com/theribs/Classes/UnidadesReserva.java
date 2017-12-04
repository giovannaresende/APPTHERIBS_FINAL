package br.com.theribs.Classes;

/**
 * Created by Jessica on 29/11/2017.
 */

public class UnidadesReserva{

    private int id_unidade;
    private String nome_unidade;

    @Override
    public String toString() {
        return nome_unidade;
    }

    public UnidadesReserva(int id_unidade, String nome_unidade) {
        this.id_unidade = id_unidade;
        this.nome_unidade = nome_unidade;
    }

    public int getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    public String getNome_unidade() {
        return nome_unidade;
    }

    public void setNome_unidade(String nome_unidade) {
        this.nome_unidade = nome_unidade;
    }
}
