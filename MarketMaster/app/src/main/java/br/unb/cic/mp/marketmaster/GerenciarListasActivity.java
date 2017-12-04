package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class GerenciarListasActivity extends AppCompatActivity {

    // TODO implementar entrada nas listas, e o uso de listas, que na moral que é um cu de fazer (adapter - se você lendo não sou eu, procure isso e boa sorte)
    // Mesma ideia na classe ListaActivity
    private FloatingActionButton mAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_listas);
        mAddItem = findViewById(R.id.add_lista);
        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences("UsuarioMM", MODE_PRIVATE);
                Log.d("MarketMaster", "usuario = " + prefs.getString("usuario", null));
                Log.d("MarketMaster",  "email = " + prefs.getString("email", null));
                Toast.makeText(GerenciarListasActivity.this, "Louco, mano.", Toast.LENGTH_SHORT);
                Intent intent = new Intent(GerenciarListasActivity.this, AdicionarListaActivity.class);
                startActivity(intent);
            }
        });
    }
}
