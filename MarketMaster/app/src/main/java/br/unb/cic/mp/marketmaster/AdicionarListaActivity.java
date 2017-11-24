package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdicionarListaActivity extends AppCompatActivity {

    Button mAddLista;
    Button mCancela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lista);

        mAddLista = findViewById(R.id.cria_lista);
        mCancela = findViewById((R.id.cancela));
        mAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO realmente mandar pro banco a lista, e colocar essas coisa tudo dentro de um if der certo
                Intent intent = new Intent(AdicionarListaActivity.this, ListaActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
