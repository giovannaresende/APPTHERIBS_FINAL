package br.com.theribs.Garcom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.theribs.Adapter.AdapterListaUltimasAvaliacoes;
import br.com.theribs.Adapter.AdapterPratos;
import br.com.theribs.Adapter.AdapterPratosGarcom;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.Classes.PratosQuantidade;
import br.com.theribs.Classes.UltimasAvaliacoes;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class ListaPedido extends AppCompatActivity {

    ListView lst_pratos;
    String id_mesa;
    Context context;
    String url;
    AdapterPratosGarcom adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pedido);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        findViews();

        id_mesa = preferences.getString("id_mesa", "");

        adapter = new AdapterPratosGarcom(
                context,
                new ArrayList<PratosQuantidade>()
        );

        lst_pratos.setAdapter(adapter);

        new ListaPedido.VerificarPedidoAberto().execute();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        lst_pratos = (ListView) findViewById(R.id.lst_pratos);
    }

    private void carregarPratos() {

        new ListaPedido.carregarPratosAsync().execute();
    }

    private class carregarPratosAsync extends AsyncTask<String, Void, String> {

        PratosQuantidade pratos[];

        @Override
        protected String doInBackground(String... strings) {

            url = getString(R.string.link)+"garcom/fazerPedido.php?modo=carregar_pratos&id_mesa="+id_mesa;
            String executa = HttpConnection.get(url);

            pratos = new Gson().fromJson(executa, PratosQuantidade[].class);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            adapter.clear();
            adapter.addAll(Arrays.asList(pratos));
        }
    }

    private class VerificarPedidoAberto extends AsyncTask<String, Void, String>{

        ProgressDialog carregar;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carregar = ProgressDialog.show(context, "Aguarde...", "Verificando a existência de conta aberta");
        }

        @Override
        protected String doInBackground(String... strings) {
            url = HttpConnection.get(getString(R.string.link)+"garcom/fazerPedido.php?modo=verificar&id_mesa="+id_mesa);
            return url;
        }

        @Override
        protected void onPostExecute(String resultado) {

            carregar.dismiss();

            try{

                JSONObject object = new JSONObject(resultado);
                String teste = object.getString("resultado");
                Log.d("mesa", teste);
                if(object.getString("resultado").equals("ok")){

                    carregarPratos();
                }else if(object.getString("resultado").equals("erro")){

                    Toast.makeText(context, "Essa mesa não possui uma conta aberta! Por favor, abra uma conta e tente novamente",Toast.LENGTH_LONG).show();
                }

            }catch (JSONException e){

                e.printStackTrace();
                Toast.makeText(context, "exception",Toast.LENGTH_LONG).show();

            }

        }
    }
}
