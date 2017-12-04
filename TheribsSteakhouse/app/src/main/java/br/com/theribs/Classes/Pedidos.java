package br.com.theribs.Classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.theribs.Garcom.DadosGarcom;

/**
 * Created by j4m2 on 06/11/17.
 */

public class Pedidos {

    private int id_pedido;
    private String nome_prato;
    private float preco_prato;
    private String codigo_atendimento;

   /* public Pedidos(int id_pedido, String nome_prato, float preco_prato, int codigo_atendimento){
        this.id_pedido = id_pedido;
        this.nome_prato = nome_prato;
        this.preco_prato = preco_prato;
        this.codigo_atendimento = codigo_atendimento;
    }*/

    public String getCodigo_atendimento() {
        return codigo_atendimento;
    }
    public void setCodigo_atendimento(String codigo_atendimento) {
        this.codigo_atendimento = codigo_atendimento;
    }
    public int getId_pedido() {
        return id_pedido;
    }
    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
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

    public static void pegarCodigo(Context ctx, String resultado){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);

        try {

            JSONObject json = new JSONObject(resultado);

            preferences.edit().putString("codigo_atendimento", json.getString("codigo_atendimento")).commit();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static Pedidos getDados(Context context){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        Pedidos retorno = new Pedidos();

        retorno.setCodigo_atendimento(preferences.getString("codigo_atendimento", ""));

        return retorno;

    }

}
