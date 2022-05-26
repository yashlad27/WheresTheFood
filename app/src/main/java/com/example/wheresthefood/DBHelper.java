package com.example.wheresthefood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.wheresthefood.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "image int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );
    }

     /*
        Below function when invoked, would delete the already existing table named "orders"
        and would invoke onCreate() again to create the table with updated values/fields.
      */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP table if exists orders");
        onCreate(sqLiteDatabase);

    }

    public boolean insertOrder(String name, String phone, int price, int image, String description, String foodname, int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
            id = 0
            name = 1
            phone = 2
            price = 3
            image = 4
            description = 5
            foodname = 6
            quantity = 7
        */
        values.put("name", name);
        values.put("price", price);
        values.put("phone", phone);
        values.put("image", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);

        long id = database.insert("orders",null, values);

        // in case there's no value added to table
        if(id <= 0)
            return false;
        else
            return true;

    }

    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id, foodname, image, price from orders",null);

        // if there's data in the table
        if(cursor.moveToFirst()) {
            while(cursor.moveToNext()) {
                OrdersModel model = new OrdersModel();
                model.setOrderNumber(cursor.getInt(0) + "");
                model.setSoldItemName(cursor.getString(1));
                model.setOrderImage(cursor.getInt(2));
                model.setPrice(cursor.getInt(3) + "");
                orders.add(model);

            }
        }

        cursor.close();
        database.close();
        return orders;
    }

    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from orders where id = " +id,null);

        if(cursor != null)
            cursor.moveToFirst();

//        cursor.close();
//        database.close();
        return cursor;
    }

    public boolean updateOrder(String name, String phone, int price, int image, String description, String foodname, int quantity, int id){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
            id = 0
            name = 1
            phone = 2
            price = 3
            image = 4
            description = 5
            foodname = 6
            quantity = 7
        */
        values.put("name", name);
        values.put("price", price);
        values.put("phone", phone);
        values.put("image", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);

        long row = database.update("orders", values, "id="+id,null);

        // in case there's no value added to table
        if(id <= 0)
            return false;
        else
            return true;

    }

    public int deletedOrder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id="+id,null);
    }
}
