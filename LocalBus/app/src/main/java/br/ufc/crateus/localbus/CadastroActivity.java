package br.ufc.crateus.localbus;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CadastroActivity extends AppCompatActivity {

    EditText etNome;
    EditText etEmail;
    EditText etCurso;
    EditText etMatricula;
    EditText etSenha;
    EditText etConfirmaSenha;
    Button btCadastrar;
    LineHolder lh = new LineHolder();
    UserService service = RetrofitClientInstance
            .getRetrofitInstance()
            .create(UserService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        etNome = (EditText) findViewById(R.id.etNome);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCurso = (EditText) findViewById(R.id.etCurso);
        etMatricula = (EditText) findViewById(R.id.etMatricula);
        etSenha = (EditText) findViewById(R.id.etSenha);
        etConfirmaSenha = (EditText) findViewById(R.id.etConfirmaSenha);
        btCadastrar = (Button) findViewById(R.id.btCadastrar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etNome.getText().toString().equals("") || etEmail.getText().toString().equals("") || etCurso.getText().toString().equals("") || etMatricula.getText().toString().equals("") || etSenha.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Verifique se todos os campos estão preenchidos!",
                            Toast.LENGTH_SHORT).show();
                } else if (!etSenha.getText().toString().equals(etConfirmaSenha.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "As senhas não conrrespondem!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Call<UserModel> callPut = service.putUser(new UserModel(etNome.getText().toString(), etEmail.getText().toString(), Integer.parseInt(etMatricula.getText().toString()), etCurso.getText().toString(), etSenha.getText().toString()), 1);
                    callPut.enqueue(new Callback<UserModel>() {
                        @Override
                        public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                            UserModel user = response.body();
                            Log.i("PUT", user.toString());
                            Toast.makeText(getApplicationContext(), "Usuário Cadastrado com Sucesso!",
                                    Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<UserModel> call, Throwable t) {

                        }
                    });

                }
            }
        });

    }

    public void exibeUsers() {
        final Call<List<UserModel>> callGetAll = service.getUsers();
        callGetAll.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                for (UserModel user : response.body()) {
                    Log.i("GET ALL", user.toString());
                }
            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {
            }
        });

    }
}
