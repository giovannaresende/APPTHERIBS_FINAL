package br.com.theribs.BeforeLogin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import br.com.theribs.Adapter.AdapterCardapio;

import br.com.theribs.Adapter.AdapterPratosBeforeLogin;
import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.DAO.HttpConnection;
import br.com.theribs.R;

import static br.com.theribs.R.id.lista_pratos;

/**
 * Created by 16165850 on 23/10/2017.
 */
public class CardapioActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.fragment_cardapio);

    }




}
