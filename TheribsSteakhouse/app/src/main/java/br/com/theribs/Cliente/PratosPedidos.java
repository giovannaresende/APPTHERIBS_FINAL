package br.com.theribs.Cliente;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.theribs.Adapter.AdapterListaChamados;
import br.com.theribs.Adapter.AdapterPratosPedidos;
import br.com.theribs.Classes.ClassPratosPedidos;
import br.com.theribs.Classes.Pedidos;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.Garcom.DadosGarcom;
import br.com.theribs.R;

public class PratosPedidos extends AppCompatActivity {

    ListView lista_pedidos;
    AdapterPratosPedidos adapter;
    Context context;
    String url, codigo_api, cod, urlValor;
    TextView txt_codigo_atendimento, valor_conta;

    private CoordinatorLayout cordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_pedidos);

        cordinator = (CoordinatorLayout)findViewById(R.id.cordinator);

        context = this;

        findViews();

        setarValores();

        iniciarAdapter();

        Pedidos p = Pedidos.getDados(this);
        cod = p.getCodigo_atendimento();

        new PratosPedidos.buscaPedidosApi().execute();

        new PratosPedidos.valorTotal().execute();

    }

    private void iniciarAdapter(){

        adapter = new AdapterPratosPedidos(
                context,
                new ArrayList<ClassPratosPedidos>()
        );
        lista_pedidos.setAdapter(adapter);
    }

    private void setarValores() {

        txt_codigo_atendimento.setText(cod);
    }

    private void findViews() {
        lista_pedidos = (ListView) findViewById(R.id.lista_pedidos);
        txt_codigo_atendimento = (TextView) findViewById(R.id.txt_codigo_atendimento);
        valor_conta = (TextView) findViewById(R.id.valor_conta);
    }

    private class buscaPedidosApi extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... Void) {
            url = getString(R.string.link)+"pedidos/pratosPedidos.php?cod="+cod;
            String executa = HttpConnection.get(url);
            return executa;
        }

        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("erro")){

                Snackbar snackbar = Snackbar
                        .make(cordinator, "Você ainda não preencheu o código", Snackbar.LENGTH_LONG)
                        .setAction("Preencher", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(context, InserirCodigo.class);
                                startActivity(intent);
                            }
                        });

                snackbar.show();

            }else {
                ClassPratosPedidos classPratosPedidos[];
                classPratosPedidos = new Gson().fromJson(resultado, ClassPratosPedidos[].class);

                try {
                    adapter.clear();
                    adapter.addAll(Arrays.asList(classPratosPedidos));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }
    }

    private class valorTotal extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            urlValor = HttpConnection.get(getString(R.string.link)+"pedidos/pratosPedidos.php?cod="+cod+"&modo=valor_total");
            return urlValor;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            try {
                JSONObject valor = new JSONObject(resultado);

                valor_conta.setText(valor.getString("valor_total"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
