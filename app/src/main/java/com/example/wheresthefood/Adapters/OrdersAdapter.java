package com.example.wheresthefood.Adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wheresthefood.DBHelper;
import com.example.wheresthefood.DetailActivity;
import com.example.wheresthefood.MainActivity;
import com.example.wheresthefood.Models.OrdersModel;
import com.example.wheresthefood.OrderActivity;
import com.example.wheresthefood.R;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.viewHolder>{

    ArrayList<OrdersModel> list;
    Context context;

    public OrdersAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);
        return new viewHolder(view);
    }


    // THESE ARE THE MAPPINGS FOR SPECIFIC VIEWS FROM XML

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final OrdersModel model = list.get(position);
        holder.orderImage.setImageResource(model.getOrderImage());
        holder.soldItemName.setText(model.getSoldItemName());
        holder.orderNumber.setText(model.getOrderNumber());
        holder.price.setText(model.getPrice());

        // WHEN A CARD ON ORDERS SCREEN IS TAPPED, UPDATE PAGE IS BROUGHT UP

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", Integer.parseInt(model.getOrderNumber()));
                intent.putExtra("type", 2);
                context.startActivity(intent);
            }
        });

        // WHEN A CARD ON ORDERS SCREEN IS LONG TAPPED, DELETION IS PERFORMED

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                // ALERT DIALOG FOR CONFIRMATION OF DELETION

                new AlertDialog.Builder(context)
                        .setTitle("Delete Item")
                        .setMessage("Are you sure you want to remove this item?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DBHelper helper = new DBHelper(context);
                                if(helper.deletedOrder(model.getOrderNumber()) > 0)
                                    Toast.makeText(context, "Deleted Order", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(context, "Error Deleting", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context,OrderActivity.class);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context,OrderActivity.class);
                                context.startActivity(intent);
                            }
                        })
                        .show();

                return false;
           }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // MAPPING THE VIEWHOLDER

    public static class viewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView soldItemName, orderNumber, price;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            orderImage = itemView.findViewById(R.id.order_image);
            soldItemName = itemView.findViewById(R.id.orderItemName);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.price);
        }
    }
}
