package com.trunghtluu.gyminventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BuyActivity extends AppCompatActivity {

    public static String buy_key = "BUY";

    private TextView announce_textView;
    private EditText quantity_editText;
    private Button submit_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        String merchandise = getIntent().getStringExtra(buy_key);

        announce_textView = findViewById(R.id.announce_textView);
        announce_textView.setText(announce_textView.getText() + " " + merchandise.toUpperCase());

        quantity_editText = findViewById(R.id.quantity_editText);
        submit_button = findViewById(R.id.submit_Button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String merchandise = getIntent().getStringExtra(buy_key);
                int quantity = Integer.parseInt(quantity_editText.getText().toString());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra(MainActivity.merc_key, merchandise);
                intent.putExtra(MainActivity.quant_key, quantity);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
