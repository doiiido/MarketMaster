package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GerenciarListasActivity extends AppCompatActivity {

    // TODO implementar entrada nas listas, e o uso de listas, que na moral que é um cu de fazer (adapter - se você lendo não sou eu, procure isso e boa sorte)
    // Mesma ideia na classe ListaActivity
    private FloatingActionButton mAddItem;
    private ListView mListas;
    private DatabaseReference mDatabaseReference;
    private ListasAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_listas);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mAddItem = findViewById(R.id.add_lista);
        mListas = findViewById(R.id.listas);

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

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("UsuarioMM", 0);
        mAdapter = new ListasAdapter(this, mDatabaseReference, prefs.getString("email", null));
        mListas.setAdapter(mAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.cleanup();
    }
}
