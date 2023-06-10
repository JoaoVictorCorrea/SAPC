package com.sapc.sapcandroid.model;

import android.os.StrictMode;
import android.util.Log;

import java.sql.*;

public class Conexao {
    private static final String DATABASE="sapc";
    private static final String HOST="35.198.27.2:3306";
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String
            URL="jdbc:mysql://"+HOST+"/"+DATABASE;
    private static final String USR="root";
    private static final String PWD="Mura2023Bcc5";
    private static String error = "";


    public static Connection Conectar(){
        try {
            Class.forName(DRIVER);
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            return DriverManager.getConnection(URL, USR,PWD);
        } catch (ClassNotFoundException | SQLException e) {
            //System.out.println("ERRO: " + e.getMessage());
            error = e.getMessage();
            Log.e("MyAPP", error);
            return null;
        }
    }

    public static void Desconectar(Connection con){
        try {
            if ( con != null){
                con.close();
            }
        } catch (SQLException e) {
            //System.out.println("ERRO: "  + e.getMessage());
            error = e.getMessage().toString();
            Log.e("MyAPP", error);
        }
    }
}
