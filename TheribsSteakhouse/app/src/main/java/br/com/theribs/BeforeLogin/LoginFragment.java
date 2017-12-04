package br.com.theribs.BeforeLogin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import br.com.theribs.Classes.Garcom;
import br.com.theribs.Cliente.DadosCliente;
import br.com.theribs.Cliente.TelaPrincipalCliente;
import br.com.theribs.DAO.Conexao;
import br.com.theribs.Garcom.DadosGarcom;
import br.com.theribs.Garcom.MainGarcom;
import br.com.theribs.R;

/**
 * Created by 16165850 on 30/11/2017.
 */

public class LoginFragment extends Fragment {

    String url = "";
    String parametros;
    Context context;
    EditText txt_usuario, txt_senha;
    Button btn_logar;
    View v;
    SharedPreferences preferences;

    public LoginFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        context = getContext();
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_login, container, false);
        findViews(rootView);

        preferences = PreferenceManager.getDefaultSharedPreferences(getContext());

        Logar();

        return rootView;
    }

    public void findViews(View rootView) {

        txt_usuario = (EditText) rootView.findViewById(R.id.usuario);
        txt_senha = (EditText) rootView.findViewById(R.id.senha);
        btn_logar = (Button) rootView.findViewById(R.id.btnLogar);

    }

    public void Logar() {

        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario, senha;

                usuario = txt_usuario.getText().toString();
                senha = txt_senha.getText().toString();

                if(usuario == null || senha == null){

                    Snackbar.make(view, "Preencha todos os campos!"
                            , Snackbar.LENGTH_INDEFINITE)
                            .setAction("", null).show();

                }else{

                    url = getString(R.string.link)+"login/logar.php";

                    parametros = "usuario="+usuario+"&senha="+senha;

                    new LoginFragment.Logar(view).execute(url);

                }
            }
        });
    }

    private class Logar extends AsyncTask<String, Void, String>{
        View view;
        public Logar(View view){
            this.view = view;
        }

        ProgressDialog janelaCarregar;

        @Override
        protected void onPreExecute() {
            janelaCarregar = ProgressDialog.show(getContext(), "Aguarde...", "Verificando...");
        }
        @Override
        protected String doInBackground(String... urls) {
            SystemClock.sleep(1000);
            return Conexao.postDados(urls[0], parametros);
        }
        @Override
        protected void onPostExecute(String resultado) {
            janelaCarregar.dismiss();

            try {
                JSONObject json = new JSONObject(resultado);
                if(json.getString("resultado").equals("login_cliente_ok")){
                    logouCliente(resultado);
                }else if(json.getString("resultado").equals("login_garcom_ok")){
                    logouGarcom(resultado);
                }else if(json.getString("resultado").equals("usuario_nao_encontrado")){

                    Snackbar.make(view, "Usuário nao cadastrado! Verifique se o nome de usuário e senha estão corretos"
                            , Snackbar.LENGTH_INDEFINITE)
                            .setAction("", null).show();

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void logouGarcom(String resultado) {

        Intent abreInicioGarcom = new Intent(getContext(), MainGarcom.class);
        dados_garcom(resultado);
        startActivity(abreInicioGarcom);

    }

    public void dados_garcom(String resultado){

        DadosGarcom dadosGarcom = new DadosGarcom();
        dadosGarcom.pegarDadosBD(context, resultado);

    }

    public void logouCliente(String resultado){

        Intent abreInicioCliente = new Intent(getContext(), TelaPrincipalCliente.class);
        dados_cliente(resultado);
        startActivity(abreInicioCliente);
    }

    public void dados_cliente(String resultado){

        DadosCliente dadosCliente = new DadosCliente();
        dadosCliente.pegarDadosBD(context, resultado);

    }



}
