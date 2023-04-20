package com.example.projetexercicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Exercices extends AppCompatActivity {

    protected SQLiteDatabase db;
    protected Cursor cursor;
    protected SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices);

        Intent intent = getIntent();
        int idCateg = intent.getIntExtra("idCateg",0);
        String categorie = intent.getStringExtra("categorie");
        db = (new DatabaseHelper(this)).getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM exercices WHERE idCateg=?",new String[]{""+idCateg});

        ListView lvEx = findViewById(R.id.lvEx);
        TextView tvEx = findViewById(R.id.tvEx);
        tvEx.setText(categorie);

        adapter = new SimpleCursorAdapter(
                this, R.layout.listview, cursor,
                new String[] {"nomEx", "courteDesc", "imgEx"},
                new int[] {R.id.tvNomEx, R.id.tvCourteDesc, R.id.ivEx},0);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (view.getId() == R.id.ivEx) {
                    ImageView IV = (ImageView) view;
                    int resID = getApplicationContext().getResources().getIdentifier(cursor.getString(columnIndex),
                            "drawable", getApplicationContext().getPackageName());
                    IV.setImageDrawable(getApplicationContext().getResources().getDrawable(resID));
                    return true;
                }
                return false;
            }
        });

        lvEx.setAdapter(adapter);
        lvEx.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) adapter.getItem(position);
                Intent intent = new Intent(view.getContext(), Details.class);
                intent.putExtra("idEx", cursor.getInt(0));
                intent.putExtra("nomEx", cursor.getString(2));
                startActivity(intent);
            }
        });
    }

    public void modifier(View view) {
        LinearLayout ll =(LinearLayout) view.getParent();
        ListView lv = (ListView) ll.getParent().getParent().getParent();
        int position = lv.getPositionForView(view);
        Intent intent = new Intent(this,Modifier.class);
        Cursor cursor = (Cursor) adapter.getItem(position);
        intent.putExtra("idEx",cursor.getInt(0));
        startActivity(intent);
    }

    public void supprimer(View view) {
        LinearLayout ll =(LinearLayout) view.getParent();
        ListView lv = (ListView) ll.getParent().getParent().getParent();
        int position = lv.getPositionForView(view);
        Cursor cursor = (Cursor) adapter.getItem(position);
        int idEx = cursor.getInt(0);

        db.execSQL("DELETE FROM details WHERE idEx=?",new String[]{""+idEx});
        db.execSQL("DELETE FROM exercices WHERE _id=?",new String[]{""+idEx});
        Toast.makeText(this, idEx +" est supprimé", Toast.LENGTH_SHORT).show();
    }

    public void add(View view) {
        Intent intent = new Intent(this,Modifier.class);
        intent.putExtra("idEx",0);
        startActivity(intent);
    }

    public void backtoMain(View view) {
        finish();
    }

}
