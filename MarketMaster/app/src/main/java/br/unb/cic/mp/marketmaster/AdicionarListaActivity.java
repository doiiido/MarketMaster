package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdicionarListaActivity extends AppCompatActivity {

    private DatabaseReference mDatabaseReference;
    Button mAddLista;
    Button mCancela;
    TextInputEditText mNomeLista;
    TextInputEditText mDescricaoLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_lista);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mAddLista = findViewById(R.id.cria_lista);
        mCancela = findViewById((R.id.cancela));
        mAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_nome = mNomeLista.getText().toString();
                String input_descricao = mDescricaoLista.getText().toString();
                if (!input_nome.equals("")) {
                    NovaLista lista = new NovaLista(input_nome, input_descricao);
                    mDatabaseReference.child("usuarios").child(input_nome).push().setValue(lista);
                }
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
