package br.com.theribs.Garcom;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

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

        findViews();

        adapter = new AdapterPratosGarcom(
                context,
                new ArrayList<PratosQuantidade>()
        );

        lst_pratos.setAdapter(adapter);

        carregarPratos();

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

            url = getString(R.string.link)+"garcom/fazerPedido.php?modo=carregar_pratos";
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
}
