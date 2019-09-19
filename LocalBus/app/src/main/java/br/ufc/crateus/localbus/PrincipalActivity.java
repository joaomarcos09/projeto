package br.ufc.crateus.localbus;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class PrincipalActivity extends AppCompatActivity {


    Button btMapa;
    Button btBatePapo;
    Button btMeusDados;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btMapa = (Button) findViewById(R.id.btMapa);
        btBatePapo = (Button) findViewById(R.id.btBatePapo);
        btMeusDados = (Button) findViewById(R.id.btDados);
    }
}
