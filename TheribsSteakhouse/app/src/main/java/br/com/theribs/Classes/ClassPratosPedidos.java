package br.com.theribs.Classes;

/**
 * Created by 16165850 on 27/11/2017.
 */

public class ClassPratosPedidos {

    private String nome_produto;
    private String preco;

    public ClassPratosPedidos(String nome_produto, String preco) {
        this.nome_produto = nome_produto;
        this.preco = preco;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}
