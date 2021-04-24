package com.woow.tlcoperador;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.woow.tlcoperador.model.Operadores_sin_validar;

import javax.security.auth.login.LoginException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    //Se crea variable para hacer referencia a la base de datos
    DatabaseReference mRootReference;

    //Declaro variables para los campos del formulario
    EditText editTextcioperador, editTextNombre, editTextApellido, editTextTelefono, editTextCorreo, editTextPass;
    EditText editTextPassC, editTextTipo_operador, editTextNombre_ubicacion;
    TextView textVmensaje,textVmensaje_1;

    //Variable que se va utilizar para subir los datos a firebase
    Button mButtonSubirDatosFirebase;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase connections
        mRootReference = FirebaseDatabase.getInstance().getReference();


        //Se vincula boton de la activity con la variable creada aqui
        mButtonSubirDatosFirebase = findViewById(R.id.btnCrearOperador);
        mButtonSubirDatosFirebase.setOnClickListener(this);

        editTextcioperador = findViewById(R.id.eTcioperador);
        editTextNombre = findViewById(R.id.eTNombre);
        editTextApellido = findViewById(R.id.eTApellido);
        editTextTelefono = findViewById(R.id.eTTelefono);
        editTextCorreo = findViewById(R.id.eTCorreo);
        editTextPass = findViewById(R.id.eTPass);
        editTextPassC = findViewById(R.id.eTPassC);
        editTextTipo_operador= findViewById(R.id.eTTipo_operador);
   //     editTextNombre_ubicacion = findViewById(R.id.eTNombre_ubicacion);

        textVmensaje = findViewById(R.id.tVmensaje);
        textVmensaje_1 = findViewById(R.id.tv_mensaje_1);
    }


    @Override
    public void onClick(View v) {

        //Vinculo las variables creadas con los campos de la base de datos.

        String CI_operador = editTextcioperador.getText().toString();
        String Nombre = editTextNombre.getText().toString();
        String Apellido = editTextApellido.getText().toString();
        String Telefono = editTextTelefono.getText().toString();
        String Mail_ingresado = editTextCorreo.getText().toString();
        String Password = editTextPass.getText().toString();
        String PasswordC = editTextPassC.getText().toString();
        String Tipo_operador = editTextTipo_operador.getText().toString();
       // String Nombre_ubicacion = editTextNombre_ubicacion.getText().toString();
        Boolean Estado = false;

        //Cierro el teclado
        cerrarTeclado();

        //Validacion si esta vacio
        if (CI_operador.equals("") || Nombre.equals("")|| Apellido.equals("")|| Mail_ingresado.equals("")|| Telefono.equals("")|| Password.equals("")||
                PasswordC.equals("") || Tipo_operador.equals("")){
            validacion_vacio();
        } else{
            Query q = mRootReference.child("Operadores_sin_validar").orderByChild("CI_operador").equalTo(CI_operador);
            q.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    //Si me da un resultado la query anterior aviso que el correo ya existe
                    if (snapshot.exists()) {
                        textVmensaje.setText("La Cedula ingresada ya existe en los registros de Te Lo Cuido.");
                    } else {
                        // VERIFICO SI LAS CONTRASEÑAS COINCIDEN
                        if (Password.equals(PasswordC)) {
                            // cargo la informacion para realizar el insert en la base de datos.
                            Operadores_sin_validar o = new Operadores_sin_validar();

                            o.setCI_operador(CI_operador);
                            o.setNombre(Nombre);
                            o.setApellido(Apellido);
                            o.setTelefono(Telefono);
                            o.setMail(Mail_ingresado);
                            o.setPassword(Password);
                            o.setEstado(Estado);
                            o.setTipo_operador(Tipo_operador);
                    //        o.setNombre_ubicacion(Nombre_ubicacion);

                            //Aca indico como guardar los datos en la bd, nodo persona con el nodo id dentro.
                            mRootReference.child("Operadores_sin_validar").child(o.getCI_operador()).setValue(o);
                            textVmensaje_1.setText("Registrado correctamente! Estaremos validando los datos con la IMM y nos comunicaremos.");
                            textVmensaje.setText("Redirigiendo al login.");
                            limpiar_todo();

                            //Para esperar unos segundos en pasar a la siguiente pantalla.
                            new Handler().postDelayed(() -> {
                                //Envio los datos de la transaccion a la proxima activity
                                Intent i = new Intent(RegisterActivity.this, login_activity.class);
                                startActivity(i);
                            }, 5000); //5 segundos

                        } else {
                            textVmensaje.setText("Las contraseñas no coinciden, favor de verificar");
                            limpiarCajasPass();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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


    //Validar que los campos tengan datos
    private void validacion_vacio(){
        String ci_v = editTextcioperador.getText().toString();
        String nombre_v = editTextNombre.getText().toString();
        String Apellido_v = editTextApellido.getText().toString();
        String Telefono_v = editTextTelefono.getText().toString();
        String Mail_ingresado_v = editTextCorreo.getText().toString();
        String Password_v = editTextPass.getText().toString();
        String PasswordC_v = editTextPassC.getText().toString();
        String Tipo_operador_v = editTextTipo_operador.getText().toString();
  //      String Nombre_ubicacion_v = editTextNombre_ubicacion.getText().toString();

        if (ci_v.equals("")) {
            editTextcioperador.setError("Required");
        }  else if (nombre_v.equals("")) {
            editTextNombre.setError("Required");
        } else if (Apellido_v.equals("")) {
            editTextApellido.setError("Required");
        } else if (Telefono_v.equals("")) {
            editTextTelefono.setError("Required");
        } else if (Mail_ingresado_v.equals("")) {
            editTextCorreo.setError("Required");
        } else if (Password_v.equals("")) {
            editTextPass.setError("Required");
        } else if (PasswordC_v.equals("")) {
            editTextPassC.setError("Required");
        } else if (Tipo_operador_v.equals("")) {
            editTextTipo_operador.setError("Required");
    //    } else if (Nombre_ubicacion_v.equals("")) {
      //      editTextNombre_ubicacion.setError("Required");
    }
    }

    //Metodo para limpiar textbox
    private void limpiarCajasPass(){
        editTextPass.setText("");
        editTextPassC.setText("");
    }

    //Metodo para limpiar textbox
    private void limpiar_todo() {
        editTextcioperador.setText("");
        editTextNombre.setText("");
        editTextApellido.setText("");
        editTextTelefono.setText("");
        editTextCorreo.setText("");
        editTextPass.setText("");
        editTextPassC.setText("");
        editTextTipo_operador.setText("");
  //      editTextNombre_ubicacion.setText("");
        }







}
