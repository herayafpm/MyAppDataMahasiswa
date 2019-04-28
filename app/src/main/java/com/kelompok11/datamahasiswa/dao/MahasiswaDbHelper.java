package com.kelompok11.datamahasiswa.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MahasiswaDbHelper extends SQLiteOpenHelper {
    public MahasiswaDbHelper(Context context) {
        super(context, SkemaDatabaseMahasiswa.DatabaseName, null, SkemaDatabaseMahasiswa.DatabaseVer);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE = "CREATE TABLE "+SkemaDatabaseMahasiswa.TableMahasiswa.TableName+"("
                +SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM+" TEXT PRIMARY KEY," +
                SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_Nama+" TEXT )";
        db.execSQL(SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS "+SkemaDatabaseMahasiswa.TableMahasiswa.TableName;
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }
}
