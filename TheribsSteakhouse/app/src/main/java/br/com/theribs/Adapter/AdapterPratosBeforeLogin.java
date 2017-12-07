package br.com.theribs.Adapter;

import android.content.Context;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.theribs.BeforeLogin.CardapioFragment;
import br.com.theribs.Classes.Pratos;
import br.com.theribs.R;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by j4m2 on 16/11/17.
 */

public class AdapterPratosBeforeLogin extends ArrayAdapter<Pratos>{

    public AdapterPratosBeforeLogin(Context context, List<Pratos> lista){
        super(context, 0, lista);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;


        if (v == null){
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_before_login, null);
        }


        final Pratos pratos = getItem(position);

        TextView nome_prato = v.findViewById(R.id.nome_prato_before_login);
        TextView preco_prato = v.findViewById(R.id.preco_prato_before_login);
        CircleImageView img_prato = v.findViewById(R.id.imagem_prato_before_login);

        String url = getContext().getString(R.string.link_imagem) + pratos.getFoto_prato();

        nome_prato.setText(String.valueOf(pratos.getNome_prato()));
        preco_prato.setText(String.valueOf(String.valueOf(pratos.getPreco_prato())));

        Picasso.with(getContext())
                .load(url)
                .into(img_prato);
        return v;
    }
}
