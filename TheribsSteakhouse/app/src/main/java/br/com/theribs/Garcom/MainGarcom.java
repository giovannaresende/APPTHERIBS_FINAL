package br.com.theribs.Garcom;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.theribs.R;

public class MainGarcom extends AppCompatActivity {

    EditText numero_pedido;
    Context context;

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch (item.getItemId()) {

                case R.id.gerar_codigo:
                    transaction.replace(R.id.container, new GerarCodigo()).commit();
                    return true;

                case R.id.fazer_pedido:
                    transaction.replace(R.id.container, new FazerPedido()).commit();
                    return true;

                case R.id.pedidos_prontos:
                    transaction.replace(R.id.container, new PedidosProntos()).commit();
                    return true;

                case R.id.chamados_pendentes:
                    transaction.replace(R.id.container, new ChamadosPendentes()).commit();
                    return true;

                case R.id.fechar_conta:
                    transaction.replace(R.id.container, new FecharContaFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_garcom);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container, new GerarCodigo()).commit();

        context = this;

    }

    public void VisualizarPedidosRealizados(View view) {

        Toast.makeText(context, "Em andamento...", Toast.LENGTH_LONG).show();
    }

    public void BuscarProdutos(View view) {

        Toast.makeText(context, "Buscar...", Toast.LENGTH_LONG).show();
    }

    public void adicionarProduto(View view) {

        Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show();
    }


    public void tirarProduto(View view) {

        Toast.makeText(context, "Tirar", Toast.LENGTH_SHORT).show();
    }
}
