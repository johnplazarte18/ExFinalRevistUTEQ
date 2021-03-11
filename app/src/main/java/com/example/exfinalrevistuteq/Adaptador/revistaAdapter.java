package com.example.exfinalrevistuteq.Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.exfinalrevistuteq.Model.Revista;
import com.example.exfinalrevistuteq.R;
import com.mindorks.placeholderview.PlaceHolderView;

import java.util.ArrayList;
import java.util.List;

public class revistaAdapter extends RecyclerView.Adapter<revistaAdapter.RevistaViewHolder> {
    private Context Ctx;
    private List<Revista> lstRevista;

    public revistaAdapter(Context mCtx, ArrayList<Revista> usuarios) {
        this.lstRevista = usuarios;
        Ctx=mCtx;
    }

    @Override
    public RevistaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.itemrevista, null);
        return new RevistaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RevistaViewHolder holder, int position) {

        Revista usuario = lstRevista.get(position);

        holder.textViewDescripcion.setText(usuario.getDescripcion());
        holder.textViewPortada.setText(usuario.getPortada());

        Glide.with(Ctx)
                .load(usuario.getPortada())
                .into(holder.imageView);

    }


    @Override
    public int getItemCount() {
        return lstRevista.size();
    }


    class RevistaViewHolder extends PlaceHolderView.ViewHolder {

        TextView textViewDescripcion, textViewPortada;
        ImageView imageView;

        public RevistaViewHolder(View itemView) {
            super(itemView);

            textViewDescripcion= itemView.findViewById(R.id.txtDescripcion);
            textViewPortada = itemView.findViewById(R.id.imgPortada);
        }
    }

}

