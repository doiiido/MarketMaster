package br.unb.cic.mp.marketmaster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Esta classe representa uma instância de item, para que ele apareça neste layout
 */

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }
}
