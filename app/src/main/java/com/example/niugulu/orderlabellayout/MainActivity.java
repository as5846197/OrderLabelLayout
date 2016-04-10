package com.example.niugulu.orderlabellayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private OrderLabelLayout orderLabelLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        orderLabelLayout = (OrderLabelLayout) findViewById(R.id.order_label_layout);
        addTextView();

    }

    private void addTextView() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(150, 70);
        for (int i = 0; i < 20; i++) {
            TextView text = new TextView(this);
            text.setText("wa" + i);
            text.setTextSize(16);
            text.setLayoutParams(params);
            orderLabelLayout.addView(text);
        }
    }
}
