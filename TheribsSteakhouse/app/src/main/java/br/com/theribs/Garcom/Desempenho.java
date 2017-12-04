package br.com.theribs.Garcom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.IntegerRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import br.com.theribs.Adapter.AdapterListaUltimasAvaliacoes;
import br.com.theribs.Classes.ListaChamadosPendentes;
import br.com.theribs.Classes.UltimasAvaliacoes;
import br.com.theribs.DAO.Conexao;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class Desempenho extends AppCompatActivity {

    String url_desempenho, id_funcionario, id_unidade, id_endereco, nome_funcionario, sobrenome_funcionario, dt_nasc, usuario_funcionario, senha, foto;
    String media_avaliacao;
    EditText edit_clientes_satisfeitos, edit_mesas_atendidas, edit_comissao;
    TextView txt_nota;
    ListView lst_ultimas_avaliacoes;
    Context context;
    SharedPreferences preferences;
    AdapterListaUltimasAvaliacoes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desempenho);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();
        context = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = PreferenceManager.getDefaultSharedPreferences(context);

        avaliacoesAdapter();
    }

    private void avaliacoesAdapter() {

        adapter = new AdapterListaUltimasAvaliacoes(
                context,
                new ArrayList<UltimasAvaliacoes>()
        );

        lst_ultimas_avaliacoes.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Desabilita a edição do EditText
        DesabilitarDigitacao();

        // Resgata os dados que vieram do banco atribuindo a variáveis locais
        preencherVariaveis();

        // Chama a classe que trabalha em segundo plano para preencher a lista
        new Desempenho.PreencherNotas().execute();

        new Desempenho.CalcularMedia().execute();

    }

    private void findViews() {

        edit_clientes_satisfeitos = (EditText) findViewById(R.id.edit_clientes_satisfeitos);
        txt_nota = (TextView) findViewById(R.id.txt_nota);
        lst_ultimas_avaliacoes = (ListView) findViewById(R.id.lst_ultimas_avaliacoes);
        edit_mesas_atendidas = (EditText) findViewById(R.id.edit_mesas_atendidas);
        edit_comissao = (EditText) findViewById(R.id.edit_comissao);
    }

    private void setarValoresNaTela() {

        txt_nota.setText(media_avaliacao);
    }

    private void preencherVariaveis() {

        DadosGarcom dadosGarcom = DadosGarcom.getDados(this);
        id_funcionario = dadosGarcom.getId_funcionario();
        id_unidade = dadosGarcom.getId_unidade();
        id_endereco = dadosGarcom.getId_endereco();
        setTitle(dadosGarcom.getNome_funcionario());
        sobrenome_funcionario = dadosGarcom.getSobrenome_funcionario();
        dt_nasc = dadosGarcom.getDt_nasc();
        usuario_funcionario = dadosGarcom.getUsuario_funcionario();
        senha = dadosGarcom.getSenha();
        foto = dadosGarcom.getFoto();
    }

    private void DesabilitarDigitacao() {

        edit_clientes_satisfeitos.setEnabled(false);
        edit_mesas_atendidas.setEnabled(false);
        edit_comissao.setEnabled(false);

    }

    private class PreencherNotas extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... urls) {

            url_desempenho = getString(R.string.link)+"garcom/desempenho.php?id_funcionario="+id_funcionario;
            String executa = HttpConnection.get(url_desempenho);
            return executa;

        }

        @Override
        protected void onPostExecute(String resultado){

            List<UltimasAvaliacoes> lista_avaliacoes = new ArrayList<>();

            try {

                JSONArray jsonArray = new JSONArray(resultado);

                for(int resultadobd = 0; resultadobd < jsonArray.length(); resultadobd++){

                    JSONObject jsonObject = jsonArray.getJSONObject(resultadobd);

                    String nome = jsonObject.getString("nome_cliente");
                    String valor = jsonObject.getString("valor_nota_garcom");

                    lista_avaliacoes.add(new UltimasAvaliacoes(nome, valor));

                }

                adapter.clear();
                adapter.addAll(lista_avaliacoes);

            } catch (JSONException e) {
                e.printStackTrace();

            }


        }

    }

    private class CalcularMedia extends AsyncTask<Void, Void, String>{

        @Override
        protected String doInBackground(Void... Void){
            String url = getString(R.string.link)+"garcom/calcular_media.php?id_funcionario="+id_funcionario;
            String executa = HttpConnection.get(url);
            return executa;
        }

        @Override
        protected void onPostExecute(String resultado) {

            try {
                JSONObject media = new JSONObject(resultado);

                media_avaliacao = media.getString("media");

                Log.d("media", media_avaliacao);

                // Coloca o valor das variáveis em seus devidos lugares na tela
                setarValoresNaTela();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
