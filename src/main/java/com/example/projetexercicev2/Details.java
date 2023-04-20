package com.example.projetexercicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    protected TextView tvDesDet;
    protected TextView tvMuscDet;
    protected TextView tvExecDet;
    protected TextView tvLien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        int idEx = intent.getIntExtra("idEx",0);
        String nomEx = intent.getStringExtra("nomEx");
        SQLiteDatabase db = (new DatabaseHelper(this)).getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM details WHERE idEx=?",new String[]{""+idEx});
        String longueDesc="";
        String muscle="";
        String execution="";
        String lien="";
        int imgDet=0;

        if(cursor.getCount()==1){

            cursor.moveToFirst();
            longueDesc = cursor.getString(2);
            muscle = cursor.getString(3);
            execution = cursor.getString(4);
            lien = cursor.getString(5);
            imgDet = cursor.getInt(6);
        }
        cursor.close();

        ImageView ivIntro = findViewById(R.id.ivIntro);
        ivIntro.setImageResource(imgDet);

        TextView tvNomDet = findViewById(R.id.tvNomDet);
        tvNomDet.setText(nomEx);

        tvDesDet = findViewById(R.id.tvDescDet);
        tvDesDet.setText(longueDesc);

        tvMuscDet = findViewById(R.id.tvMuscDet);
        tvMuscDet.setText(muscle);

        tvExecDet = findViewById(R.id.tvExecDet);
        tvExecDet.setText(execution);

        tvLien = findViewById(R.id.tvLien);
        tvLien.setText(lien);
    }

    public void toggleDesc(View view) {
        if(tvDesDet.getVisibility()==View.GONE){
            tvDesDet.setVisibility(View.VISIBLE);
        }else if(tvDesDet.getVisibility()==View.VISIBLE){
            tvDesDet.setVisibility(View.GONE);
        }
    }

    public void toggleMusc(View view) {
        if(tvMuscDet.getVisibility()==View.GONE){
            tvMuscDet.setVisibility(View.VISIBLE);
        }else if(tvMuscDet.getVisibility()==View.VISIBLE){
            tvMuscDet.setVisibility(View.GONE);
        }
    }

    public void toggleExec(View view) {
        if(tvExecDet.getVisibility()==View.GONE){
            tvExecDet.setVisibility(View.VISIBLE);
        }else if(tvExecDet.getVisibility()==View.VISIBLE){
            tvExecDet.setVisibility(View.GONE);
        }
    }

    public void toggleVideo(View view) {
        if(tvLien.getVisibility()==View.GONE){
            tvLien.setVisibility(View.VISIBLE);
        }else if(tvLien.getVisibility()==View.VISIBLE){
            tvLien.setVisibility(View.GONE);
        }
    }

    public void backtoEx(View view) {
        finish();
    }

    public void playVideo(View view) {
        String lien = tvLien.getText().toString();
        Uri uri = Uri.parse(lien);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}