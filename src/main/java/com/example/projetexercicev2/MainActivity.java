package com.example.projetexercicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected GridView gridView;
    protected SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = findViewById(R.id.gridview);

        db = (new DatabaseHelper(this)).getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM categories",null);

        adapter = new SimpleCursorAdapter(
                this, R.layout.gridview, cursor,
                new String[] {"categorie", "imgCateg"},
                new int[] {R.id.tvCateg, R.id.ivImg},0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.ivImg) {
                    ImageView IV = (ImageView) view;
                    int resID = getApplicationContext().getResources().getIdentifier(cursor.getString(columnIndex),
                            "drawable", getApplicationContext().getPackageName());
                    IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
                    return true;
                }
                return false;
            }
        });
        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), Exercices.class);
                intent.putExtra("idCateg", cursor.getInt(0));
                intent.putExtra("categorie",cursor.getString(1));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_nav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch(item.getItemId()){
            case R.id.action_courriel:
                try {
                        intent = new Intent(Intent.ACTION_SENDTO);
                        intent.setData(Uri.parse("mailto:coach4life@gmail.com"));
                        startActivity(intent);

                }catch(ActivityNotFoundException e){
                    Toast.makeText(this,"Erreur application courriel",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_tel:
                Uri uri = Uri.parse("tel:438555777");
                intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}