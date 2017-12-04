package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Space;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class AdicionarItensActivity extends AppCompatActivity {

    private Button mVoltar;
    private Button mCriaItem;
    private Button mAddItem;
    private ArrayList<String> mListaItens;
    private Spinner mItensDisponiveis;
    private DatabaseReference mDatabaseReference;
    private int mPosicaoSelecionada = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_itens);

        mListaItens = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mListaItens.add(dataSnapshot.getValue(NovoItem.class).getNome());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference
                .child("itens")
                .child("conteudo")
                .addChildEventListener(listener);

        mVoltar = findViewById(R.id.voltar);
        mCriaItem = findViewById(R.id.cria_item);
        mAddItem = findViewById(R.id.add_item);

        mVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mCriaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdicionarItensActivity.this, CriaItemActivity.class);
                startActivity(intent);
            }
        });

        mItensDisponiveis = findViewById(R.id.itens_disp);
        mItensDisponiveis.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPosicaoSelecionada = i;
                mItensDisponiveis.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapter adaptador = new ArrayAdapter<>(AdicionarItensActivity.this, android.R.layout.simple_spinner_item, mListaItens);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItensDisponiveis = findViewById(R.id.itens_disp);
        mItensDisponiveis.setAdapter(adaptador);

    }
}
