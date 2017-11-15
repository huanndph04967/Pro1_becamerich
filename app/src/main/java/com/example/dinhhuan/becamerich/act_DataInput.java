package com.example.dinhhuan.becamerich;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by dinhhuan on 15/11/2017.
 */

public class act_DataInput extends AppCompatActivity {
    private Button btn_Next;
    EditText edt_Get;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        btn_Next = (Button)findViewById(R.id.btn_next);
        edt_Get = (EditText)findViewById(R.id.edt_Values);
        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 int values = Integer.parseInt(edt_Get.getText().toString());
                Intent intent = new Intent(act_DataInput.this,act_DataUser.class);
                startActivity(intent);
            }
        });
    }
}
