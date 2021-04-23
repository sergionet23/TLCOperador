package com.woow.tlcoperador.ui.Monedero;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.woow.tlcoperador.R;
import com.woow.tlcoperador.model.Ocupar_lugar;
import com.woow.tlcoperador.ui.home.HomeViewModel;


public class MonederoFragment extends Fragment {

    private MonederoViewModel monederoViewModel;

    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();

    //Variable que se va utilizar para consultar los datos a firebase
    TextView textview_total, textview_nombre;

    int total_propinas;


    private HomeViewModel homeViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {



        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        // Inflate the layout for this fragment
        View root_monedero = inflater.inflate(R.layout.fragment_monedero, container, false);


        //Vinculo los campos del layout con las variables
        textview_total = root_monedero.findViewById(R.id.tv_total);
        textview_nombre = root_monedero.findViewById(R.id.tv_nombre);

        //Recibo datos de la pantalla anterior
        String ci_operador = "12345678";
        String nombre_opera = "Yooo";

        Query q = mRootReference.child("Ocupar_Lugar").orderByChild("CI_operador") .equalTo(ci_operador);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot transaccion : snapshot.getChildren()) {
                        Ocupar_lugar transacciones = transaccion.getValue(Ocupar_lugar.class);
                        int monto_propina = transacciones.getMonto_Propina();
                        total_propinas = total_propinas+monto_propina;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        textview_total.setText(" $"+total_propinas);
        textview_nombre.setText(""+nombre_opera);

        return root_monedero;
    }
}