package com.example.wheresthefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.wheresthefood.Adapters.MainAdapter;
import com.example.wheresthefood.Models.MainModel;
import com.example.wheresthefood.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // WE USE VIEW BINDING TO ELIMINATE THE HASSLE OF INITIALIZING VARIABLES
    // AND CONNECTING THEM WITH THEIR CORRESPONDING XML ID VALUES

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list = new ArrayList<>();

        list.add(new MainModel(R.drawable.burger, "Chicken Burger", "180", "Smoked and Barbecued Chicken Patty"));
        list.add(new MainModel(R.drawable.burger, "Veg Burger", "160", "Classic Mix Veg Patty with Paneer"));
        list.add(new MainModel(R.drawable.pizza, "Chicken Pizza", "350", "Smoked and Barbecued Chicken Pizza"));
        list.add(new MainModel(R.drawable.pizza, "Farmhouse Pizza", "320", "Mix Veggies + Cottage Cheese + Creme Pizza"));
        list.add(new MainModel(R.drawable.pasta, "Spaghetti", "300", "Italiano Pasta"));
        list.add(new MainModel(R.drawable.dosa, "Mysore Masala Dosa", "150", "Mysore Masala Dosa with Coconut Chutney"));
        list.add(new MainModel(R.drawable.dosa, "Masala Dosa", "130", "Masala Dosa with Coconut Chutney"));

        // ADAPTERS HELP PUSH DATA FROM A DATA SOURCE WITHIN VIEWS
        // HERE, WE'RE USING CUSTOM ADAPTERS TO PUSH DATA WITHIN RECYCLERVIEW

        MainAdapter adapter = new MainAdapter(list, this);
        binding.recyclerview.setAdapter(adapter);

        // HERE, WE GIVE OUR RECYCLERVIEW A CONTEXT ABOUT THE LAYOUT WE REQUIRE (LINEAR LAYOUT)

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // WHEN AN ITEM FROM THE RECYCLERVIEW IS TAPPED, ORDERACTIVITY NEEDS TO START

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}