package br.com.theribs.Classes;

/**
 * Created by j4m2 on 06/11/17.
 */

public class Filiais {

    private int id_unidade;
    private String nome_filial;
    private String descricao_filial;
    private String imagem_filial;

    public Filiais(int id_unidade, String nome_filial, String descricao_filial, String imagem_filial) {
        this.id_unidade = id_unidade;
        this.nome_filial = nome_filial;
        this.descricao_filial = descricao_filial;
        this.imagem_filial = imagem_filial;
    }

    public int getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }
    public String getImagem_filial() {
        return imagem_filial;
    }

    public void setImagem_filial(String imagem_filial) {
        this.imagem_filial = imagem_filial;
    }

    public String getNome_filial() {
        return nome_filial;
    }

    public void setNome_filial(String nome_filial) {
        this.nome_filial = nome_filial;
    }

    public String getDescricao_filial() {
        return descricao_filial;
    }

    public void setDescricao_filial(String descricao_filial) {
        this.descricao_filial = descricao_filial;
    }
}
