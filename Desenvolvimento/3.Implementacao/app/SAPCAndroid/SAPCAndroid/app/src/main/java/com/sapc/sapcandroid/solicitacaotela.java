package com.sapc.sapcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class solicitacaotela extends AppCompatActivity {

    TextView txtSoli;
    Button btnAceitar, btnRecusar;
    String nome, usuario, tipo;
    int id;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitacaotela);

        Connection con = Conexao.Conectar();

        Intent intent = this.getIntent();
        if(intent != null){
            nome = intent.getStringExtra("nome");
            usuario = intent.getStringExtra("usuario");
            tipo = intent.getStringExtra("tipo");
            id = intent.getIntExtra("id", 0);
        }

        txtSoli = findViewById(R.id.txtSoli);
        txtSoli.setText("Olá " + nome +", o "+ usuario + " está solicitando acesso para " + tipo);

        btnAceitar = findViewById(R.id.btnAceitar);
        btnRecusar = findViewById(R.id.btnRecusar);

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Statement stm = con.createStatement();
                    String queryAutorize = "UPDATE tb_solicitacaodeentrada SET status = 'AUTORIZADO' WHERE id=" + id;
                    stm.executeUpdate(queryAutorize);
                    Toast.makeText(solicitacaotela.this, "Entrada autorizada!", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Log.e("solicitacaoAPP", e.getMessage());
                }
            }
        });

        btnRecusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Statement stm = con.createStatement();
                    String queryReject = "UPDATE tb_solicitacaodeentrada SET status = 'RECUSADO' WHERE id=" + id;
                    stm.executeUpdate(queryReject);
                    Toast.makeText(solicitacaotela.this, "Entrada Recusada!", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Log.e("solicitacaoAPP", e.getMessage());
                }
            }
        });
    }
}