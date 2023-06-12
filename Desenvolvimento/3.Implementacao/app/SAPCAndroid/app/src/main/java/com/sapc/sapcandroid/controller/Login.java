package com.sapc.sapcandroid.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sapc.sapcandroid.R;
import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Login extends AppCompatActivity {

    EditText edtEmail, edtSenha;
    TextView txtLogin;
    Button btnLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.email);
        edtSenha = findViewById(R.id.password);
        btnLogin = findViewById(R.id.submit);
        txtLogin = findViewById(R.id.txtLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection con = Conexao.Conectar();
                Log.d("MyApp", "Debug 1");
                if(con != null){
                    String email = edtEmail.getText().toString();
                    String senha = edtSenha.getText().toString();
                    String records="";
                    Integer cod;
                    Log.d("MyAppLogin", "Debug 2");

                    try {
                        Statement stm = con.createStatement();
                        String queryLogin = "SELECT * FROM tb_morador WHERE email ='"+email+"' AND senha='"+senha+"'";
                        ResultSet rs = stm.executeQuery(queryLogin);
                        Log.d("MyAppLogin", "Debug 3");

                        if(rs.next()){
                            Intent intent = new Intent(getApplicationContext(), Main.class);
                            cod = Integer.valueOf(rs.getString(1));
                            intent.putExtra("CodAuthentication", cod);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Login.this, "Erro ao logar: Usuário ou Senha incorretos", Toast.LENGTH_LONG).show();
                        }
                    } catch (SQLException e) {
                        Log.e("MyAppLogin", e.getMessage());
                    }
                    Conexao.Desconectar(con);
                }
            }
        });


    }
}