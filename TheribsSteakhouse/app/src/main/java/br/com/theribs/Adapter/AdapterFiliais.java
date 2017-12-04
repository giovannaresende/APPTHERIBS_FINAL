package br.com.theribs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.theribs.Classes.Filiais;
import br.com.theribs.Cliente.VisualizarPratoActivity;
import br.com.theribs.R;

/**
 * Created by j4m2 on 06/11/17.
 */

public class AdapterFiliais extends ArrayAdapter<Filiais> {


    public AdapterFiliais(Context context, List<Filiais> lista){
        super(context, 0, lista);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View view = convertView;
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_filiais, null);
        }

        final Filiais filiais = getItem(position);

        TextView nome_filial = (TextView) view.findViewById(R.id.nome_filial);
        TextView descricao_filial = (TextView) view.findViewById(R.id.descricao_filial);
        ImageView imagem_filial = (ImageView) view.findViewById(R.id.imagem_filial);

        String url = getContext().getString(R.string.link_imagem) + filiais.getImagem_filial();
        nome_filial.setText(filiais.getNome_filial());
        descricao_filial.setText(filiais.getDescricao_filial());

        Picasso.with(getContext())
                .load(url)
                .into(imagem_filial);

        return view;
    }
}
