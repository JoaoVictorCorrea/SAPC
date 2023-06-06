package com.sapc.sapcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends AppCompatActivity {

    TextView txtTrocar, txtMensagem;
    int cod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Connection con = Conexao.Conectar();
        Log.d("MyApp", "Debug 1");

        setContentView(R.layout.activity_main);
        txtTrocar = findViewById(R.id.trocar);
        txtMensagem = findViewById(R.id.mensagem);
        String nome="";

        Intent myIntent = getIntent();
        int codAuthetication = myIntent.getIntExtra("CodAuthentication", 0);
        Log.d("MyAppMain", "Debug 2" + codAuthetication);

        try {
            Statement stm = con.createStatement();
            String queryLogin = "SELECT id, nome FROM tb_morador WHERE id =" + codAuthetication;
            ResultSet rs = stm.executeQuery(queryLogin);
            Log.d("MyAppMain", "Debug 3");

            if(rs.next()){
                Intent intent = new Intent(getApplicationContext(), Main.class);
                cod = Integer.valueOf(rs.getString(1));
                nome = rs.getString(2);
            }else{
                Toast.makeText(getBaseContext(), "Erro ao logar", Toast.LENGTH_LONG);
            }
        } catch (SQLException e) {
            Log.e("MyAppMain", e.getMessage());
        }

        txtMensagem.setText("Olá, " + nome);
        Conexao.Desconectar(con);

        txtTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTroca = new Intent(getApplicationContext(), Trocar.class);
                intentTroca.putExtra("CodAuthentication", cod);
                startActivity(intentTroca);
                finish();
            }
        });
    }
}