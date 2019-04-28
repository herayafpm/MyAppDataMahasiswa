package com.kelompok11.datamahasiswa.dao;

import android.provider.BaseColumns;

public class SkemaDatabaseMahasiswa {
    public static final String DatabaseName = "Universitas.db";
    public static final int DatabaseVer = 1;

    private SkemaDatabaseMahasiswa() {
    }
    public abstract class TableMahasiswa implements BaseColumns{
        public static final String TableName = "Mahasiswa";
        public static final String ColumnName_NIM = "Nim";
        public static final String ColumnName_Nama = "Nama";
    }
}
