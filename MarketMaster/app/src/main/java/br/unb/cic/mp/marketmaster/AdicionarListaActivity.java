package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdicionarListaActivity extends AppCompatActivity {

    Button mAddLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lista);

        mAddLista = findViewById(R.id.cria_lista);
        mAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdicionarListaActivity.this, ListaActivity.class);
                startActivity(intent);
            }
        });
    }
}
