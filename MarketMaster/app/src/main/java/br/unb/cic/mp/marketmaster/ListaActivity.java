package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ListaActivity extends AppCompatActivity {

    // TODO implementar entrada nos itens, e o uso de listas, que na moral que é um cu de fazer (adapter - se você lendo não sou eu, procure isso e boa sorte)
    // Mesma ideia na classe GerenciarListasActivity
    private FloatingActionButton mAddItem;
    private Button mVolta;
    private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        SharedPreferences prefs = getSharedPreferences("UsuarioMM", MODE_PRIVATE);
        titulo = findViewById(R.id.titulo_lista);
        titulo.setText(prefs.getString("lista", null));

        mAddItem = findViewById(R.id.add_item);
        mVolta = findViewById(R.id.volta);
        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, AdicionarItensActivity.class);
                startActivity(intent);
            }
        });
        mVolta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
