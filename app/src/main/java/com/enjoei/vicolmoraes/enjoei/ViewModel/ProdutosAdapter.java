package com.enjoei.vicolmoraes.enjoei.ViewModel;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.enjoei.vicolmoraes.enjoei.Model.ProdutoVO;
import com.enjoei.vicolmoraes.enjoei.R;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProdutosAdapter extends RecyclerView.Adapter<ProdutosAdapter.ProdutosViewHolder> {
    private ArrayList<ProdutoVO> listaProdutos;
    private ItemClickListener mClickListener;


    public ProdutosAdapter(ArrayList<ProdutoVO> lista, ItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
        listaProdutos = lista;
        notifyDataSetChanged();
    }

    @Override
    public ProdutosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.produto_item, parent, false);
        return new ProdutosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutosViewHolder holder, int position) {

        holder.titulo.setText(listaProdutos.get(position).getTitle());
        holder.preco.setText(NumberFormat.getCurrencyInstance().format(listaProdutos.get(position).getPrice()));
        holder.likes.setText(String.valueOf(listaProdutos.get(position).getLikes_count()));
        Picasso.get().load(listaProdutos.get(position).getPhotos().get(0).gerarUrl()).into(holder.imagemProduto);
        Picasso.get().load(listaProdutos.get(position).getUser().getAvatar().gerarUrl()).into(holder.avatarUsuario);
        holder.posicao = position;
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public interface ItemClickListener {
        void click(int posicao);
    }

    public class ProdutosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView titulo;
        private TextView preco;
        private TextView likes;
        private ImageView imagemProduto;
        private CircleImageView avatarUsuario;
        private ConstraintLayout layout;
        private int posicao;

        ProdutosViewHolder(View itemView) {

            super(itemView);
            titulo = itemView.findViewById(R.id.tv_produto_item_titulo);
            preco = itemView.findViewById(R.id.tv_produto_item_valor);
            likes = itemView.findViewById(R.id.tv_produto_icone_like);
            imagemProduto = itemView.findViewById(R.id.iv_produto_item_foto);
            avatarUsuario = itemView.findViewById(R.id.civ_produto_avatar);
            layout = itemView.findViewById(R.id.cl_produto_item_layout);
            layout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.click(posicao);
        }
    }

}
