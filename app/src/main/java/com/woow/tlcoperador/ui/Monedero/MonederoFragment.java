package com.woow.tlcoperador.ui.Monedero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.woow.tlcoperador.R;
import com.woow.tlcoperador.TransaccionesAdapter;
import com.woow.tlcoperador.model.Ocupar_lugar;
import com.woow.tlcoperador.ui.home.HomeViewModel;

import java.util.ArrayList;


public class MonederoFragment extends Fragment {

    private MonederoViewModel monederoViewModel;

    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();

    //Traigo la referencia al adapter del recycler view
    private TransaccionesAdapter tAdapter;
    //   private RecyclerView tRecyvlerView;
    private ArrayList<Ocupar_lugar> tTransaccionList = new ArrayList<>();


    //Variable que se va utilizar para consultar los datos a firebase
    TextView textview_total, textview_nombre;

    int total_propinas = 135;

    private HomeViewModel homeViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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


        //Para un recycler view en un futuro
        //      tRecyvlerView = root_monedero.findViewById(R.id.recylcerViewTransac);

        //    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //  tRecyvlerView.setLayoutManager(layoutManager);

        //   LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //   layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //    tRecyvlerView.setLayoutManager(layoutManager);

        //       tAdapter = new TransaccionesAdapter (tTransaccionList, R.layout.transaccionesview);
        //      tRecyvlerView.setAdapter(tAdapter);

        //tL .setLayoutManager(new LinearLayoutManager(this));
        //  LinearLayoutManager llm = LinearLayoutManager(this);
        //llm.setOrientation(LinearLayoutManager.VERTICAL);


        //Recibo datos de la pantalla anterior
        int ci_operador = 12345678;
        String nombre_opera = "Juan";


        Query q = mRootReference.child("Ocupar_Lugar").orderByChild("CI_operador").equalTo(ci_operador);
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total_propinas=1;
                if (snapshot.exists()) {
                    //int suma = 0;
           //         textview_total.setText("$ax " + total_propinas);
//                    for (DataSnapshot transaccion : snapshot.getChildren()) {
//                        Ocupar_lugar transacciones = transaccion.getValue(Ocupar_lugar.class);
//                        String tran = transacciones.getId_transaccion();


                                                //total_propinas +=value;
                        //suma += transaccion.child("Monto_propina").getValue(Integer.class);
//
                        //                      int monto_propina = transacciones.getMonto_Propina();

                        //                    total_propinas =+monto_propina;

//                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //    getTransaccionesFromFirebase();

        textview_total.setText(" $ "+ total_propinas);
        textview_nombre.setText("Hola, " + nombre_opera);


        return root_monedero;
    }
}
/*
    //El metodo para obtener datos de firebase
    private void getTransaccionesFromFirebase(){
        mRootReference.child("Ocupar_Lugar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot transaccion : snapshot.getChildren()) {
                        String transac = transaccion.child("Fecha_hora_propina").getValue().toString();
                        tTransaccionList.add(new Ocupar_lugar());
                    }

                    tAdapter = new TransaccionesAdapter(tTransaccionList, R.layout.transaccionesview);
                    tRecyvlerView.setAdapter(tAdapter);

                    }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}

 */