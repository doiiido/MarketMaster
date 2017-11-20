package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GerenciarListasActivity extends AppCompatActivity {

    private EditText mAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_listas);
        mAddItem = findViewById(R.id.floating_action_button);
        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GerenciarListasActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });
    }
}
