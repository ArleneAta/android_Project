package com.example.androidproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View.OnClickListener;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContactActivity extends AppCompatActivity
        implements OnClickListener {

    private EditText add_nameID;
    private EditText add_phoneID;
    private EditText add_emailID;
    private EditText add_addressID;
    private EditText add_cityID;
    private EditText add_provinceID;
    private EditText add_codeID;

    private Button saveID;
    private Button cancelID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact_activity);

        //get references to the widgets
        add_nameID = (EditText) findViewById(R.id.add_nameID);
        add_phoneID = (EditText) findViewById(R.id.add_phoneID);
        add_emailID = (EditText) findViewById(R.id.add_emailID);
        add_addressID = (EditText) findViewById(R.id.add_addressID);
        add_cityID = (EditText) findViewById(R.id.add_cityID);
        add_provinceID = (EditText) findViewById(R.id.add_provinceID);
        add_codeID = (EditText) findViewById(R.id.add_codeID);

        saveID = (Button) findViewById(R.id.saveID);
        cancelID = (Button) findViewById(R.id.cancelID);
        //set listeners
        saveID.setOnClickListener(this);
        cancelID.setOnClickListener(this);

    }

    public void save (View v){

        DBAdapter db = new DBAdapter(this);

        //save edit text fields to strings
        String name = add_nameID.getText().toString();
        String phone = add_phoneID.getText().toString();
        String email = add_emailID.getText().toString();
        String address = add_addressID.getText().toString();
        String city = add_cityID.getText().toString();
        String province = add_provinceID.getText().toString();
        String code = add_codeID.getText().toString();

        //insert data into db
        //---add a contact--- this works now
        //opens the DB connection and inserts records.
        //Return a long int indicating the number of rows affected
        //Finally closes the DB, which is ALWAYS a good practice
        db.open();
        @SuppressWarnings("unused")
        long id = db.insertContact(name, phone, email, address, city, province, code);
        db.close();

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.saveID:
                save(v);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.cancelID:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }
    }
}
