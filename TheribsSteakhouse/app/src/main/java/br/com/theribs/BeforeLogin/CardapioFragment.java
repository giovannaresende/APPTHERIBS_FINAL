package br.com.theribs.BeforeLogin;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.theribs.Adapter.AdapterCardapio;

import br.com.theribs.Adapter.AdapterPratosBeforeLogin;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

import static br.com.theribs.R.id.lista_pratos;

/**
 * A placeholder fragment containing a simple view.
 */
public class CardapioFragment extends Fragment {
    View v;
    ListView lst_view_cardapio;
    Context context;
    AdapterPratosBeforeLogin adapter;
    public CardapioFragment() {
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_cardapio, container, false);
        lst_view_cardapio = (ListView)v.findViewById(lista_pratos);
        pratosAdapter();
        new CardapioFragment.SolicitarPratos().execute();
        return v;
    }
    private void pratosAdapter() {
        adapter = new AdapterPratosBeforeLogin(
                getContext(),
                new ArrayList<Pratos>()
        );
        lst_view_cardapio.setAdapter(adapter);
    }
    private class SolicitarPratos extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... Void){
            String url = getString(R.string.link)+"Cardapios/buscar_pratos.php";
            String executa = HttpConnection.get(url);
            return executa;
        }
        @Override
        protected void onPostExecute(String resultado) {

            try{

                Gson gson = new Gson();
                Pratos[] p =  gson.fromJson(resultado, Pratos[].class);
                adapter.clear();
                adapter.addAll(Arrays.asList(p));
                adapter.notifyDataSetChanged();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
