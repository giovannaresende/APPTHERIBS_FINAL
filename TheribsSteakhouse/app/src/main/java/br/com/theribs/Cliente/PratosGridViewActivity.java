package br.com.theribs.Cliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.theribs.Adapter.AdapterPratos;
import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.R;

import static java.security.AccessController.getContext;

public class PratosGridViewActivity extends AppCompatActivity {

    GridView lst_grid_view;
    Context context;
    Cardapio c;
    AdapterPratos adapterPratos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pratos_grid_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lst_grid_view = (GridView) findViewById(R.id.grid_view);





        Intent intent = getIntent();
        c = (Cardapio) intent.getSerializableExtra("cardapio");
        setTitle(c.getNome_cardapio());

        PratosAdapter();

        lst_grid_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(PratosGridViewActivity.this, DetalhesPratosActivity.class);

                Pratos prato = adapterPratos.getItem(position);

                intent.putExtra("prato",prato);
                startActivity(intent);
            }
        });
    }
    private void PratosAdapter() {
        adapterPratos = new AdapterPratos(
                this,

             Arrays.asList(c.getPratos())
        );
        lst_grid_view.setAdapter(adapterPratos);
    }
}
