package com.sapc.sapcandroid.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sapc.sapcandroid.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Solicitacao> solicitacaoArrayList;
    LayoutInflater inflater;

    public ListAdapter(Context ctx, ArrayList<Solicitacao> soso){
        this.context = ctx;
        this.solicitacaoArrayList = soso;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return solicitacaoArrayList.size();
    }

    @Override
    public Solicitacao getItem(int position) {
        return solicitacaoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return solicitacaoArrayList.get(position).id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_item, null);

        Solicitacao solicitacao = getItem(position);

        TextView userSolicName = convertView.findViewById(R.id.nome_solicitante);
        TextView userTypeName = convertView.findViewById(R.id.tipo_solicitante);
        TextView data = convertView.findViewById(R.id.data);
        TextView status = convertView.findViewById(R.id.txtStatus);

        userSolicName.setText(solicitacao.usuario_externo_nome);
        String tipoFormatado = solicitacao.tipo.replace("_", " ");
        userTypeName.setText(tipoFormatado);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault());
        try {
            Date date = inputFormat.parse(solicitacao.data);
            String dataFormatada = outputFormat.format(date);
            data.setText(dataFormatada);
        } catch (ParseException e) {
            e.printStackTrace();
            data.setText(solicitacao.data);
        }
        status.setText(solicitacao.status);
        if(solicitacao.status.equals("AUTORIZADO")){
            status.setTextColor(Color.parseColor("#6AFF00"));
        } else if (solicitacao.status.equals("RECUSADO")) {
            status.setTextColor(Color.parseColor("#FF0000"));
        }else {
            status.setTextColor(Color.parseColor("#EDFF00"));
        }

        return convertView;
    }
}
