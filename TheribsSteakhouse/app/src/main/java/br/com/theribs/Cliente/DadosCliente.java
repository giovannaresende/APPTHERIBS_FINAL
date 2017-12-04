package br.com.theribs.Cliente;

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

public class DadosCliente{

    private String id_cliente;
    private String nome_cliente, sobrenome_cliente, nome_usuario, senha_usuario, foto;

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public String getSobrenome_cliente() {
        return sobrenome_cliente;
    }

    public void setSobrenome_cliente(String sobrenome_cliente) {
        this.sobrenome_cliente = sobrenome_cliente;
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

            preferences.edit().putString("id_cliente", json.getString("id_cliente")).commit();
            preferences.edit().putString("nome_cliente", json.getString("nome_cliente")).commit();
            preferences.edit().putString("sobrenome_cliente", json.getString("sobrenome_cliente")).commit();
            preferences.edit().putString("nome_usuario", json.getString("nome_usuario")).commit();
            preferences.edit().putString("senha_usuario", json.getString("senha_usuario")).commit();
            preferences.edit().putString("foto", json.getString("foto")).commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static DadosCliente getDados(Context ctx){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        DadosCliente retorno = new DadosCliente();

        retorno.setId_cliente(preferences.getString("id_cliente", ""));
        retorno.setNome_cliente(preferences.getString("nome_cliente", ""));
        retorno.setSobrenome_cliente(preferences.getString("sobrenome_cliente", ""));
        retorno.setNome_usuario(preferences.getString("nome_usuario", ""));
        retorno.setSenha_usuario(preferences.getString("senha_usuario", ""));
        retorno.setFoto(preferences.getString("foto", ""));

        return retorno;

    }

}
