package br.unb.cic.mp.marketmaster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

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

        mCriaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mNomeItem.getText().toString();
                if (!input.equals("")) {
                    NovoItem item = new NovoItem(input);
                    mDatabaseReference.child("itens").child(input).push().setValue(item);
                }
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
