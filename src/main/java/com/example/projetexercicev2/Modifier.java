package com.example.projetexercicev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Modifier extends AppCompatActivity {
    protected SQLiteDatabase db;
    protected Cursor cursor1;
    protected Cursor cursor2;
    protected TextView tvIdEx;
    protected TextView tvIdDet;
    protected EditText edNom;
    protected EditText edCourteDesc;
    protected EditText edLongueDesc;
    protected EditText edMusc;
    protected EditText edExec;
    protected EditText edLien;
    protected int idCateg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        tvIdEx = findViewById(R.id.tvIdEx);
        tvIdDet = findViewById(R.id.tvIdDet);
        edNom = findViewById(R.id.edNom);
        edCourteDesc = findViewById(R.id.edCourteDesc);
        edLongueDesc = findViewById(R.id.edLongueDesc);
        edMusc = findViewById(R.id.edMusc);
        edExec = findViewById(R.id.edExec);
        edLien = findViewById(R.id.edLien);

        Intent intent = getIntent();
//        String nomEx = intent.getStringExtra("nomEx");
        int idEx = intent.getIntExtra("idEx", 0);
        db = (new DatabaseHelper(this)).getWritableDatabase();

        TextView tvAddMod = findViewById(R.id.tvAddMod);
        if (idEx!=0) {
            tvAddMod.setText("MODIFIER EXERCICE");
            cursor1 = db.rawQuery("SELECT * FROM exercices WHERE _id=?", new String[]{""+idEx});
            cursor2 = db.rawQuery("SELECT * FROM details WHERE idEx=?", new String[]{""+idEx});
            int idDet= 0;
            int idCateg = 0;
            String nom = "";
            String courteDesc = "";
            String longueDesc = "";
            String musc = "";
            String exec = "";
            String lien = "";

            if (cursor1.getCount() == 1) {
                cursor1.moveToFirst();
                idCateg = cursor1.getInt(1);
                nom = cursor1.getString(2);
                courteDesc = cursor1.getString(3);
            }
            cursor1.close();

            if(cursor2.getCount()==1){
                cursor2.moveToFirst();
                idDet = cursor2.getInt(0);
                longueDesc = cursor2.getString(2);
                musc = cursor2.getString(3);
                exec = cursor2.getString(4);
                lien = cursor2.getString(5);
            }
            cursor2.close();

            tvIdEx.setText(""+idEx);
            tvIdDet.setText(""+idDet);
            edNom.setText(nom);
            edCourteDesc.setText(courteDesc);
            edLongueDesc.setText(longueDesc);
            edMusc.setText(musc);
            edExec.setText(exec);
            edLien.setText(lien);

            switch (idCateg) {
                case 1:
                    RadioButton rbBiceps = findViewById(R.id.rbBiceps);
                    rbBiceps.setChecked(true);
                    break;
            }
        }else{
            tvAddMod.setText("AJOUTER EXERCICE");
        }
    }

    public void envoyer(View view) {
        String idEx = tvIdEx.getText().toString();
        String idDet = tvIdEx.getText().toString();
        String nom = edNom.getText().toString();
        String courteDesc = edCourteDesc.getText().toString();
        String longueDesc = edLongueDesc.getText().toString();
        String musc = edMusc.getText().toString();
        String exec = edExec.getText().toString();
        String lien = edLien.getText().toString();
        RadioButton rbBiceps = findViewById(R.id.rbBiceps);
        RadioButton rbAbdominaux = findViewById(R.id.rbAbdominaux);
        RadioButton rbCuisses = findViewById(R.id.rbCuisses);
        RadioButton rbAvantbras = findViewById(R.id.rbAvantbras);
        RadioButton rbEpaules = findViewById(R.id.rbEpaules);
        RadioButton rbDos = findViewById(R.id.rbDos);
        RadioButton rbMollets = findViewById(R.id.rbMollets);
        RadioButton rbPectoraux = findViewById(R.id.rbPectoraux);
        RadioButton rbTriceps = findViewById(R.id.rbTriceps);
        if(rbBiceps.isChecked()){
            idCateg = 1;
        }else if(rbAbdominaux.isChecked()) {
            idCateg = 1;
        }else if(rbAvantbras.isChecked()) {
            idCateg = 2;
        }else if(rbCuisses.isChecked()){
            idCateg = 3;
        }else if(rbEpaules.isChecked()) {
            idCateg = 5;
        }else if(rbDos.isChecked()) {
            idCateg = 6;
        }else if(rbMollets.isChecked()) {
            idCateg = 7;
        }else if(rbPectoraux.isChecked()) {
            idCateg = 8;
        }else if(rbTriceps.isChecked()) {
            idCateg = 9;
        }

        if(!idEx.equals("")) {
            db.execSQL("UPDATE exercices SET idCateg=?, nomEx=?, courteDesc=? WHERE _id=?",
                    new String[]{""+idCateg, nom, courteDesc, ""+idEx});
            if(!idDet.equals("")) {
                db.execSQL("UPDATE details SET idEx=?, longueDesc=?, muscle=?, execution=?, lien=? WHERE _id=?",
                        new String[]{""+idEx, longueDesc, musc, exec, lien, ""+idDet});
            }else {
                ContentValues values = new ContentValues();
                values.put("idEx", Integer.parseInt(idEx));
                values.put("longueDesc", longueDesc);
                values.put("muscle", musc);
                values.put("execution", exec);
                values.put("lien", lien);
                values.put("imgDet", R.drawable.gym1);
                db.insert("details", null, values);
            }
            Toast.makeText(this, "Exercice modifié", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            ContentValues values = new ContentValues();
            values.put("idCateg", idCateg);
            values.put("nomEx", nom);
            values.put("courteDesc", courteDesc);
            values.put("imgEx", R.drawable.gym1);
            long row = db.insert("exercices", null, values);

            values.clear();
            values.put("idEx", (int) row);
            values.put("longueDesc", longueDesc);
            values.put("muscle", musc);
            values.put("execution", exec);
            values.put("lien", lien);
            values.put("imgDet", R.drawable.gym1);
            db.insert("details", null, values);

            Toast.makeText(this, "Exercice ajouté", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public void backtoEx(View view) {
        finish();
    }
}