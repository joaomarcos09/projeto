package br.ufc.crateus.localbus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.Principal;

public class MainActivity extends AppCompatActivity {

    Button btEntrar;
    Button btCadastrar;
    EditText etEmail;
    EditText etSenha;
    TextView tvMsgCadastro;
    LineHolder lh = new LineHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btEntrar = (Button) findViewById(R.id.btEntrar);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean verifica = verificaLogin(etEmail.getText().toString(), etSenha.getText().toString());
                if(verifica) {
                    Intent i = new Intent(MainActivity.this, PrincipalActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(getApplicationContext(),"Verifique os dados de login",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(i);
            }
        });

    }
    public boolean verificaLogin(String email, String senha){
        int i;
        UserModel usu = lh.getUsuario(email);

        if(usu == null){
            return false;
        }
        if(usu.getSenha().toString().equals(senha)){
            return true;
        }else {
            return false;
        }
    }
}