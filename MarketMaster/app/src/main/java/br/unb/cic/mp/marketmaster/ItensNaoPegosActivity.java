package br.unb.cic.mp.marketmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Classe que implementa a lista de itens que não forem pegos no supermercado.
 */

public class ItensNaoPegosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_nao_pegos);
    }
}
