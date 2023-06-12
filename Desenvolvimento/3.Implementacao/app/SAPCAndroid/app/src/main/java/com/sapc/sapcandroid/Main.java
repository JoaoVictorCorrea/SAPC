package com.sapc.sapcandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sapc.sapcandroid.databinding.ActivityMainBinding;
import com.sapc.sapcandroid.model.Conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {


    TextView txtTrocar, txtMensagem;
    int cod;
    ListView listView;
    String nome="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Integer> ids = new ArrayList<Integer>();
        List<String> nome_usuarios_externos = new ArrayList<String>();
        List<String> tipo_usuarios_externos = new ArrayList<String>();
        List<String> datas_solicitacoes = new ArrayList<String>();
        List<String> status = new ArrayList<String>();

        Connection con = Conexao.Conectar();
        Log.d("MyApp", "Debug 1");

        setContentView(R.layout.activity_main);
        txtTrocar = findViewById(R.id.trocar);
        txtMensagem = findViewById(R.id.mensagem);
        listView = findViewById(R.id.listview);

        Intent myIntent = getIntent();
        int codAuthetication = myIntent.getIntExtra("CodAuthentication", 0);
        Log.d("MyAppMain", "Debug 2 Codigo:" + codAuthetication);

        try {
            Statement stm = con.createStatement();
            String queryLogin = "SELECT s.id, m.nome, u.nome, s.tipo_usuario_externo, s.data_solicitacao, s.status from tb_solicitacaodeentrada s INNER JOIN tb_residencia r ON (s.residencia_id=r.id) INNER JOIN tb_morador m ON (m.residencia_id = r.id) INNER JOIN tb_usuarioexterno u ON (u.id=s.usuario_externo_id) WHERE m.id = "+ codAuthetication;
            ResultSet rs = stm.executeQuery(queryLogin);
            Log.d("MyAppMain", "Debug 3");


            while(rs.next()) {
                nome = rs.getString(2);
                ids.add(rs.getInt(1));
                nome_usuarios_externos.add(rs.getString(3));
                tipo_usuarios_externos.add(rs.getString(4));
                datas_solicitacoes.add(rs.getString(5));
                status.add(rs.getString(6));
                Log.d("MyAppMain", "Debug 4");
            }
        } catch (SQLException e) {
            Log.e("MyAppMain", e.getMessage());
        }

        txtMensagem.setText("Olá, " + nome);
        ArrayList<Solicitacao> solicitacaoArrayList = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++){
            Solicitacao solicitacao = new Solicitacao(nome_usuarios_externos.get(i), datas_solicitacoes.get(i), tipo_usuarios_externos.get(i), ids.get(i), status.get(i));
            solicitacaoArrayList.add(solicitacao);
            Log.d("MyAppMain", "Debug 5");
        }

        ListAdapter listAdapter = new ListAdapter(Main.this, solicitacaoArrayList);
        listView.setAdapter(listAdapter);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent soli = new Intent(getApplicationContext(), solicitacaotela.class);
                soli.putExtra("nome", nome);
                soli.putExtra("usuario", nome_usuarios_externos.get(position));
                soli.putExtra("tipo", tipo_usuarios_externos.get(position));
                soli.putExtra("id", ids.get(position));
                soli.putExtra("codAuthentication", codAuthetication);
                Log.e("solic", "FOI 1");
                startActivity(soli);
            }
        });

        Conexao.Desconectar(con);

        txtTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent troca = new Intent(getApplicationContext(), Trocar.class);
                troca.putExtra("CodAuthentication", codAuthetication);
                startActivity(troca);
            }
        });
    }
}