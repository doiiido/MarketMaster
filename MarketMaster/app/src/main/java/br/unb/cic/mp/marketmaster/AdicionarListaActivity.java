package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
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
        mNomeLista = findViewById(R.id.nome_lista_input);
        mDescricaoLista = findViewById(R.id.descricao_lista_input);
        mAddLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input_nome = mNomeLista.getText().toString();
                String input_descricao = mDescricaoLista.getText().toString();
                if (!input_nome.equals("")) {
                    NovaLista lista = new NovaLista(input_nome, input_descricao);

                    // Firebase não gosta desses caracteres
                    String nomePath = input_nome;
                    nomePath = nomePath.replace('.', '_');
                    nomePath = nomePath.replace('#', '-');
                    nomePath = nomePath.replace('$', '+');
                    nomePath = nomePath.replace('[', '(');
                    nomePath = nomePath.replace(']', ')');

                    SharedPreferences prefs = getSharedPreferences("UsuarioMM", MODE_PRIVATE);

                    String emailPath = prefs.getString("email", null);
                    emailPath = emailPath.replace('.', '_');
                    emailPath = emailPath.replace('#', '-');
                    emailPath = emailPath.replace('$', '+');
                    emailPath = emailPath.replace('[', '(');
                    emailPath = emailPath.replace(']', ')');

                    String objectId = mDatabaseReference
                            .child("usuarios")
                            .child(emailPath)
                            .child("listas")
                            .child("info")
                            .push().getKey();
                    mDatabaseReference
                            .child("usuarios")
                            .child(emailPath)
                            .child("listas")
                            .child("info")
                            .child(objectId)
                            .setValue(lista);

                    mDatabaseReference.child("usuarios")
                            .child(emailPath)
                            .child("listas")
                            .child("lista")
                            .child(nomePath)
                            .setValue(objectId);

                    prefs.edit().putString("lista", nomePath).apply();
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
