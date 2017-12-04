package br.com.theribs.Garcom;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.theribs.Classes.Mesas;
import br.com.theribs.DAO.Conexao;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class GerarCodigo extends Fragment {

    TextView codigo, title_codigo;
    Spinner spn_mesa;
    Button  btn_iniciar_pedido;
    View rootView;
    String url, numcodigo, id_funcionario, parametros;
    ArrayAdapter adapter;
    List<Mesas> lst_mesas = new ArrayList<>();
    int id_mesa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_gerar_codigo, container, false);

        findViews();

        getAplication();

        DadosGarcom dadosGarcom = DadosGarcom.getDados(getContext());
        id_funcionario = dadosGarcom.getId_funcionario();

        carregarMesas();

        adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_spinner_item,
                new ArrayList<Mesas>());

        spn_mesa.setAdapter(adapter);

        spn_mesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Mesas id_mesa_adapter = (Mesas) adapter.getItem(position);

                id_mesa = id_mesa_adapter.getIdMesa();

                new GerarCodigo.GerarCodigoAsync().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

        mostrar_e_ocultar(0);

        return rootView;
    }

    private void carregarMesas() {

        new GerarCodigo.carregarMesasAsync().execute();

    }

    @Override
    public void onResume() {
        super.onResume();

        mostrar_e_ocultar(0);

    }

    private void mostrar_e_ocultar(int clique) {

        if(clique == 1){

            codigo.setVisibility(View.VISIBLE);
            title_codigo.setVisibility(View.VISIBLE);
            btn_iniciar_pedido.setVisibility(View.VISIBLE);

        }else{
            codigo.setVisibility(View.INVISIBLE);
            title_codigo.setVisibility(View.INVISIBLE);
            btn_iniciar_pedido.setVisibility(View.INVISIBLE);
        }
    }

    private void getAplication() {

        btn_iniciar_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                parametros = "codigo="+numcodigo+"&modo=iniciar_pedido&id_mesa="+id_mesa;
                String url_pedido = getString(R.string.link)+"garcom_e_cliente/pedido.php";
                new GerarCodigo.iniciarPedidoAsync().execute(url_pedido);

            }
        });
    }

    private void findViews() {

        codigo = (TextView) rootView.findViewById(R.id.numero_pedido_gerado);
        spn_mesa = (Spinner) rootView.findViewById(R.id.numero_mesa);
        title_codigo = (TextView) rootView.findViewById(R.id.title_codigo);
        btn_iniciar_pedido = (Button) rootView.findViewById(R.id.btn_iniciar_pedido);
    }

    private class GerarCodigoAsync extends AsyncTask<String, Void, String>{

        ProgressDialog carregando;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carregando = ProgressDialog.show(getContext(), "Aguarde...", "Gerando código...");
        }

        @Override
        protected String doInBackground(String... strings) {
            SystemClock.sleep(1000);

            url = HttpConnection.get(getString(R.string.link)+"garcom_e_cliente/gerar_codigo.php?num_mesa="+id_mesa);
            return url;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            carregando.dismiss();

            JSONObject resultado = null;
            try {
                resultado = new JSONObject(result);

                numcodigo = resultado.getString("resultado");

                codigo.setText(numcodigo);

                mostrar_e_ocultar(1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class carregarMesasAsync extends AsyncTask<String, Void, String>{

        Mesas mesas[];

        @Override
        protected String doInBackground(String... strings) {
            String url = HttpConnection.get(getString(R.string.link)+"garcom/carregarMesas.php?id_funcionario="+id_funcionario);
            mesas = new Gson().fromJson(url, Mesas[].class);

            return null;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            adapter.clear();
            adapter.addAll(Arrays.asList(mesas));
        }
    }

    private class iniciarPedidoAsync extends AsyncTask<String, Void, String>{

        ProgressDialog carregando;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            carregando = ProgressDialog.show(getContext(), "Aguarde...", "Carregando...");
        }

        @Override
        protected String doInBackground(String... urls) {
            url = Conexao.postDados(urls[0], parametros);
            return url;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            carregando.dismiss();

            try {
                JSONObject result = new JSONObject(resultado);

                if(result.getString("resultado").equals("iniciado")){

                    Toast.makeText(getContext(),"Pedido iniciado com sucesso!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getContext(),"Ocorreu um erro, tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
