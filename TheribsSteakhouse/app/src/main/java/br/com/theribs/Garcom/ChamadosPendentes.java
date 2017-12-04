package br.com.theribs.Garcom;

        import android.content.Context;
        import android.graphics.drawable.AdaptiveIconDrawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;
        import java.util.List;

        import br.com.theribs.Adapter.AdapterListaChamados;
        import br.com.theribs.Classes.ListaChamadosPendentes;
        import br.com.theribs.R;

        import static br.com.theribs.R.id.container;

public class ChamadosPendentes extends Fragment {

    View v;
    ListView lst_chamados;
    List<ListaChamadosPendentes> lista_chamados = new ArrayList<>();
    Context context;

    private void findViews() {
        lst_chamados = v.findViewById(R.id.lst_chamados);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_chamados_pendentes, container, false);

        findViews();

        context = getContext();

        lista_chamados.add(new ListaChamadosPendentes("8888", "10"));
        lista_chamados.add(new ListaChamadosPendentes("8888", "10"));
        lista_chamados.add(new ListaChamadosPendentes("8888", "10"));
        lista_chamados.add(new ListaChamadosPendentes("8888", "10"));
        lista_chamados.add(new ListaChamadosPendentes("8888", "10"));

        AdapterListaChamados adapterListaChamados = new AdapterListaChamados(
                context,
                lista_chamados
        );

        lst_chamados.setAdapter(adapterListaChamados);

        return v;
    }

}
