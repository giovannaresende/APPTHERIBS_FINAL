package br.com.theribs.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.theribs.Classes.ClassPedidosProntos;
import br.com.theribs.R;

/**
 * Created by 16165850 on 23/11/2017.
 */

public class AdapterPedidosProntos extends ArrayAdapter<ClassPedidosProntos> {

    public AdapterPedidosProntos(@NonNull Context context, List<ClassPedidosProntos> lista_pedidos) {
        super(context,0, lista_pedidos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_pedidos_prontos, null);
        }

        final ClassPedidosProntos pedidos = getItem(position);

        TextView nome_produto = (TextView) v.findViewById(R.id.txt_produto);
        TextView nome_mesa = (TextView) v.findViewById(R.id.txt_mesa);

        nome_produto.setText(pedidos.getNome_produto());
        nome_mesa.setText(pedidos.getNome_mesa());

        return v;
    }

}
