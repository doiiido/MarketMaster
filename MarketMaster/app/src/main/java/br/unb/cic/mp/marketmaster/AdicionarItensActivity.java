package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdicionarItensActivity extends AppCompatActivity {

    private Button mVoltar;
    private Button mCriaItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_itens);

        mVoltar = findViewById(R.id.voltar);
        mCriaItem = findViewById(R.id.cria_item);

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
    }
}
