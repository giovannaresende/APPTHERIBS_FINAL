package br.com.theribs.Classes;

import java.io.Serializable;

/**
 * Created by 16165877 on 18/10/2017.
 */

public class Pratos implements Serializable {


    private int id_prato;
    private String nome_prato;
    private float preco_prato;
    private String foto_prato;
    private String descricao_prato;
    private String tipo_produto;

    //CONSTRUTORES
    public Pratos(int id_prato, String nome_prato,float preco_prato, String foto_prato, String tipo_produto, String descricao_prato){
        this.id_prato = id_prato;
        this.nome_prato= nome_prato;
        this.preco_prato = preco_prato;
        this.foto_prato = foto_prato;
        this.tipo_produto = tipo_produto;
        this.descricao_prato = descricao_prato;
    }
    public int getId_prato() {
        return id_prato;
    }

    public void setId_prato(int id_prato) {
        this.id_prato = id_prato;
    }

    public String getNome_prato() {
        return nome_prato;
    }

    public void setNome_prato(String nome_prato) {
        this.nome_prato = nome_prato;
    }

    public float getPreco_prato() {
        return preco_prato;
    }

    public void setPreco_prato(float preco_prato) {
        this.preco_prato = preco_prato;
    }


    public String getFoto_prato() {
        return foto_prato;
    }

    public void setFoto_prato(String foto_prato) {
        this.foto_prato = foto_prato;
    }

    public String getDescricao_prato() {
        return descricao_prato;
    }

    public void setDescricao_prato(String descricao_prato) {
        this.descricao_prato = descricao_prato;
    }

    public String getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(String tipo_produto) {
        this.tipo_produto = tipo_produto;
    }


}
