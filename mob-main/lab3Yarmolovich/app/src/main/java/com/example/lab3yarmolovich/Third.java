package com.example.lab3yarmolovich;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Third extends AppCompatActivity {

    private EditText[] routeParams = new EditText[6];
    private Button buttonOK, buttonShowName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d("Third", "onCreate");

        routeParams[0] = findViewById(R.id.editTextRouteParam1);
        routeParams[1] = findViewById(R.id.editTextRouteParam2);
        routeParams[2] = findViewById(R.id.editTextRouteParam3);
        routeParams[3] = findViewById(R.id.editTextRouteParam4);
        routeParams[4] = findViewById(R.id.editTextRouteParam5);
        routeParams[5] = findViewById(R.id.editTextRouteParam6);
        buttonOK = findViewById(R.id.buttonOK);
        buttonShowName = findViewById(R.id.buttonShowName);

        // Получение имени и фамилии из Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");

        buttonOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder route = new StringBuilder();
                for (EditText param : routeParams) {
                    route.append(param.getText().toString()).append(" ");
                }
                Intent resultIntent = new Intent();
                resultIntent.putExtra("route", route.toString().trim());
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Обработка нажатия кнопки для показа имени
        buttonShowName.setOnClickListener(view -> {
            String fullName = name + " " + surname;
            Toast.makeText(Third.this, "Yarmolovich Alexander AS-63 " , Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Third", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Third", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Third", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Third", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Third", "onDestroy");
    }
}