package com.example.wheresthefood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import com.example.wheresthefood.Adapters.OrdersAdapter;
import com.example.wheresthefood.Models.OrdersModel;
import com.example.wheresthefood.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ArrayList<OrdersModel> list = new ArrayList<>();
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));
//        list.add(new OrdersModel(R.drawable.burger, "Chicken Burger", "180", "2022001" ));

        DBHelper helper = new DBHelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

        OrdersAdapter adapter = new OrdersAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(layoutManager);
    }


}