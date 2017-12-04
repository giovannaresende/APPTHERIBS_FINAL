package br.com.theribs.BeforeLogin;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import br.com.theribs.Classes.Filiais;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.Cliente.NossaLocalizacaoActivity;
import br.com.theribs.DAO.Conexao;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UnidadesFragment extends Fragment {/*


    View v;
    Context context;
    ListView lista_filiais;
    AdapterFiliais adapter;
    String parametros;
    String url;

    public UnidadesFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_unidades, container, false);


        lista_filiais = (ListView) v.findViewById(R.id.lista_filiais);

       // filiaisAdapter();

       //new UnidadesFragment.SolicitarFiliais().execute();

        return v;
    }

   /* private void filiaisAdapter() {
        adapter = new AdapterFiliais(
                getContext(),
                new ArrayList<Filiais>()
        );
        lista_filiais.setAdapter(adapter);
    }

    private class SolicitarFiliais extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... Void){

            String url = getString(R.string.link)+"filiais/filiais.php";
            String executa = HttpConnection.get(url);
            return executa;
        }

        @Override
        protected void onPostExecute(String resultado) {
            Gson gson = new Gson();
            Filiais[] f =  gson.fromJson(resultado, Filiais[].class);
            adapter.clear();
            adapter.addAll(Arrays.asList(f));
            adapter.notifyDataSetChanged();
        }
    }*/


}
