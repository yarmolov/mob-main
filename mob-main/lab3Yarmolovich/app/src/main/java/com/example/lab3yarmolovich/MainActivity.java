package com.example.lab3yarmolovich;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPhone, editTextName, editTextSurname;
    private Button buttonRegister, buttonShowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "onCreate");

        editTextPhone = findViewById(R.id.editTextPhone);
        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        buttonRegister = findViewById(R.id.buttonRegister);
        buttonShowName = findViewById(R.id.buttonShowName);

        SharedPreferences sharedPreferences = getSharedPreferences("TaxiAppPrefs", MODE_PRIVATE);
        editTextPhone.setText(sharedPreferences.getString("surname", ""));
        editTextName.setText(sharedPreferences.getString("name", ""));
        editTextSurname.setText(sharedPreferences.getString("phone", ""));

        if (!editTextPhone.getText().toString().isEmpty()) {
            buttonRegister.setText("Зарегестрировать");
        }

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();

                // Сохранение данных
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("surname", phone);
                editor.putString("name", name);
                editor.putString("phone", surname);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Second.class);
                intent.putExtra("surname", phone);
                intent.putExtra("name", name);
                intent.putExtra("phone", surname);
                startActivity(intent);
            }
        });

        buttonShowName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                Toast.makeText(MainActivity.this, "Yarmolovich Alexander AS-63 " , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }
}