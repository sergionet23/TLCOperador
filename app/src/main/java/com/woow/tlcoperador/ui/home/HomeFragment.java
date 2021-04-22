package com.woow.tlcoperador.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.woow.tlcoperador.R;
import com.woow.tlcoperador.model.Operadores;

public class HomeFragment extends Fragment {

    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference refDatos = mRootReference;

    //Variable que se va utilizar para consultar los datos a firebase
    Button ButtonSumar, ButtonRestar;
    TextView textViewCantidadLugares;

    int cantidad_lugares_restantes;
    String ci_operador_key;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);


        //Vinculo los campos del layout con las variables
        ButtonSumar = root.findViewById(R.id.btnSumar);
        ButtonRestar = root.findViewById(R.id.btnRestar);
        textViewCantidadLugares = root.findViewById(R.id.tVCantidadLugares);


    //    Bundle bundle = this.getArguments();
  //      String ci_operador_home_frag = bundle.getString("ci_operador_main","no");

        //Recibo la info de la activity anterior
         int ci_operador = 12345678 ;
                //getArguments().getInt("ci_operador_frag",0);
        //String nombre_operador = getArguments().getString("nombre_operador_frag");


        //Busco los datos del operador que ingreso
        Query query = refDatos.child("Operadores").orderByChild("CI_operador").equalTo(ci_operador);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot operador : snapshot.getChildren()) {
                        Operadores operadorSeleccionadoLugares = operador.getValue(Operadores.class);
                        cantidad_lugares_restantes = operadorSeleccionadoLugares.getCantidad_restante_lugares();
                        ci_operador_key = operador.getKey();
                        //String nombre_operador = operadorSeleccionadoLugares.getNombre();
                        textViewCantidadLugares.setText(""+cantidad_lugares_restantes);
                    }
                } else {
                    textViewCantidadLugares.setText(" No " + ci_operador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //Si clickeo en el mas, sumo lugares
        ButtonSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad_lugares_restantes++;
                refDatos.child("Operadores").child(ci_operador_key).child("Cantidad_restante_lugares").setValue(cantidad_lugares_restantes);

                textViewCantidadLugares.setText(""+cantidad_lugares_restantes);
            }
        });


        //Si clickeo en el menos, resto lugares
        ButtonRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidad_lugares_restantes--;
                refDatos.child("Operadores").child(ci_operador_key).child("Cantidad_restante_lugares").setValue(cantidad_lugares_restantes);
                textViewCantidadLugares.setText(""+cantidad_lugares_restantes);

            }
        });



        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}