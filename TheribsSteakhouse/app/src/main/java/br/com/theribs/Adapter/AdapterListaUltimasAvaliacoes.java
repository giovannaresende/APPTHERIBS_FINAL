package br.com.theribs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


import br.com.theribs.Classes.ListaChamadosPendentes;
import br.com.theribs.Classes.UltimasAvaliacoes;
import br.com.theribs.Cliente.VisualizarPratoActivity;
import br.com.theribs.R;

/**
 * Created by 16165850 on 26/10/2017.
 */

public class AdapterListaUltimasAvaliacoes extends ArrayAdapter<UltimasAvaliacoes> {

    TextView txt_nota, txt_nome_cliente;

    public AdapterListaUltimasAvaliacoes(Context context, List<UltimasAvaliacoes> objects){
        super(context,0,objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_ultimas_avaliacoes, null);
        }

        final UltimasAvaliacoes avaliacoes = getItem(position);

        TextView txt_nota = (TextView) v.findViewById(R.id.txt_nota);
        TextView txt_nome_cliente = (TextView) v.findViewById(R.id.txt_nome_cliente);

        txt_nota.setText(avaliacoes.getNota());
        txt_nome_cliente.setText(avaliacoes.getNomeCliente());

        return v;
    }

}
