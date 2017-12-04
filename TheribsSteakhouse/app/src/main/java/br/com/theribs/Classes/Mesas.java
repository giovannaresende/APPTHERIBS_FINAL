package br.com.theribs.Classes;

/**
 * Created by Giovanna on 15/11/2017.
 */

public class Mesas {

    private String nome_mesa;
    private int id_mesa;

    public String getNome() {
        return nome_mesa;
    }

    public void setNome(String nome_mesa) {
        this.nome_mesa = nome_mesa;
    }

    public int getIdMesa() {
        return id_mesa;
    }

    public void setIdMesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    @Override
    public String toString() {
        return nome_mesa;
    }
}
