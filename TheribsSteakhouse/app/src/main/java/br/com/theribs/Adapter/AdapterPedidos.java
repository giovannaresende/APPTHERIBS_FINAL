package br.com.theribs.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.theribs.Classes.Pedidos;
import br.com.theribs.R;

/**
 * Created by j4m2 on 06/11/17.
 */

public class AdapterPedidos extends ArrayAdapter<Pedidos> {

    public AdapterPedidos(Context context, List<Pedidos> lista){
        super(context, 0, lista);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view==null){

            view = LayoutInflater.from(getContext()).inflate(R.layout.item_pedido, null);

        }

        final Pedidos pedidos = getItem(position);

        TextView nome_prato_pedido = (TextView)view.findViewById(R.id.nome_prato_pedido);
        TextView preco_prato_pedido = (TextView)view.findViewById(R.id.preco_prato_pedido);

        nome_prato_pedido.setText(pedidos.getNome_prato());
        preco_prato_pedido.setText(String.valueOf(pedidos.getPreco_prato()));

        return view;
    }
}
