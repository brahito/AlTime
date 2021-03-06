package com.example.altime.altime;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {


    EditText et_correo;
    EditText et_contra;
    Button btn_iniciar;
    FirebaseDatabase db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_correo = findViewById(R.id.et_correo);
        et_contra = findViewById(R.id.et_contra);
        btn_iniciar = findViewById(R.id.btn_iniciar);
        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        FirebaseUser usuario = auth.getCurrentUser();

        if(usuario != null){

            finish();
            startActivity( new Intent(LoginActivity.this, MainActivity.class));

        }

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo = et_correo.getText().toString();
                String contra = et_contra.getText().toString();
                validar(correo,contra);
            }
        });



    }

    private void validar(String correo, String contra){

            auth.signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        startActivity( new Intent(LoginActivity.this, MainActivity.class));
                        Toast.makeText(LoginActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                    }else {

                        Toast.makeText(LoginActivity.this, "Login Fallido", Toast.LENGTH_SHORT).show();
                    }

                }
            });
    }

}
