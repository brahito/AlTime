package com.example.altime.altime;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity{

    FirebaseDatabase db;
    FirebaseAuth auth;
    Button btn_salir;
    BottomNavigationView nv_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  btn_salir =findViewById(R.id.btn_salir);
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        nv_bar = findViewById(R.id.main_nav);

        FirebaseUser usuario = auth.getCurrentUser();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                new HomeFragment()).commit();

        //Esto es lo del boton de cerrar sesion para el que lo necesita mover
     /* btn_salir.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              auth.signOut();
              finish();
              startActivity( new Intent(MainActivity.this, LoginActivity.class));
          }
      });*/

        nv_bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;
                switch (menuItem.getItemId()){

                    case R.id.nav_home:
                         fragment = new HomeFragment();
                    break;

                    case R.id.nav_cal:
                        fragment = new CalendarFragment();
                        break;

                    case R.id.nav_pet:
                        fragment = new PetFragment();
                        break;

                    case R.id.nav_perfil:
                        fragment = new PerfilFragment();
                        break;
                }

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contenedor,
                        fragment).commit();
                return  true;
            }
        });

    }

    }
