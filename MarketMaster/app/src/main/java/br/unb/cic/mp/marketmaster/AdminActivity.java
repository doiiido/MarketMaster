package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Esta é a classe do Administrador do Banco de dados, ou de um possível dono de um estabelecimento
 * que opte por usar o aplicativo.
 */

public class AdminActivity extends AppCompatActivity {

    private Button mCriaMercado;
    private Button mCriaItem;
    private Button mItemMercado;
    private Button mVolta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mCriaMercado = findViewById(R.id.cria_mercado);
        mCriaItem = findViewById(R.id.cria_item);
        mItemMercado = findViewById(R.id.item_mercado_btn);
        mVolta = findViewById(R.id.volta);

        /**
         * Quando clicar no botão de Criar mercado
         */
        mCriaMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        /**
         * Quando clicar no botão cria item, deve ser redirecionado para o Cria Item Activity
         */
        mCriaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, CriaItemActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Caso clique no Item do Mercado
         */
        mItemMercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
