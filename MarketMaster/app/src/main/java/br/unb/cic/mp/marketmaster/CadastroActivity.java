package br.unb.cic.mp.marketmaster;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroActivity extends AppCompatActivity {

    private AutoCompleteTextView mNomeView;
    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private AutoCompleteTextView mCepView;
    private Button mBotaoConclui;

    private FirebaseAuth mAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mNomeView = (AutoCompleteTextView) findViewById(R.id.nome_input);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_input);
        mSenhaView = (EditText) findViewById(R.id.senha_input);
        mCepView = (AutoCompleteTextView) findViewById(R.id.cep_input);
        mBotaoConclui = findViewById(R.id.botao_concluir);

        mAut = FirebaseAuth.getInstance();

        mBotaoConclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarSignUp();
            }
        });

    }

    private void tentarSignUp() {

        mEmailView.setError(null);
        mSenhaView.setError(null);

        String nome = mNomeView.getText().toString();
        String email = mEmailView.getText().toString();
        String senha = mSenhaView.getText().toString();
        String cep = mCepView.getText().toString();

        boolean cancela = false;
        View focusView = null;

        if (TextUtils.isEmpty(nome)) {
            mNomeView.setError("Campo do nome vazio!");
            focusView = mNomeView;
            cancela = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Campo do email vazio!");
            focusView = mEmailView;
            cancela = true;
        }
        if (TextUtils.isEmpty(senha)) {
            mSenhaView.setError("Campo da senha vazio!");
            focusView = mSenhaView;
            cancela = true;
        }
        if (TextUtils.isEmpty(cep)) {
            mCepView.setError("Campo do CEP vazio!");
            focusView = mCepView;
            cancela = true;
        }

        if (cancela) {
            focusView.requestFocus();
        } else {
            criaUsuarioFirebase();
        }
    }

    private void criaUsuarioFirebase() {
        String email = mEmailView.getText().toString();
        String senha = mSenhaView.getText().toString();
        mAut.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    new AlertDialog.Builder(CadastroActivity.this)
                            .setTitle("Oops")
                            .setMessage(task.getException().toString())
                            .setPositiveButton(android.R.string.ok, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    Intent intent = new Intent(CadastroActivity.this, MainActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

}
