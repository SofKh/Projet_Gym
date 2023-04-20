package com.example.projetexercicev2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "bdmusculation";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 5);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//Création des tables
        String sqlCateg = "CREATE TABLE IF NOT EXISTS categories (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "categorie TEXT, " +
                "imgCateg INTEGER)";
        db.execSQL(sqlCateg);

        String sqlEx = "CREATE TABLE IF NOT EXISTS exercices(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idCateg INTEGER, " +
                "nomEx TEXT, " +
                "courteDesc TEXT, " +
                "imgEx INTEGER, " +
                "FOREIGN KEY(idCateg) REFERENCES categories(_id))";
        db.execSQL(sqlEx);

        String sqlDet = "CREATE TABLE IF NOT EXISTS details (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "idEx INTEGER, " +
                "longueDesc TEXT, " +
                "muscle TEXT, " +
                "execution TEXT, " +
                "lien TEXT, " +
                "imgDet INTEGER, " +
                "FOREIGN KEY(idEx) REFERENCES exercices(_id))";
        db.execSQL(sqlDet);

        ContentValues values = new ContentValues();

        values.put("categorie", "biceps");
        values.put("imgCateg",R.drawable.exercices_biceps);
        db.insert("categories", null, values);

        values.put("categorie", "abdominaux");
        values.put("imgCateg", R.drawable.exercices_abdominaux);
        db.insert("categories", null, values);

        values.put("categorie", "pectoraux");
        values.put("imgCateg",R.drawable.exercices_pectorauxparties);
        db.insert("categories", null, values);

        values.put("categorie", "dos");
        values.put("imgCateg",R.drawable.exercices_rowingbarret);
        db.insert("categories", null, values);

        values.put("categorie", "épaules");
        values.put("imgCateg",R.drawable.exercices_forceperformance);
        db.insert("categories", null, values);

        values.put("categorie", "triceps");
        values.put("imgCateg",R.drawable.exercices_triceps);
        db.insert("categories", null, values);

        values.put("categorie", "avant-bras");
        values.put("imgCateg",R.drawable.exercices_avantbras);
        db.insert("categories", null, values);

        values.put("categorie", "cuisses / fessiers");
        values.put("imgCateg",R.drawable.exercices_programmecuisses);
        db.insert("categories", null, values);

        values.put("categorie", "mollets");
        values.put("imgCateg",R.drawable.exercices_mollets);
        db.insert("categories", null, values);
        values.clear();

        values.put("idCateg", 1);
        values.put("nomEx","Curl barre");
        values.put("courteDesc","Cet exercice sollicite et développe les biceps. Le curl est l’exercice d’isolation de base pour les biceps et est généralement réalisé debout, avec une barre droite ou coudée…");
        values.put("imgEx", R.drawable.biceps_curlbarre);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl haltère");
        values.put("courteDesc","Une alternative à la barre qui permet de travailler les bras séparément. Les haltères permettent pas mal de variantes intéressantes. Avec elles, vous allez vous forger des biceps d'acier…");
        values.put("imgEx", R.drawable.biceps_curlhaltere);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl pupitre barre");
        values.put("courteDesc","Cet exercice va faire travailler les biceps avec une grande intensité. Le pupitre permet de se concentrer sur les muscles des bras et évite de tricher comme c'est souvent le cas avec les curls…");
        values.put("imgEx", R.drawable.biceps_curlpupitre);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl concentration");
        values.put("courteDesc","Un exercice d'isolation avec haltère qui va vous permettre de peaufiner vos biceps. Il a la réputation de donner du « pic » au biceps. ");
        values.put("imgEx", R.drawable.biceps_curlconcentration);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Le curl incliné");
        values.put("courteDesc","Un exercice d'isolation pour les biceps idéal pour solliciter la partie externe, celle qu'on voit le plus de profil.");
        values.put("imgEx", R.drawable.biceps_curlincline);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl prise marteau haltères");
        values.put("courteDesc","Le curl prise marteau est un exercice de musculation qui cible les biceps mais aussi les avant-bras.");
        values.put("imgEx", R.drawable.biceps_curlhaltere);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl pupitre à la machine");
        values.put("courteDesc","Un exercice d'isolation à la machine idéal pour solliciter, en tension continue, le brachial antérieur et le biceps. ");
        values.put("imgEx", R.drawable.biceps_curlpupitremachine);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl inversé à la barre");
        values.put("courteDesc","Les curls inversés à la barre permettent de renforcer les poignets, les avant-bras et de développer la partie basse du biceps.");
        values.put("imgEx", R.drawable.biceps_curlinverse);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Le curl Araignée");
        values.put("courteDesc","Le curl araignée est un exercice de musculation idéal pour isoler les biceps et obtenir des gros bras. Il a la réputation de faire gagner du « pic » aux biceps.");
        values.put("imgEx", R.drawable.biceps_curlaraignee);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Le curl Gironda");
        values.put("courteDesc","Développez et isolez vos biceps avec le Curl Gironda. Cet exercice de musculation pour les biceps est un des meilleurs choix pour ceux qui ont du mal à muscler les bras. ");
        values.put("imgEx", R.drawable.biceps_curlgironda);
        db.insert("exercices",null, values);

        values.put("idCateg", 1);
        values.put("nomEx","Curl Zottman");
        values.put("courteDesc","Le Curl Zottman est un exercice de musculation qui va vous cramer les biceps et les avant-bras. Si vous souhaitez muscler vos bras et renforcer votre poigne, le curl Zottman est fait pour vous…");
        values.put("imgEx", R.drawable.biceps_curlzottman);
        db.insert("exercices",null, values);
        values.clear();

        values.put("idEx",1);
        values.put("longueDesc","Sur cet exercice, on constate souvent que les pratiquants trichent et s’aident de l’élan en se penchant en avant et en donnant une impulsion avec le bas du dos. Ensuite, ils avancent les coudes vers l’avant en s’aidant de l'avant de l'épaule (deltoïde antérieur) et du haut des pectoraux. En réalisant l'exercice de cette façon, le biceps n’est pas sollicité sur toute l’amplitude et le recrutement des fibres n’est pas maximum. La stimulation musculaire et donc le développement seront lésés." +
                "Pour une bonne forme d’exécution, gardez les bras collés contre les flancs et perpendiculaires au sol." +
                "Pour éviter l’élan, gardez le dos immobile et droit, les genoux fléchis ou avancez une jambe pour stabiliser le corps. Il est aussi possible de réaliser l'exercice de façon encore plus stricte, en maintenant le dos contre un mur. Cela évite de tricher et c'est d'ailleurs de cette façon qu'il est exécuté lors de compétitions de curls à la barre.");
        values.put("muscle","Le biceps brachial, courte et longue portion, le brachial antérieur et le long supinateur.");
        values.put("execution","En position de départ debout, le dos immobile et droit, les genoux fléchis ou une jambe avancée pour éviter de tricher en s'aidant de l'élan et les coudes prés du corps. Monter et descendre la barre sans à-coups. Vous pouvez varier l'écartement des mains en utilisant une prise large, moyenne ou serrée.");
        values.put("lien","https://www.youtube.com/watch?v=ZLRBO5wiPwM");
        values.put("imgDet", R.drawable.intro_curlbarre);
        db.insert("details", null, values);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int
            oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS details");
        db.execSQL("DROP TABLE IF EXISTS exercices");
        db.execSQL("DROP TABLE IF EXISTS categories");
        onCreate(db);
    }
}


