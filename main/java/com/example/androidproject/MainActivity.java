package com.example.androidproject;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        myListView = (ListView) findViewById(R.id.display_text);

        DBAdapter db = new DBAdapter(this); //this is going to look for db if not found will create


        //--get all contacts---
        //Opens a DB connection and returns all records currently available.
        db.open();
        Cursor c = db.getAllContacts();           //gets cursor

        myCursorActivity myCursorAdapter = new myCursorActivity(this, c);

        myListView.setAdapter(myCursorAdapter);

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                intent.putExtra("nameKey", view.getTag().toString());
                startActivity(intent);
            }
        });
        db.close();
    }

    //display main menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add:
                startActivity(new Intent(getApplicationContext(), AddContactActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
