package br.unb.cic.mp.marketmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Classe que implementa a tela de Pegar um item, ou seja, quando o usuário estiver no
 * supermercado e pegar um item.
 */
public class PegarItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pegar_item);
    }
}
