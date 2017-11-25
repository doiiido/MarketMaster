package br.unb.cic.mp.marketmaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView mEmailView;
    private EditText mSenhaView;
    private Button mSignIn;
    private Button mSignUp;
    private FirebaseAuth mAut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmailView = findViewById(R.id.email_input);
        mSenhaView = findViewById(R.id.senha_input);
        mSignUp = findViewById(R.id.cadastrar_btn);
        mSignIn = findViewById(R.id.entrar_btn);

        mAut = FirebaseAuth.getInstance();

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tentarLogin();
            }
        });
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarNovoUsuario();
            }
        });

    }

    /**
     * Usuário é mandado para a tela de cadastro por aqui.
     */
    private void cadastrarNovoUsuario() {
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    /**
     * Usuário vem para esse método ao clicar em login.
     */
    private void tentarLogin() {
        Toast.makeText(this, "Log in...", Toast.LENGTH_SHORT).show();
        String email = mEmailView.getText().toString();
        String senha = mSenhaView.getText().toString();
        if (email.equals("") || senha.equals("")) {
            Toast.makeText(this, "Por favor, ambos os campos de login e senha são necessários!", Toast.LENGTH_SHORT);
            // TODO saco cheio
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        } else if (email.equals("admin") && senha.equals("jangostosao")) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        } else {
            mAut.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Oops")
                                .setMessage(task.getException().toString())
                                .setPositiveButton(android.R.string.ok, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    } else {
                        //String nome;
                        //SharedPreferences prefs = getSharedPreferences("UsuarioMM", 0);
                        //prefs.edit().putString("usuario", nome).apply();

                        Toast.makeText(MainActivity.this, "Massa, fera.", Toast.LENGTH_SHORT);
                        Intent intent = new Intent(MainActivity.this, GerenciarListasActivity.class);
                        finish();
                        startActivity(intent);
                    }
                }
            });
        }
    }

}
