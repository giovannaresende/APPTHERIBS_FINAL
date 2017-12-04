package br.com.theribs.Garcom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.theribs.Classes.Mesas;
import br.com.theribs.Classes.MyListView;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class FazerPedido extends Fragment {

    View v;
    MyListView lst_mesas;
    ArrayAdapter adapter;
    String id_funcionario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_fazer_pedido, container, false);

        findViews();

        DadosGarcom id = DadosGarcom.getDados(getContext());
        id_funcionario = id.getId_funcionario();

        adapter = new ArrayAdapter(
                getContext(),
                android.R.layout.simple_list_item_1,
                new ArrayList<Mesas>());

        lst_mesas.setAdapter(adapter);

        carregarMesas();

        lst_mesas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Mesas id_mesa = (Mesas) adapter.getItem(position);

                Intent abrirPedido = new Intent(getContext(), ListaPedido.class);
                abrirPedido.putExtra("id_mesa", id_mesa.getIdMesa());
                startActivity(abrirPedido);
            }
        });

        return v;
    }

    private void carregarMesas() {

        new FazerPedido.CarregarMesasAsync().execute();
    }

    private void findViews() {
        lst_mesas = (MyListView) v.findViewById(R.id.lst_mesas);
    }

    private class CarregarMesasAsync extends AsyncTask<String, Void, String> {

        Mesas mesas[];

        @Override
        protected String doInBackground(String... strings) {
            String url = HttpConnection.get(getString(R.string.link)+"garcom/carregarMesas.php?id_funcionario="+id_funcionario);
            mesas = new Gson().fromJson(url, Mesas[].class);
            Log.d("urls", url);
            return null;
        }

        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);

            adapter.clear();
            adapter.addAll(Arrays.asList(mesas));
        }

    }
}
