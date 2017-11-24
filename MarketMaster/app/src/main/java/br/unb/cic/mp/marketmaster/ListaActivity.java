package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListaActivity extends AppCompatActivity {

    private Button mAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        mAddItem = findViewById(R.id.add_item); // TODO deu ruim aqui... aparentemente mAddItem tá nulo e não consegue ser instanciado
        mAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaActivity.this, AdicionarItensActivity.class);
                startActivity(intent);
            }
        });
    }
}
