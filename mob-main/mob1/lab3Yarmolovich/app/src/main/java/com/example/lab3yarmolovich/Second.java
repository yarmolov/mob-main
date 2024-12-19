package com.example.lab3yarmolovich;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class Second extends AppCompatActivity {

    private TextView textViewNameSurname, textViewPhone, textViewRoute;
    private Button buttonSetPath, buttonCallTaxi, buttonShowFullName;
    private ActivityResultLauncher<Intent> setPathLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("Second", "onCreate");

        textViewNameSurname = findViewById(R.id.textViewNameSurname);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewRoute = findViewById(R.id.textViewRoute);
        buttonSetPath = findViewById(R.id.buttonSetPath);
        buttonCallTaxi = findViewById(R.id.buttonCallTaxi);
        buttonShowFullName = findViewById(R.id.buttonShowFullName);

        Intent intent = getIntent();
        String phone = intent.getStringExtra("surname");
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("phone");

        textViewNameSurname.setText(name +  " " + phone );
        textViewPhone.setText(surname);

        // Инициализация ActivityResultLauncher
        setPathLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    String route = data.getStringExtra("route");
                    textViewRoute.setText("Маршрут: " + route);
                    buttonCallTaxi.setEnabled(true);
                    buttonCallTaxi.setOnClickListener(view -> {
                        // Выводим всплывающее сообщение
                        Toast.makeText(Second.this, "Такси успешно вызвано!", Toast.LENGTH_SHORT).show();
                        Log.d("Second", "Такси вызвано");
                    });
                }
            }
        });

        buttonSetPath.setOnClickListener(view -> {
            Intent setPathIntent = new Intent(Second.this, Third.class);
            setPathLauncher.launch(setPathIntent);
        });

        //
        buttonShowFullName.setOnClickListener(view -> {
            String fullName = name + " " + surname;
            Toast.makeText(Second.this, "Yarmolovich Alexander AS-63 " , Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Second", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Second", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Second", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Second", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Second", "onDestroy");
    }
}