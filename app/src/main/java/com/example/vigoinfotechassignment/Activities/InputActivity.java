package com.example.vigoinfotechassignment.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.vigoinfotechassignment.R;

public class InputActivity extends AppCompatActivity {

    private EditText editText1, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);

        Button storeButton = findViewById(R.id.storeButton);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = editText1.getText().toString();
                String data2 = editText2.getText().toString();
                String data3 = editText3.getText().toString();

                // Store data in a Bundle
                Bundle bundle = new Bundle();
                bundle.putString("data1", data1);
                bundle.putString("data2", data2);
                bundle.putString("data3", data3);

                // Start the new activity with the stored data
                Intent intent = new Intent(InputActivity.this, TabActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
