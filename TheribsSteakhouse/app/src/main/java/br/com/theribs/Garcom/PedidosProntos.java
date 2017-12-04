package br.com.theribs.Garcom;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import br.com.theribs.Adapter.AdapterPedidosProntos;
import br.com.theribs.Classes.ClassPedidosProntos;
import br.com.theribs.R;

public class PedidosProntos extends Fragment {

    View rootview;
    String id_funcionario, url, nome_produto, nome_mesa;
    ListView lst_pedidos_prontos;
    AdapterPedidosProntos adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_pedidos_prontos, container, false);


        new PedidosProntos.listarPedidosProntos().execute();

        findViews();

        return rootview;
    }

    private void findViews() {
        lst_pedidos_prontos = (ListView) rootview.findViewById(R.id.lst_pedidos_prontos);
    }

    private class listarPedidosProntos extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {

            DadosGarcom id = DadosGarcom.getDados(getContext());
            id_funcionario = id.getId_funcionario();

            url = getString(R.string.link)+"garcom_e_cliente/pedido.php?modo=pedidos_prontos&id_funcionario="+id_funcionario;

            return url;
        }

        @Override
        protected void onPostExecute(String resultado){

            List<ClassPedidosProntos> pedidosProntos = new ArrayList<>();

            try {
                JSONObject json = new JSONObject(resultado);

                if(json.getString("resultado").equals("ok")){

                    nome_produto = json.getString("nome_produto");
                    nome_mesa = json.getString("nome_mesa");

                    pedidosProntos.add(new ClassPedidosProntos(nome_produto, nome_mesa));

                }

                adapter.clear();
                adapter.addAll(pedidosProntos);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
