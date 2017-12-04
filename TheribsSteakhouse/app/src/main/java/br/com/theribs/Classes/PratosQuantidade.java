package br.com.theribs.Classes;

/**
 * Created by Jessica on 03/12/2017.
 */

public class PratosQuantidade {

    String nome_prato;
    String quantidade;

    public PratosQuantidade(String nome_prato, String quantidade) {
        this.nome_prato = nome_prato;
        this.quantidade = quantidade;
    }

    public String getNome_prato() {
        return nome_prato;
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }
}
