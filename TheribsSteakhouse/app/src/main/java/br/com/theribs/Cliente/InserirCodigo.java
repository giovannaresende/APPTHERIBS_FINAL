package br.com.theribs.Cliente;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pedidos;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.Garcom.DadosGarcom;
import br.com.theribs.R;

public class InserirCodigo extends AppCompatActivity {

    EditText inserir_codigo;
    String cod, url;
    Context context;
    Button btn_inserir_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_codigo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        findViews();

        btn_inserir_codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cod = inserir_codigo.getText().toString();

                if(cod.isEmpty()){

                    Toast.makeText(context, "Insira um código!", Toast.LENGTH_LONG).show();
                }else{

                    new InserirCodigo.VerificarCodigoApi().execute();
                }

            }
        });

    }

    private void findViews() {
        inserir_codigo = (EditText) findViewById(R.id.codigo);
        btn_inserir_codigo = (Button) findViewById(R.id.btn_inserir_codigo);
    }


    private class VerificarCodigoApi extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... Void) {
            url = getString(R.string.link) + "garcom_e_cliente/inserirCodigo.php?cod=" + cod;
            String executa = HttpConnection.get(url);
            return executa;
        }
        @Override
        protected void onPostExecute(String resultado) {

            if(resultado.contains("erro")){

                Toast.makeText(context, "Insira um código válido!", Toast.LENGTH_LONG).show();

            }else{

                Toast.makeText(context, "Código validado com sucesso!", Toast.LENGTH_LONG).show();
                codigo(resultado);
                Intent intent = new Intent(context, TelaPrincipalCliente.class);
                startActivity(intent);

            }

        }
    }

    public void codigo(String resultado){
        Pedidos p = new Pedidos();
        p.pegarCodigo(context, resultado);

    }

}