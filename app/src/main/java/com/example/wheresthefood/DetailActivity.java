package com.example.wheresthefood;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wheresthefood.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

//    TextView quantity;
//    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type", 0) == 1) {

            int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String description = getIntent().getStringExtra("description");
//            quantity = Integer.parseInt(getIntent().getStringExtra("quantity"));

            binding.detailImage.setImageResource(image);
            binding.pricelbl.setText(String.format("%d", price));
            binding.nameBox.setText(name);
            binding.detailDescription.setText(description);
//            binding.quantity.setText("1");



            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isInserted = helper.insertOrder(
                            binding.nameText.getText().toString(),
                            binding.phoneBox.getText().toString(),
                            price,
                            image,
                            name,
                            description,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Some Error while Inserting :(", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            int id = getIntent().getIntExtra("id", 0);
            Cursor cursor = helper.getOrderById(id);
            int image = cursor.getInt(4);

            binding.detailImage.setImageResource(cursor.getInt(4));
            binding.pricelbl.setText(String.format("%d", cursor.getInt(3)));
            binding.nameBox.setText(cursor.getString(6));
            binding.detailDescription.setText(cursor.getString(7));
            binding.quantity.setText(cursor.getString(5));

            binding.nameText.setText(cursor.getString(1));
            binding.phoneBox.setText(cursor.getString(2));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean isUpdated = helper.updateOrder(
                      binding.nameText.getText().toString(),
                      binding.phoneBox.getText().toString(),
                      Integer.parseInt(binding.pricelbl.getText().toString()),
                      image,
                      binding.detailDescription.getText().toString(),
                      binding.nameBox.getText().toString(),
                            Integer.parseInt(binding.quantity.getText().toString()),
                      id
                    );

                    if(isUpdated)
                        Toast.makeText(DetailActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Failed to Update :(", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}