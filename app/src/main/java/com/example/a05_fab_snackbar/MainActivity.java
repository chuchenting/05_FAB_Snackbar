package com.example.a05_fab_snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ListView dateListView;
    private ArrayList<String> dataSource;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateListView = (ListView) findViewById(R.id.dateListView);
        dataSource = new ArrayList<String>();


        arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_item, dataSource);
        dateListView.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        View.OnClickListener handler = new OnClickHandler();
        fab.setOnClickListener(handler);
    }//end of onCreate

    public class OnClickHandler implements android.view.View.OnClickListener {
        public void onClick(View view) {
            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.TAIWAN);
            dataSource.add(date.format(new Date(System.currentTimeMillis())));
            arrayAdapter.notifyDataSetChanged();

            Snackbar sb = Snackbar.make(view, "Item added to list.", Snackbar.LENGTH_SHORT);
            UndoClickHandler undoHandler = new UndoClickHandler();
            sb.setAction("Undo", undoHandler);
            sb.show();
        }//End of onClick

        public class UndoClickHandler implements android.view.View.OnClickListener {
            public void onClick(View view) {
                int size =dataSource.size();
                dataSource.remove(size-1);
                arrayAdapter.notifyDataSetChanged();

                Snackbar sb = Snackbar.make(view, "Item removed from list.", Snackbar.LENGTH_SHORT);
                sb.show();
            }//End of onClick
        }//End of UndoClickHandler
    }//End of OnClickHandler
}//End of MainActivity