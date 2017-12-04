package br.com.theribs.Classes;


import java.io.Serializable;

/**
 * Created by 16165877 on 09/11/2017.
 */

public class Cardapio implements Serializable {


   private int id_cardapio;
   private String nome_cardapio;
   private Pratos[] pratos;


    public Cardapio(int id_cardapio, String nome_cardapio) {
        this.id_cardapio = id_cardapio;
        this.nome_cardapio = nome_cardapio;
    }




    public int getId_cardapio() {
        return id_cardapio;
    }

    public void setId_cardapio(int id_cardapio) {
        this.id_cardapio = id_cardapio;
    }

    public String getNome_cardapio() {
        return nome_cardapio;
    }

    public void setNome_cardapio(String nome_cardapio) {
        this.nome_cardapio = nome_cardapio;
    }

    public Pratos[] getPratos() {
        return pratos;
    }

    public void setPratos(Pratos[] pratos) {
        this.pratos = pratos;
    }





}
