package br.unb.cic.mp.marketmaster;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CriaItemActivity extends AppCompatActivity {

    Button mCriaItem;
    Button mCancela;
    AutoCompleteTextView mNomeItem;
    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cria_item);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        mCriaItem = findViewById(R.id.cria_item);
        mCancela = findViewById(R.id.cancela);
        mNomeItem = findViewById(R.id.nome_input);

        /**
         * Quando clicar no botão de criar item, deve adicionar o banco o novo item
         */
        mCriaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mNomeItem.getText().toString();
                if (!input.equals("")) {
                    NovoItem item = new NovoItem(input);
                    String objectId = mDatabaseReference
                            .child("itens")
                            .child("conteudo")
                            .push().getKey();
                    mDatabaseReference
                            .child("itens")
                            .child("conteudo")
                            .child(objectId)
                            .setValue(item);

                    // Firebase não gosta desses caracteres
                    input = input.replace('.', '_');
                    input = input.replace('#', '-');
                    input = input.replace('$', '+');
                    input = input.replace('[', '(');
                    input = input.replace(']', ')');

                    mDatabaseReference
                            .child("itens")
                            .child("lista")
                            .child(input)
                            .setValue(objectId);
                }
                finish();
            }
        });
        /**
         * Caso clique no cancelar, deve sair.
         */
        mCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
