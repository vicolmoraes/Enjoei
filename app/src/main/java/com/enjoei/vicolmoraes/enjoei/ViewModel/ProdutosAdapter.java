package com.enjoei.vicolmoraes.enjoei.ViewModel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.enjoei.vicolmoraes.enjoei.R;

import java.util.ArrayList;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder> {
    private ArrayList<String> listaFiltros;

    public ProdutosAdapter(ArrayList<String> lista) {

        listaFiltros = lista;
        notifyDataSetChanged();
    }

    @Override
    public ProdutosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_item, parent, false);
        return new ProdutosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosViewHolder holder, int position) {

        holder.texto.setText(listaFiltros.get(position));

    }

    @Override
    public int getItemCount() {
        return listaFiltros.size();
    }

    public class ProdutosViewHolder extends RecyclerView.ViewHolder {
        private TextView texto;

        ProdutosViewHolder(View itemView) {

            super(itemView);
            texto = itemView.findViewById(R.id.tv_filtro);
        }
    }
}
