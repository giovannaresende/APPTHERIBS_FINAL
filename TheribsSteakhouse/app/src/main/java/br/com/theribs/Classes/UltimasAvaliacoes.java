package br.com.theribs.Classes;

/**
 * Created by 16165850 on 26/10/2017.
 */

public class UltimasAvaliacoes {

    private String nome_cliente;
    private String nota;

    public UltimasAvaliacoes(String nome_cliente, String nota){

        setNomeCliente(nome_cliente);
        setNota(nota);
    }

    public String getNomeCliente() {
        return nome_cliente;
    }

    public void setNomeCliente(String codigo) {
        this.nome_cliente = codigo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }
}
