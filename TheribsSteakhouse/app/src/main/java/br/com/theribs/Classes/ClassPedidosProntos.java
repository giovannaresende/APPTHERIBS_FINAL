package br.com.theribs.Classes;

/**
 * Created by 16165850 on 23/11/2017.
 */

public class ClassPedidosProntos {

    private String nome_produto;
    private String nome_mesa;

    public ClassPedidosProntos(String nome_produto, String nome_mesa) {
        this.nome_produto = nome_produto;
        this.nome_mesa = nome_mesa;
    }

    public String getNome_produto() {
        return nome_produto;
    }

    public void setNome_produto(String nome_produto) {
        this.nome_produto = nome_produto;
    }

    public String getNome_mesa() {
        return nome_mesa;
    }

    public void setNome_mesa(String nome_mesa) {
        this.nome_mesa = nome_mesa;
    }

}
