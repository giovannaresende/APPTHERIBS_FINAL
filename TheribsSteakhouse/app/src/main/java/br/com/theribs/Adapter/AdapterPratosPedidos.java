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
import br.com.theribs.Classes.ClassPratosPedidos;
import br.com.theribs.R;


public class AdapterPratosPedidos extends ArrayAdapter<ClassPratosPedidos> {

    TextView txt_nome_prato, txt_preco;

    public AdapterPratosPedidos(@NonNull Context context, @NonNull List<ClassPratosPedidos> objects) {
        super(context, 0, objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if(v == null){

            v = LayoutInflater.from(getContext()).inflate(R.layout.item_pratos_pedidos, null);
        }

        final ClassPratosPedidos pratos = getItem(position);

        txt_nome_prato = (TextView) v.findViewById(R.id.txt_nome_prato);
        txt_preco = (TextView) v.findViewById(R.id.txt_preco);

        txt_nome_prato.setText(pratos.getNome_produto());
        txt_preco.setText(pratos.getPreco());

        return  v;
    }
}