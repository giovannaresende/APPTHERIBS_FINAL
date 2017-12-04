package br.com.theribs.Garcom;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import br.com.theribs.MainActivity;
import br.com.theribs.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FecharContaFragment extends Fragment {

    View v;

    public FecharContaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_fechar_conta, container, false);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        getActivity().getMenuInflater().inflate(R.menu.menu_fechar_conta, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_desempenho){

            Intent desempenho = new Intent(getContext(), Desempenho.class);
            startActivity(desempenho);

            return true;
        }else{
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }

    }

}
