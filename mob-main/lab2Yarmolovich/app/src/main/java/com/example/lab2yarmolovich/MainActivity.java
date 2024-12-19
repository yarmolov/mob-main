package com.example.lab2yarmolovich;

import com.example.lab2yarmolovich.R;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView gestureTextView;
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureTextView = findViewById(R.id.gestureTextView);
        gestureDetector = new GestureDetector(this, new GestureListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            gestureTextView.setText("Двойное нажатие");
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            gestureTextView.setText("Одиночное нажатие");
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            String direction = getFlingDirection(e1, e2);
            gestureTextView.setText("Свайп: " + direction);
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            gestureTextView.setText("Долгое нажатие");
        }

        private String getFlingDirection(MotionEvent e1, MotionEvent e2) {
            float deltaX = e2.getX() - e1.getX();
            float deltaY = e2.getY() - e1.getY();
            String direction;

            if (Math.abs(deltaX) > Math.abs(deltaY)) {
                direction = deltaX > 0 ? "вправо" : "влево";
            } else {
                direction = deltaY > 0 ? "вниз" : "вверх";
            }
            return direction;
        }
    }
}