package com.woow.tlcoperador;


import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.woow.tlcoperador.ui.Monedero.MonederoFragment;
import com.woow.tlcoperador.ui.home.HomeFragment;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static androidx.annotation.InspectableProperty.ValueType.RESOURCE_ID;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Realizar una Denuncia", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.nav_monedero, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_help)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Aca recibimos la id del operador que ingreso
        Bundle extras = getIntent().getExtras();
        String ci_operador_ingreso = extras.getString("ci_operador_login");
        String nombre_operador_ingreso = extras.getString("nombre_operador");


        Bundle bundle = new Bundle();
        bundle.putString("ci_operador_frag", ci_operador_ingreso);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
/*
        Bundle bundle_1 = new Bundle();
        bundle_1.putString("ci_operador_frag", ci_operador_ingreso);
        HomeFragment homeFragment_1 = new HomeFragment();
        homeFragment_1.setArguments(bundle_1);

        FragmentManager fm_1 = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm_1.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment , homeFragment_1);
        fragmentTransaction.commit();
//

        Bundle bundle = new Bundle();
        bundle.putString("ci_operador_frag", ci_operador_ingreso);
        MonederoFragment fraginfo = new MonederoFragment();
        fraginfo.setArguments(bundle);

         Bundle bundle = new Bundle();
         String myMessage = "Stackoverflow is cool!";
         bundle.putString("message", myMessage );
         FragmentClass fragInfo = new FragmentClass();
         fragInfo.setArguments(bundle);
         transaction.replace(R.id.fragment_single, fragInfo);
         transaction.commit();
*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}