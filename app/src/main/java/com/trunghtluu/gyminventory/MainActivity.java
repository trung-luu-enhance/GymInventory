package com.trunghtluu.gyminventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> shopArray = Arrays.asList(new String[]{"Treadmill", "Fixed Dumbbell", "Adjustable Dumbbell",
            "Kettlebell", "Fixed Barbell", "Bumper Plates", "Steel Plates", "Micro-Plates", "Excercise Bike"});

    private List<String> inventoryArray = new  ArrayList<String>(Arrays.asList("Treadmill", "Treadmill", "Treadmill"));

    private ListView shop_listView;
    private ListView inventory_listView;

    public static String merc_key = "MERCHANDISE";
    public static String quant_key = "QUANTITY_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inventory_listView = findViewById(R.id.inventory_listView);
        setInventoryArray();

        shop_listView = findViewById(R.id.shop_listView);
        setShopArray();
        shop_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String)shop_listView.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), BuyActivity.class);
                intent.putExtra(BuyActivity.buy_key, selection);

                if (intent != null)
                    startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String merc = data.getStringExtra(merc_key);
            int quant = data.getIntExtra(quant_key,0);

            for (int i = 0; i < quant; ++i)
                inventoryArray.add(merc);

            System.out.println("Im here");

            setInventoryArray();
        }
    }

    private void setShopArray() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.item_textview, shopArray);

        shop_listView.setAdapter(adapter);
    }

    private void setInventoryArray() {
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.item_textview, inventoryArray);

        inventory_listView.setAdapter(adapter);
    }
}
