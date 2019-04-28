package com.kelompok11.datamahasiswa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.kelompok11.datamahasiswa.domain.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaDao {
    private Context ctx;
    private MahasiswaDbHelper dbHelper;
    private SQLiteDatabase db;

    public MahasiswaDao(Context ctx) {
        this.ctx = ctx;
    }

    public List<Mahasiswa> semuaMahasiswa(){
        List<Mahasiswa> dataMahasiswa = new ArrayList<>();
        dbHelper = new MahasiswaDbHelper(ctx);
        db = dbHelper.getReadableDatabase();
        String[] daftarKolom ={
                SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM,
                SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_Nama
        };
        String urutan = SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM+" DESC";
        Cursor c = db.rawQuery("SELECT * FROM Mahasiswa ORDER BY Nim",null);
        if(c.moveToFirst()){
            do{
                Mahasiswa m = new Mahasiswa();
                m.setNim(c.getString(0));
                m.setNama(c.getString(1));
                dataMahasiswa.add(m);
            }while (c.moveToNext());
        }
        c.close();
        return dataMahasiswa;
    }
    public void insertData(Mahasiswa m){
        dbHelper = new MahasiswaDbHelper(ctx);
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM,m.getNim());
        cv.put(SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_Nama,m.getNama());
        db.insert(SkemaDatabaseMahasiswa.TableMahasiswa.TableName,null,cv);
    }
    public void updateData(Mahasiswa m,String nim){
        dbHelper = new MahasiswaDbHelper(ctx);
        db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_Nama,m.getNama());
        db.update(SkemaDatabaseMahasiswa.TableMahasiswa.TableName,cv,SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM+"='"+nim+"'",null);
    }
    public void deleteData(String nim){
        dbHelper = new MahasiswaDbHelper(ctx);
        db = dbHelper.getWritableDatabase();
        db.delete(SkemaDatabaseMahasiswa.TableMahasiswa.TableName,
                SkemaDatabaseMahasiswa.TableMahasiswa.ColumnName_NIM +" = '"+nim+"'",null);
    }
}
