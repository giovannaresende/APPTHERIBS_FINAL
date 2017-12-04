package br.com.theribs.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.theribs.Classes.ClassPedidosProntos;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.Classes.PratosQuantidade;
import br.com.theribs.R;

/**
 * Created by Jessica on 03/12/2017.
 */

public class AdapterPratosGarcom extends ArrayAdapter<PratosQuantidade> {

    public AdapterPratosGarcom(@NonNull Context context, List<PratosQuantidade> lista_pratos) {
        super(context,0, lista_pratos);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_lista_pratos, null);
        }

        final PratosQuantidade pratos = getItem(position);

        TextView nome_produto = (TextView) v.findViewById(R.id.txt_nome_produto);
        TextView quantidade = (TextView) v.findViewById(R.id.txt_qtd);

        try {
            nome_produto.setText(pratos.getNome_prato());
            quantidade.setText(pratos.getQuantidade());

        }catch (Exception e){
            e.printStackTrace();
        }

        return v;
    }
}
