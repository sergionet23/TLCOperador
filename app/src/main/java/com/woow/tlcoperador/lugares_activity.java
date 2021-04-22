package com.woow.tlcoperador;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.woow.tlcoperador.model.Operadores;

public class lugares_activity extends AppCompatActivity {


    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference refDatos = mRootReference;

    //Variable que se va utilizar para consultar los datos a firebase
    Button ButtonSumar, ButtonRestar;
    TextView textViewCantidadLugares;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);


        //Vinculo los campos del layout con las variables
        ButtonSumar = findViewById(R.id.btnSumar);
        ButtonRestar = findViewById(R.id.btnRestar);
        textViewCantidadLugares = findViewById(R.id.tVCantidadLugares);

        //Aca recibimos la id del operador que ingreso
        Bundle extras = getIntent().getExtras();
        String ci_operador_ingreso = extras.getString("ci_operador");
        String nombre_operador_ingreso = extras.getString("nombre_operador");

        //Busco los datos del operador que ingreso
        Query query = refDatos.child("Operadores").orderByChild("CI_operador").equalTo(ci_operador_ingreso);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot operador : snapshot.getChildren()) {
                        Operadores operadorSeleccionadoLugares = operador.getValue(Operadores.class);
                        Integer cantidad_lugares_restantes = operadorSeleccionadoLugares.getCantidad_restante_lugares();
                        //String nombre_operador = operadorSeleccionadoLugares.getNombre();

                        textViewCantidadLugares.setText(cantidad_lugares_restantes);
                            }
                        }
                    }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
