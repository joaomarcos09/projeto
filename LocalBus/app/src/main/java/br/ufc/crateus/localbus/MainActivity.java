package br.ufc.crateus.localbus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.Principal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btEntrar;
    Button btCadastrar;
    EditText etEmail;
    EditText etSenha;
    UserModel usuario;
    UserModel userAux;
    TextView tvMsgCadastro;
    DatabaseReference myRef;
    Iterable<DataSnapshot> children;
    int cont = 0;
    UserService service = RetrofitClientInstance
            .getRetrofitInstance()
            .create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btEntrar = (Button) findViewById(R.id.btEntrar);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://localbus-5f2fa.firebaseio.com/");
        myRef = database.getReference("users");


        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        children = dataSnapshot.getChildren();
                        for (DataSnapshot data : children) {
                            Log.i("User", data.getKey() + " " + data.getValue(UserModel.class));
                            userAux = data.getValue(UserModel.class);
                            Log.i("emailT", etEmail.getText().toString());
                            Log.i("senhaT", etSenha.getText().toString());
                            if (etEmail.getText().toString().equals(userAux.getEmail()) && etSenha.getText().toString().equals(userAux.getSenha())) {
                                usuario = userAux;
                                Log.i("email", usuario.getEmail());
                                Log.i("senha", usuario.getSenha());
                            } else {
                                usuario = null;
                            }
                            login(usuario);
                        }
                        if(cont == 0){
                            Toast.makeText(getApplicationContext(), "verifique as configurações de login!",
                                    Toast.LENGTH_SHORT).show();
                        }


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.i("emailT", "veio pra ca");
                    }
                });
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
    public void login(UserModel usu){
        if (usu != null) {
            Intent principal = new Intent(MainActivity.this, PrincipalActivity.class);
            startActivity(principal);
            cont = 1;
        } else {
            cont = 0;
        }
    }
}
