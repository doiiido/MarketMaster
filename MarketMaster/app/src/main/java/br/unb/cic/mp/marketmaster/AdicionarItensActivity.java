package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Classe da tela de adicionar itens
 */
public class AdicionarItensActivity extends AppCompatActivity {

    private Button mVoltar;
    private Button mCriaItem;
    private Button mAddItem;
    private ArrayList<DataSnapshot> mListaItens;
    private Spinner mItensDisponiveis;
    private DatabaseReference mDatabaseReference;
    private int mPosicaoSelecionada = 0;
    private AdapterView.OnItemSelectedListener spinnerListener;
    ArrayList<String> mNomes;
    String mObjectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_itens);

        /**
         *  lista de itens que sera feita pelo usuário
         */
        mListaItens = new ArrayList<>();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mListaItens.add(dataSnapshot);
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

        spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPosicaoSelecionada = i;
                Log.d("MarketMaster", mListaItens.get(i) + " selecionado - posição " + mPosicaoSelecionada + " com i = " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabaseReference
                        .child("itens")
                        .child("lista")
                        .child(mNomes.get(mPosicaoSelecionada))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        SharedPreferences prefs = getSharedPreferences("UsuarioMM", 0);

                        // Firebase não gosta desses caracteres
                        String emailPath = prefs.getString("email", null);
                        emailPath = emailPath.replace('.', '_');
                        emailPath = emailPath.replace('#', '-');
                        emailPath = emailPath.replace('$', '+');
                        emailPath = emailPath.replace('[', '(');
                        emailPath = emailPath.replace(']', ')');

                        Log.d("MarketMaster", "Item " + prefs.getString("lista", null));

                        mObjectId = dataSnapshot.getValue(String.class);
                        Log.d("MarketMaster", "ObjectID = " + mObjectId);

                        mDatabaseReference
                                .child("usuarios")
                                .child(emailPath)
                                .child("listas")
                                .child("conteudo")
                                .child(prefs.getString("lista", null))
                                .child(mNomes.get(mPosicaoSelecionada))
                                .setValue(mObjectId);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        mNomes = new ArrayList<>();
        for (int i = 0; i < mListaItens.size(); ++i) {
            mNomes.add(mListaItens.get(i).getValue(NovoItem.class).getNome());
        }

        ArrayAdapter adaptador = new ArrayAdapter<>(AdicionarItensActivity.this, android.R.layout.simple_spinner_item, mNomes);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mItensDisponiveis.setAdapter(adaptador);

        mItensDisponiveis.setOnItemSelectedListener(spinnerListener);

    }
}
