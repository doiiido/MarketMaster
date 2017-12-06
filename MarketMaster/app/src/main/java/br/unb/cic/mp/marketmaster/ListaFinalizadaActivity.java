package br.unb.cic.mp.marketmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Classe que implementa a tela de de lista finalizada, ou seja, quando o usuário
 * finaliza a compra no supermercado.
 */

public class ListaFinalizadaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_finalizada);
    }
}
