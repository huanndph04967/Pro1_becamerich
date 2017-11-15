package com.example.dinhhuan.becamerich;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by dinhhuan on 15/11/2017.
 */

public class act_Signup extends AppCompatActivity {
    Button btn_Ok;
    EditText edt_User1,edt_Pass1,edt_Pass2;
    FirebaseAuth auth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_Ok = (Button)findViewById(R.id.btn_aa);
        edt_User1 = (EditText)findViewById(R.id.edt_User1);
        edt_Pass1 = (EditText)findViewById(R.id.edt_Pass1);
        edt_Pass2 = (EditText)findViewById(R.id.edt_Pass2);
        auth = FirebaseAuth.getInstance();
        btn_Ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_User1.getText().toString();
                String pass = edt_Pass1.getText().toString();
                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(act_Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

            }
        });
    }

    private void updateUI(Object o) {
    }
}
