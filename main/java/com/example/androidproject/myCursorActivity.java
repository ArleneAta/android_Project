package com.example.androidproject;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class myCursorActivity extends CursorAdapter {
    public myCursorActivity(Context context, Cursor c) {
        super(context, c);
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.listview_layout);
//    }

    @Override
    public View newView (Context context, Cursor cursor, ViewGroup viewGroup){
        return LayoutInflater.from(context).inflate(R.layout.listview_layout, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){

        TextView display = (TextView) view.findViewById(R.id.listNameID);
        TextView subdisplay = (TextView) view.findViewById(R.id.listemailID);

        display.setText(cursor.getString(1));
        subdisplay.setText(cursor.getString(3));


        //for onclick listener to view
        view.setTag(cursor.getString(0));
    }

}
