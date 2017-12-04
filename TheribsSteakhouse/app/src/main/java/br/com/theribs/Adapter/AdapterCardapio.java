package br.com.theribs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.Cliente.PratosGridViewActivity;
import br.com.theribs.R;

import br.com.theribs.Cliente.VisualizarPratoActivity;
import br.com.theribs.R;

/**
 * Created by Jessica on 10/11/2017.
 */


public class AdapterCardapio extends ArrayAdapter<Cardapio> {


    public AdapterCardapio(Context context, List<Cardapio> objects){
        super(context,0,objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v==null){

            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_cardapio, null);
        }

        final Cardapio cardapio = getItem(position);


        TextView nome_cardapio = (TextView) v.findViewById(R.id.nome_cardapio);
        Button btn_ver_cardapio = (Button) v.findViewById(R.id.btn_ver_cardapio);

        btn_ver_cardapio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), PratosGridViewActivity.class);
                intent.putExtra("cardapio", cardapio);
                getContext().startActivity(intent);
            }
        });

        nome_cardapio.setText(cardapio.getNome_cardapio());

        return v;


    }
}
