package br.com.theribs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


import br.com.theribs.Classes.ListaChamadosPendentes;
import br.com.theribs.Cliente.VisualizarPratoActivity;
import br.com.theribs.R;

/**
 * Created by Jessica on 22/10/2017.
 */

public class AdapterListaChamados extends ArrayAdapter<ListaChamadosPendentes> {

    public AdapterListaChamados(@NonNull Context context,  List<ListaChamadosPendentes> lista_chamados) {
        super(context,0, lista_chamados);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_chamados, null);
        }

        final ListaChamadosPendentes chamados = getItem(position);

        TextView txt_codigo = (TextView) v.findViewById(R.id.txt_codigo);
        TextView txt_mesa = (TextView) v.findViewById(R.id.txt_mesa);

        txt_codigo.setText(chamados.getNum_codigo());
        txt_mesa.setText(chamados.getNum_mesa());

        return v;
    }
}
