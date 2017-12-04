package br.com.theribs.Garcom;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.List;

import br.com.theribs.Classes.Garcom;

/**
 * Created by Giovanna Resende on 25/10/2017.
 */

public class DadosGarcom{

    private String id_funcionario;
    private String id_unidade;
    private String id_endereco;
    private String nome_funcionario, sobrenome_funcionario, dt_nasc, usuario_funcionario, senha, foto;

    public String getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getId_unidade() {
        return id_unidade;
    }

    public void setId_unidade(String id_unidade) {
        this.id_unidade = id_unidade;
    }

    public String getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(String id_endereco) {
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

    public String getUsuario_funcionario() {
        return usuario_funcionario;
    }

    public void setUsuario_funcionario(String usuario_funcionario) {
        this.usuario_funcionario = usuario_funcionario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public static void pegarDadosBD(Context ctx, String resultado){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        try {

            JSONObject json = new JSONObject(resultado);

            preferences.edit().putString("id_funcionario", json.getString("id_funcionario")).commit();
            preferences.edit().putString("id_unidade", json.getString("id_unidade")).commit();
            preferences.edit().putString("id_endereco", json.getString("id_endereco")).commit();
            preferences.edit().putString("nome_funcionario", json.getString("nome_funcionario")).commit();
            preferences.edit().putString("sobrenome_funcionario", json.getString("sobrenome_funcionario")).commit();
            preferences.edit().putString("dt_nasc", json.getString("dt_nasc")).commit();
            preferences.edit().putString("usuario_funcionario", json.getString("senha")).commit();
            preferences.edit().putString("senha", json.getString("senha")).commit();
            preferences.edit().putString("foto", json.getString("foto")).commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static DadosGarcom getDados(Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        DadosGarcom retorno = new DadosGarcom();

        retorno.setId_funcionario(preferences.getString("id_funcionario", ""));
        retorno.setId_unidade(preferences.getString("id_unidade", ""));
        retorno.setId_endereco(preferences.getString("id_endereco", ""));
        retorno.setNome_funcionario(preferences.getString("nome_funcionario", ""));
        retorno.setSobrenome_funcionario(preferences.getString("sobrenome_funcionario", ""));
        retorno.setDt_nasc(preferences.getString("dt_nasc", ""));
        retorno.setUsuario_funcionario(preferences.getString("usuario_funcionario", ""));
        retorno.setSenha(preferences.getString("senha", ""));
        retorno.setFoto(preferences.getString("foto", ""));

        return retorno;

    }

}
