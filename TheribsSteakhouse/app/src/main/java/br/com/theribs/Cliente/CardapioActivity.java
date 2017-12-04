package br.com.theribs.Cliente;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.GridView;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.theribs.Adapter.AdapterCardapio;

import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class CardapioActivity extends AppCompatActivity {

    Context context;
    String url;
    AdapterCardapio adapter;
    ListView list_cardapio_view;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = this;

        list_cardapio_view =(ListView) findViewById(R.id.list_cardapios_view);

        cardapioAdapter();
       new SolicitarPratos().execute();

    }
    private void cardapioAdapter() {
        adapter = new AdapterCardapio(
                this,
                new ArrayList<Cardapio>()
        );
        list_cardapio_view.setAdapter(adapter);
    }


    private class SolicitarPratos extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... Void) {
            Intent intent = getIntent();
            Integer id_filial = intent.getIntExtra("id", 0);

            url = context.getString(R.string.link) + "Cardapios/buscar_cardapio.php?id_filial=" + id_filial.toString();
            //String url = "http://tcc_mobile/Cardapios/buscar_cardapio.php?id_filial=" + id_filial.toString();
            String executa = HttpConnection.get(url);
            return executa;
        }

        @Override
        protected void onPostExecute(String resultado) {
            Gson gson = new Gson();
            Cardapio[] cardapio = gson.fromJson(resultado, Cardapio[].class);
            adapter.clear();
            adapter.addAll(cardapio);
        }
    }
}