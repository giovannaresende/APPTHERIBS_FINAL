package br.com.theribs.Adapter;
/**
 * Created by 16165877 on 18/10/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import br.com.theribs.Classes.Cardapio;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.R;
import br.com.theribs.Cliente.VisualizarPratoActivity;

public class AdapterPratos extends ArrayAdapter<Pratos> {
    public AdapterPratos(Context context, List<Pratos> objects) {
        super(context, 0, objects);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_prato_grid_view, null);
        }




        final Pratos pratos = getItem(position);
        TextView nome_prato = (TextView) v.findViewById(R.id.nome_prato_grid);
        TextView preco_prato = (TextView) v.findViewById(R.id.preco_prato_grid);
        ImageView img_prato = (ImageView) v.findViewById(R.id.img_prato_grid);


       String url = getContext().getString(R.string.link_imagem) + pratos.getFoto_prato();
        //String url = "http://10.0.2.2/the_ribs/" + pratos.getFoto_prato();
        Log.d("url", url);
        nome_prato.setText(pratos.getNome_prato());
        preco_prato.setText(String.valueOf(pratos.getPreco_prato()));

        Picasso.with(getContext())
                .load(url)
                .into(img_prato);


        return v;
    }
}