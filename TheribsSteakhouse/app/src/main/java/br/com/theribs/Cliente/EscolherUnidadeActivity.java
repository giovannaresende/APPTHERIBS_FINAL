package br.com.theribs.Cliente;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.theribs.Adapter.AdapterFiliais;
import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Filiais;
import br.com.theribs.Classes.UltimasAvaliacoes;
import br.com.theribs.DAO.Conexao;
import br.com.theribs.R;

import static android.media.CamcorderProfile.get;

public class EscolherUnidadeActivity extends AppCompatActivity {


    Context context;
    ListView lista_filiais;
    AdapterFiliais adapter;
    List<Filiais> lst_filiais = new ArrayList<>();
    String url="";
    String parametros;
    int id_unidade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_unidade);

        context = this;
        findViews();
        filiaisAdapter();
        buscarFiliais();



        lista_filiais.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id_unidade = adapter.getItem(position).getId_unidade();
               // Log.d("id", String.valueOf(id_unidade));
                Intent intent = new Intent(context, CardapioActivity.class);
                intent.putExtra("id", id_unidade);
                startActivity(intent);
            }

    });
    }

    private void filiaisAdapter() {
        adapter = new AdapterFiliais(
                this,new ArrayList<Filiais>()
        );
        lista_filiais.setAdapter(adapter);
    }

    private void findViews() {

        lista_filiais = (ListView) findViewById(R.id.lista_filiais);
    }

    private void buscarFiliais(){
        ConnectivityManager connMgr = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        parametros = "";
        if (networkInfo != null && networkInfo.isConnected()){

            url =  context.getString(R.string.link)+"filiais/filiais.php";

            new EscolherUnidadeActivity.SolicitarFiliais().execute(url);

        }else{
            Toast.makeText(this, "Nenhuma Conexao foi detectada", Toast.LENGTH_LONG).show();
        }
    }

    private class SolicitarFiliais extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            return Conexao.postDados(params[0], parametros);
        }
        @Override
        protected void onPostExecute(String resultado) {
            List<Filiais> lst_filiais = new ArrayList<>();
            try {
                JSONArray json = new JSONArray(resultado);
                // Itera sobre cada objeto do array
                for (int i = 0; i < json.length(); i++) {
                    JSONObject objeto = json.getJSONObject(i);
                    lst_filiais.add(new Filiais(
                            id_unidade = Integer.parseInt(objeto.getString("id_unidade")),
                            objeto.getString("nome_unidade"),
                            objeto.getString("logradouro"),
                            objeto.getString("img_perfil")
                    ));
                }
                adapter.clear();
                adapter.addAll(lst_filiais);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




}
