package br.com.theribs.Classes;

/**
 * Created by 16165850 on 01/11/2017.
 */

public class Garcom {

    private String resultado;
    private String id_funcionario;
    private int id_unidade;
    private int id_endereco;
    private String nome_funcionario;
    private String sobrenome_funcionario;
    private String dt_nasc;
    private String nome_usuario;
    private String senha_usuario;
    private String media_avaliacao;
    private String foto;

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Garcom(String resultado,String id_funcionario, int id_unidade, int id_endereco, String nome_funcionario, String sobrenome_funcionario, String dt_nasc, String nome_usuario, String senha_usuario, String media_avaliacao, String foto) {
        this.resultado = resultado;
        this.id_funcionario = id_funcionario;
        this.id_unidade = id_unidade;
        this.id_endereco = id_endereco;
        this.nome_funcionario = nome_funcionario;
        this.sobrenome_funcionario = sobrenome_funcionario;
        this.dt_nasc = dt_nasc;
        this.nome_usuario = nome_usuario;
        this.senha_usuario = senha_usuario;
        this.media_avaliacao = media_avaliacao;
        this.foto = foto;
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public int getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(int id_unidade) {
        this.id_unidade = id_unidade;
    }

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public String getNome_funcionario() {
        return nome_funcionario;
    }

    public void setNome_funcionario(String nome_funcionario) {
        this.nome_funcionario = nome_funcionario;
    }

    public String getSobrenome_funcionario() {
        return sobrenome_funcionario;
    }

    public void setSobrenome_funcionario(String sobrenome_funcionario) {
        this.sobrenome_funcionario = sobrenome_funcionario;
    }

    public String getDt_nasc() {
        return dt_nasc;
    }

    public void setDt_nasc(String dt_nasc) {
        this.dt_nasc = dt_nasc;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getMedia_avaliacao() {
        return media_avaliacao;
    }

    public void setMedia_avaliacao(String media_avaliacao) {
        this.media_avaliacao = media_avaliacao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
