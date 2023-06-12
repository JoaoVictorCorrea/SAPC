package com.sapc.sapcandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Trocar extends AppCompatActivity {

    EditText edtSenhaAnt, edtSenha, edtConfirmar;
    Button btnEnviar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Connection con = Conexao.Conectar();
        Log.d("MyAppTroca", "Debug 1");

        setContentView(R.layout.activity_trocar);

        edtSenhaAnt = findViewById(R.id.antiga);
        edtSenha = findViewById(R.id.nova);
        edtConfirmar = findViewById(R.id.confirmar);
        btnEnviar = findViewById(R.id.enviar);

        Intent myIntent = getIntent();
        int codAuthetication = myIntent.getIntExtra("CodAuthentication", 0);
        Log.d("MyAppTroca", "Debug 2" + codAuthetication);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String novaSenha = edtSenha.getText().toString();
                String antSenha = edtSenhaAnt.getText().toString();
                String confSenha = edtConfirmar.getText().toString();
                if(confSenha.equals(novaSenha)) {
                    try {
                        Statement stm = con.createStatement();
                        String queryChangePass = "UPDATE tb_morador SET senha ='" + novaSenha + "' WHERE senha ='" + antSenha + "' AND id=" + codAuthetication;
                        stm.executeUpdate(queryChangePass);
                        Toast.makeText(Trocar.this, "Senha alterada com sucesso!", Toast.LENGTH_LONG).show();
                    } catch (SQLException e) {
                        Log.e("MyAppTroca", e.getMessage());
                    }
                }else {
                    Toast.makeText(Trocar.this, "As senhas precisam ser iguais!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
