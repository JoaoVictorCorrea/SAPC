package com.sapc.sapcandroid;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

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
        userTypeName.setText(solicitacao.tipo);
        data.setText(solicitacao.data);
        status.setText(solicitacao.status);
        if(solicitacao.status.equals("AUTORIZADO")){
            status.setTextColor(Color.parseColor("#6AFF00"));
        } else if (solicitacao.status.equals("RECUSADO")) {
            status.setTextColor(Color.parseColor("#FF0000"));
        }else {
            status.setTextColor(Color.parseColor("#B5B5B5"));
        }

        return convertView;
    }
}
