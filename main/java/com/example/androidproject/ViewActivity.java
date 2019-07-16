package com.example.androidproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class ViewActivity extends AppCompatActivity {

    private EditText edit_nameID;
    private EditText edit_phoneID;
    private EditText edit_emailID;
    private EditText edit_addressID;
    private EditText edit_cityID;
    private EditText edit_provinceID;
    private EditText edit_codeID;

    DBAdapter db = new DBAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        //get references to the widgets
        edit_nameID = (EditText) findViewById(R.id.edit_nameID);
        edit_phoneID = (EditText) findViewById(R.id.edit_phoneID);
        edit_emailID = (EditText) findViewById(R.id.edit_emailID);
        edit_addressID = (EditText) findViewById(R.id.edit_addressID);
        edit_cityID = (EditText) findViewById(R.id.edit_cityID);
        edit_provinceID = (EditText) findViewById(R.id.edit_provinceID);
        edit_codeID = (EditText) findViewById(R.id.edit_codeID);

        DBAdapter db = new DBAdapter(this);

        //---retrieves a particular contact---
        db.open();
        String itemID = getIntent().getExtras().getString("nameKey");
        Cursor c = db.getContact(Integer.parseInt(itemID));

        if (c.moveToFirst()) {
            edit_nameID.setText(c.getString(1));
            edit_phoneID.setText(c.getString(2));
            edit_emailID.setText(c.getString(3));
            edit_addressID.setText(c.getString(4));
            edit_cityID.setText(c.getString(5));
            edit_provinceID.setText(c.getString(6));
            edit_codeID.setText(c.getString(7));
        }
        else
            Toast.makeText(this, "No contact found", Toast.LENGTH_LONG).show();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.activity_view, menu);
        return true;
    }

    //@Override
    public void update(){
        String itemID = getIntent().getExtras().getString("nameKey");
        long rowID = Long.valueOf(Integer.parseInt(itemID));
        DBAdapter db = new DBAdapter(this);
        //save edit text fields to strings
        String name = edit_nameID.getText().toString();
        String phone = edit_phoneID.getText().toString();
        String email = edit_emailID.getText().toString();
        String address = edit_addressID.getText().toString();
        String city = edit_cityID.getText().toString();
        String province = edit_provinceID.getText().toString();
        String code = edit_codeID.getText().toString();

        db.open();

        if (db.updateContact( rowID, name, phone, email, address, city, province, code))
            Toast.makeText(this, "Update successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Update failed.", Toast.LENGTH_LONG).show();
    }

    public void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                delete();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog

            }
        });
        // Set other dialog properties
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);


        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void delete(){
        String itemID = getIntent().getExtras().getString("nameKey");
        long rowID = Long.valueOf(Integer.parseInt(itemID));

        db.open();
        if (db.deleteContact(rowID))
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_update:
                update();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            case R.id.action_delete:
                alert();
//                String itemID = getIntent().getExtras().getString("nameKey");
//                long rowID = Long.valueOf(Integer.parseInt(itemID));
//
//                Intent intent = new Intent(getApplicationContext(), ConfirmActivity.class);
//                intent.putExtra("nameKey", rowID);
//                startActivity(intent);

                //startActivity(new Intent(getApplicationContext(), ConfirmActivity.class));
//                delete();
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
