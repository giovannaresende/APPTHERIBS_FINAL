package br.com.theribs.Classes;

/**
 * Created by 16165850 on 09/10/2017.
 */

public class ClassificacaoContato {

    private int id_classificacao;
    private String nomeClassificacao;

    public int getId_classificacao() {
        return id_classificacao;
    }

    public void setId_classificacao(int id_classificacao) {
        this.id_classificacao = id_classificacao;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public void setNomeClassificacao(String nomeClassificacao) {
        this.nomeClassificacao = nomeClassificacao;
    }

    @Override
    public String toString() {
        return nomeClassificacao;
    }

}
