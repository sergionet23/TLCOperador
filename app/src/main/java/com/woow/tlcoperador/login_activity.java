package com.woow.tlcoperador;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.woow.tlcoperador.model.Operadores;

public class login_activity extends AppCompatActivity {

    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference refDatos = mRootReference;

    //Variable que se va utilizar para consultar los datos a firebase
    Button mButtonLoginFirebase;
    EditText eTcontrasena, eTcorreo;
    TextView textVId_con;

    String editTcorreo, editTcontrasena;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Vinculo los campos del layout con las variables
        mButtonLoginFirebase = findViewById(R.id.btnLogin);
        eTcorreo = findViewById(R.id.eTcorreoLogin);
        eTcontrasena = findViewById(R.id.eTcontrasenaLogin);
        textVId_con = findViewById(R.id.tvId_con);

//PAra loguearme rapido mientras desarrollo
//        String mail = "alguien_1@mail.com";
//        String pass = "abc123";

        //PAra loguearme rapido mientras desarrollo
//            String editTcorreo = mail.toString();
//            String editTcontrasena = pass.toString();



        // Genero el evento al hacer click en el boton iniciar sesion
        mButtonLoginFirebase.setOnClickListener(v -> {

            //Transformo los datos ingresados en variables string
            editTcorreo = eTcorreo.getText().toString();
            editTcontrasena = eTcontrasena.getText().toString();

            //Cierro el teclado
            cerrarTeclado();

            //Limpio los textbox
            if (eTcorreo.equals("") || eTcontrasena.equals("")) {
                validacion();
            } else {
                //Poner variable en String para pasar la consulta
                Query q = refDatos.child("Operadores").orderByChild("Mail").equalTo(editTcorreo);
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {

                            //Utilizo un for para recorrer el dataSnapshot
                            for (DataSnapshot operador : dataSnapshot.getChildren()) {
                                Operadores operadorSeleccionado = operador.getValue(Operadores.class);
                                String passw_operador = operadorSeleccionado.getPassword();
                                String nombre_operador = operadorSeleccionado.getNombre();
                                String ci_operador = operador.getKey();


                                //Utilizo el if para verficar si la contraseña ingresada es correcta
                                if (passw_operador.equals(editTcontrasena)) {
                                    textVId_con.setText("Bienvenido a la mejor aplicacion! ");


                                    //Paso los datos a la nueva activity, deberia ser el mapa
                                    Intent i = new Intent(login_activity.this, MainActivity.class);
                                    i.putExtra("nombre_operador", nombre_operador);
                                    i.putExtra("ci_operador_login", ci_operador);
                                    startActivity(i);

                                } else {
                                    textVId_con.setText("Usuario o contraseña incorrecta, intente nuevamente");
                                    limpiarCajas();
                                }
                            }
                        }else{
                            textVId_con.setText(""+editTcorreo+"-"+editTcontrasena);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        }

    public void registrarUsuario(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }



    //Metodo para validar campos vacios
    private void validacion() {
        String correo_ingresado = eTcorreo.getText().toString();
        String password_ingresada = eTcontrasena.getText().toString();

        if(correo_ingresado.equals("")){
            eTcorreo.setError("Required");
        }
        else if (password_ingresada.equals("")){
            eTcontrasena.setError("Required");
        }
    }


    //MEtodo para cerrar el teclado lugo que hagan click en iniciar sesion
    private void cerrarTeclado() {
        View view = this.getCurrentFocus();
        if(view!= null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }


    //Metodo para limpiar textbox
    private void limpiarCajas() {
        eTcorreo.setText("");
        eTcontrasena.setText("");
    }


}
