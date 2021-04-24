package com.woow.tlcoperador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.woow.tlcoperador.model.Ocupar_lugar;

import java.util.ArrayList;


public class TransaccionesAdapter extends RecyclerView.Adapter<TransaccionesAdapter.ViewHolder> {

    private int resourse;
    private ArrayList<Ocupar_lugar> transaccionesList;

    public TransaccionesAdapter(ArrayList<Ocupar_lugar> transaccionesList, int resourse){
        this.transaccionesList = transaccionesList;
        this.resourse = resourse;

    }

    @NonNull
    @Override
    public TransaccionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resourse, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int index) {
        Ocupar_lugar transaccion = transaccionesList.get(index);

        viewholder.textViewtransaccion.setText(transaccion.getFecha_hora_propina());
    }

    @Override
    public int getItemCount() {
        if (transaccionesList != null){
            return transaccionesList.size();
        }else{
            return 0;
        }

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewtransaccion;
        public View view;

        public ViewHolder(@NonNull View itemview) {
            super(itemview);

            this.view = view;
            this.textViewtransaccion = (TextView) itemview.findViewById(R.id.textViewTransacciones);
        }
    }





}
