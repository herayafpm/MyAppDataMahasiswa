package com.kelompok11.datamahasiswa;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kelompok11.datamahasiswa.dao.MahasiswaDao;
import com.kelompok11.datamahasiswa.domain.Mahasiswa;
import com.kelompok11.datamahasiswa.fragment.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment(new DashboardFragment());
    }
    private void loadFragment(Fragment fr){
        getSupportFragmentManager().beginTransaction().replace(R.id.flEmpty,fr).commit();
    }
    private void insertDummy(){
        MahasiswaDao dataMahasiswa = new MahasiswaDao(this);
        Mahasiswa m1 = new Mahasiswa("17.12.0100","Heraya Fitra");
//        Mahasiswa m2 = new Mahasiswa("17.12.0150","Uga Syahril");
//        dataMahasiswa.insertData(m1);
        dataMahasiswa.insertData(m1);
    }
}
