package br.com.theribs.Cliente;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.R;

public class DetalhesPratosActivity extends AppCompatActivity {
    Pratos c;
    ImageView img_detalhes_pratos;

    TextView descricao_detalhes_pratos, preco_detalhes_prato, tipo_detalhes_prato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pratos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        img_detalhes_pratos = (ImageView) findViewById(R.id.imagem_detalhes_pratos);
        descricao_detalhes_pratos = (TextView) findViewById(R.id.descricao_detalhes_prato);
        preco_detalhes_prato = (TextView) findViewById(R.id.preco_detalhes_prato);
        tipo_detalhes_prato = (TextView) findViewById(R.id.tipo_detalhes_prato);


        Intent intent = getIntent();
        c = (Pratos) intent.getSerializableExtra("prato");
        setTitle(c.getNome_prato());

        preco_detalhes_prato.setText(String.valueOf(c.getPreco_prato()));
        tipo_detalhes_prato.setText(String.valueOf(c.getTipo_produto()));
        descricao_detalhes_pratos.setText(String.valueOf(c.getDescricao_prato()));


        String url = this.getString(R.string.link_imagem) + c.getFoto_prato();

        Picasso.with(this)
                .load(url)
                .into(img_detalhes_pratos);

    }
}
