package br.com.theribs.Cliente;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import br.com.theribs.Classes.PeriodoReserva;
import br.com.theribs.Classes.UnidadesReserva;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

public class ReservaActivity extends AppCompatActivity {

    Spinner spn_id_unidade, spn_id_periodo;
    int id_unidade, id_periodo, qtd_pessoas;
    ArrayAdapter adapterUni, adapterPeriodo;

    EditText edit_qtd_pessoas;
    static EditText edit_data;
    Button btn_solicitar;
    String urlUni, urlPeriodo, urlReserva, dt_reserva, id_cliente;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        findViews();

        iniciandoAdapter();

        new ReservaActivity.carregarUnidades().execute();

        new ReservaActivity.carregarPeriodos().execute();

        DadosCliente dados = DadosCliente.getDados(context);
        id_cliente = dados.getId_cliente();

        btn_solicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new ReservaActivity.fazerReserva().execute();
            }
        });

        spn_id_periodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                PeriodoReserva id = (PeriodoReserva) adapterPeriodo.getItem(i);

                id_periodo = id.getId_periodo();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spn_id_unidade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                UnidadesReserva id = (UnidadesReserva) adapterUni.getItem(i);
                id_unidade = id.getId_unidade();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void iniciandoAdapter() {
        adapterUni = new ArrayAdapter(context,
                R.layout.support_simple_spinner_dropdown_item,
                new ArrayList<UnidadesReserva>());

        spn_id_unidade.setAdapter(adapterUni);

        adapterPeriodo = new ArrayAdapter(
                context,
                R.layout.support_simple_spinner_dropdown_item,
                new ArrayList<PeriodoReserva>()
        );

        spn_id_periodo.setAdapter(adapterPeriodo);
    }

    private void findViews() {
        spn_id_unidade = (Spinner) findViewById(R.id.spn_id_unidade);
        spn_id_periodo = (Spinner) findViewById(R.id.spn_id_periodo);
        btn_solicitar = (Button) findViewById(R.id.btn_solicitar);
        edit_data = (EditText) findViewById(R.id.edit_data);
    }

    public void abrirCalendario(View view) {

        DialogFragment calendario= new DatePickerFragment();
        calendario.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar c = Calendar.getInstance();
            int ano = c.get(Calendar.YEAR);
            int mes = c.get(Calendar.MONTH);
            int dia = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this, ano, mes, dia); // Cria uma nova instancia de Calendário e retorna
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //Funcao que é executada após a data

            String dataSelecionada = String.format("%02d/%02d/%d",dayOfMonth, ++month, year);
            edit_data.setText(dataSelecionada);
        }
    }

    private class fazerReserva extends AsyncTask<String, Void, String>{

        ProgressDialog carregar;

        @Override
        protected void onPreExecute() {
            SystemClock.sleep(1000);
            carregar = ProgressDialog.show(context, "Aguarde...", "Estamos verificando solicitação...");
        }

        @Override
        protected String doInBackground(String... strings) {

            urlReserva = getString(R.string.link)+"cliente/adquirirReserva.php?id_unidade="+id_unidade+"&id_periodo="+id_periodo+"&id_cliente="+id_cliente+"&txt_qtd_pessoas="+qtd_pessoas+"&dt_reserva="+dt_reserva;
            Log.d("resultadoo", urlReserva);
            String executa = HttpConnection.get(urlReserva);
            return executa;
        }

        @Override
        protected void onPostExecute(String resultado) {
            Log.d("resultadoo", resultado);
            carregar.dismiss();

            try {
                JSONObject resultJson = new JSONObject(resultado);

                if(resultJson.getString("resultado").equals("erro")){

                    Toast.makeText(context, "Há algo de errado! :( Tente novamente mais tarde", Toast.LENGTH_LONG).show();

                }else if(resultJson.getString("resultado").equals("mesas_indisponiveis")){

                    Toast.makeText(context, "Não há mesas disponíveis para esse dia e horário! Não desista de nós! <3 Tente novamente em outro período ou dia.", Toast.LENGTH_LONG).show();

                }else if(resultJson.getString("resultado").equals("sucesso")){

                    Toast.makeText(context, "Solicitação efetuada com sucesso! Aguarde o contato dos funcionários do restaurante para a confirmação final de sua reserva", Toast.LENGTH_LONG).show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private class carregarUnidades extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            urlUni = getString(R.string.link)+"cliente/dadosReserva.php?modo=unidade";
            String executa = HttpConnection.get(urlUni);
            return executa;
        }

        @Override
        protected void onPostExecute(String result) {

            Gson gson = new Gson();

            UnidadesReserva[] unidades = gson.fromJson(result, UnidadesReserva[].class);

            adapterUni.clear();
            adapterUni.addAll(unidades);

        }
    }

    private class carregarPeriodos extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... strings) {

            urlPeriodo = getString(R.string.link)+"cliente/dadosReserva.php?modo=periodo";
            String executa = HttpConnection.get(urlPeriodo);
            return executa;
        }

        @Override
        protected void onPostExecute(String result) {

            Gson gson = new Gson();

            PeriodoReserva[] periodo = gson.fromJson(result, PeriodoReserva[].class);

            adapterPeriodo.clear();
            adapterPeriodo.addAll(periodo);
        }
    }
}
