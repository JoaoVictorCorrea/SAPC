package com.sapc.sapcandroid.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sapc.sapcandroid.R;
import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class solicitacaotela extends AppCompatActivity {

    TextView txtSoli;
    Button btnAceitar, btnRecusar;
    String nome, usuario, tipo, data;
    int id, cod, idExterno;

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
            idExterno = intent.getIntExtra("idExterno", 0);
            cod = intent.getIntExtra("codAuthentication", 0);
            data = intent.getStringExtra("data");
        }

        txtSoli = findViewById(R.id.txtSoli);
        txtSoli.setText("Olá " + nome +", "+ usuario + " está solicitando acesso para " + tipo);

        btnAceitar = findViewById(R.id.btnAceitar);
        btnRecusar = findViewById(R.id.btnRecusar);

        btnAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Statement stm = con.createStatement();
                    String queryAutorize = "UPDATE tb_solicitacaodeentrada SET status_solicitacao = 'AUTORIZADO' WHERE id=" + id;
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    Log.e("Teste Data", now.toString());
                    String queryAutorizado = "INSERT into tb_autorizacaodeentrada(`data_autorizacao`,`morador_id`,`solicitacao_id`) VALUES('"+now+"',"+cod+","+id+")";
                    String queryFluxo = "INSERT into tb_fluxo(`data_entrada`,`usuario_externo_id`) VALUES('"+data+"',"+idExterno+")";
                    stm.executeUpdate(queryAutorize);
                    stm.executeUpdate(queryAutorizado);
                    stm.executeUpdate(queryFluxo);
                    Toast.makeText(solicitacaotela.this, "Entrada autorizada!", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(solicitacaotela.this, Main.class);
                    main.putExtra("CodAuthentication", cod);
                    startActivity(main);
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
                    String queryReject = "UPDATE tb_solicitacaodeentrada SET status_solicitacao = 'RECUSADO' WHERE id=" + id;
                    stm.executeUpdate(queryReject);
                    Toast.makeText(solicitacaotela.this, "Entrada Recusada!", Toast.LENGTH_LONG).show();
                    Intent main = new Intent(solicitacaotela.this, Main.class);
                    main.putExtra("CodAuthentication", cod);
                    startActivity(main);
                } catch (SQLException e) {
                    Log.e("solicitacaoAPP", e.getMessage());
                }
            }
        });
    }
}