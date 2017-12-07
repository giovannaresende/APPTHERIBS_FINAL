package br.com.theribs.Garcom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.theribs.MainActivity;
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

                /*case R.id.chamados_pendentes:
                    transaction.replace(R.id.container, new ChamadosPendentes()).commit();
                    return true;

                case R.id.fechar_conta:
                    transaction.replace(R.id.container, new FecharContaFragment()).commit();
                    return true;*/
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_fechar_conta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_desempenho){

            Intent desempenho = new Intent(context, Desempenho.class);
            startActivity(desempenho);

            return true;
        }else{
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            return true;
        }
    }

}
