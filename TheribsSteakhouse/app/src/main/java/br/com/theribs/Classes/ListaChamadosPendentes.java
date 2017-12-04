package br.com.theribs.Classes;

import android.widget.ListView;

/**
 * Created by Jessica on 22/10/2017.
 */

public class ListaChamadosPendentes {

    private String codigo;
    private String num_codigo;
    private String mesa;
    private String num_mesa;

    public ListaChamadosPendentes(String num_codigo, String num_mesa){

        setNum_codigo(num_codigo);
        setNum_mesa(num_mesa);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNum_codigo() {
        return num_codigo;
    }

    public void setNum_codigo(String num_codigo) {
        this.num_codigo = num_codigo;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(String num_mesa) {
        this.num_mesa = num_mesa;
    }
}
