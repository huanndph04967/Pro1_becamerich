package com.example.dinhhuan.becamerich;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ok" ;
    private EditText edt_User,edt_Pass;
    private Button btn_Login,btn_Register,btn_Reset,btn_gg;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        btn_Login = (Button)findViewById(R.id.btn_Login);
        btn_Register = (Button)findViewById(R.id.btn_Register);
        btn_Reset = (Button)findViewById(R.id.btn_reset);
        btn_gg= (Button)findViewById(R.id.btn_gg);
        edt_User = (EditText)findViewById(R.id.edt_User);
        edt_Pass = (EditText)findViewById(R.id.edt_Pass);
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edt_User.getText().toString().trim();
                String pass = edt_Pass.getText().toString().trim();
                if (TextUtils.isEmpty(user)){
                    Toast.makeText(MainActivity.this, "Vui long nhap ten dang nhap", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pass)){
                    Toast.makeText(MainActivity.this, "Vui long nhap mat khau", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pass.length()<6){
                    Toast.makeText(MainActivity.this, "Mat khau phai lon hon 6", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(user,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            updateUI(firebaseUser);
                            Intent intent1 = new Intent(MainActivity.this,act_DataInput.class);
                            startActivity(intent1);
                        }else {
                            Toast.makeText(MainActivity.this, "loi", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });

            }

        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,act_Signup.class);
                startActivity(intent);
            }
        });
        btn_gg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestIdToken(getString(R.string.default_web_client_id))
                        .requestEmail()
                        .build();

            }
        });


    }




    private void updateUI(FirebaseUser user) {
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser current = auth.getCurrentUser();
        updateUI(current);
    }



    @Override
    protected void onResume() {
        super.onResume();

    }
}

