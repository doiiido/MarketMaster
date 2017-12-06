package br.unb.cic.mp.marketmaster;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {

    private AutoCompleteTextView mNomeView;
    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private AutoCompleteTextView mCepView;
    private Button mBotaoConclui;

    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        mNomeView = findViewById(R.id.nome_input);
        mEmailView = findViewById(R.id.email_input);
        mSenhaView = findViewById(R.id.senha_input);
        mCepView = findViewById(R.id.cep_input);
        mBotaoConclui = findViewById(R.id.botao_concluir);

        mAut = FirebaseAuth.getInstance();
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        /**
         * Caso clique no concluir cadastro, deve então checar se os dados inseridos são válidos.
         * Email sem '@' não é valido, caso os campos obrigadorios forem vazis, também será inválido
         */
        mBotaoConclui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarSignUp();
            }
        });

    }

    /**
     * Este é o metodo que checa a validade dos campos inseridos no cadastro. Caso algum for inválido
     * será mostrado um erro, dependendo de qual for, e o cadastro não será efetuado. Se tudo estiver correto
     * o usuário terá um cadastro no banco de dados e poderá entrar no aplicativo.
     */
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

    /**
     * Este método persiste o usuário no banco de dados Firebase.
     */
    private void criaUsuarioFirebase() {
        final String email = mEmailView.getText().toString();
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
                    String nome = mNomeView.getText().toString();
                    String cep = mCepView.getText().toString();
                    NovoUsuario usuario = new NovoUsuario(nome, email, cep);

                    // Firebase não gosta desses caracteres
                    String emailPath = email;
                    emailPath = emailPath.replace('.', '_');
                    emailPath = emailPath.replace('#', '-');
                    emailPath = emailPath.replace('$', '+');
                    emailPath = emailPath.replace('[', '(');
                    emailPath = emailPath.replace(']', ')');

                    mDatabaseReference
                            .child("usuarios")
                            .child(emailPath)
                            .child("info")
                            .push().setValue(usuario);

                    SharedPreferences prefs = getSharedPreferences("UsuarioMM", 0);
                    prefs.edit().putString("usuario", nome).apply();
                    prefs.edit().putString("email", email).apply();

                    Intent intent = new Intent(CadastroActivity.this, GerenciarListasActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

}
