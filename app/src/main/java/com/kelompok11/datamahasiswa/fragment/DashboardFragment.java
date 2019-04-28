package com.kelompok11.datamahasiswa.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.kelompok11.datamahasiswa.MainActivity;
import com.kelompok11.datamahasiswa.R;
import com.kelompok11.datamahasiswa.adapter.MahasiswaAdapter;
import com.kelompok11.datamahasiswa.dao.MahasiswaDao;
import com.kelompok11.datamahasiswa.domain.Mahasiswa;

public class DashboardFragment extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SwipeRefreshLayout swRefresh = view.findViewById(R.id.swRefresh);
        swRefresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary);
        swRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swRefresh.setRefreshing(false);
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        getActivity().finish();
                        startActivity(intent);
                    }
                },2000);
            }
        });
    }
    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fr_dashboard,container,false);
        ListView lvMahasiswa = rootView.findViewById(R.id.lv_daftarmahasiswa);
        final MahasiswaDao dataMahasiswa = new MahasiswaDao(getContext());
        final MahasiswaAdapter adapter = new MahasiswaAdapter(getContext(),R.layout.lv_data_mahasiswa,dataMahasiswa.semuaMahasiswa());
        lvMahasiswa.setAdapter(adapter);
        lvMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                MahasiswaAdapter ma = new MahasiswaAdapter(getContext(),position,dataMahasiswa.semuaMahasiswa());
                final Mahasiswa m = ma.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Options")
                        .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
                                View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_mhs,null);
                                final EditText etNim = dialogView.findViewById(R.id.etNIM);
                                final EditText etNama = dialogView.findViewById(R.id.etNama);
                                etNim.setText(m.getNim());
                                etNama.setText(m.getNama());
                                builder2.setView(dialogView);
                                builder2.setTitle("Ubah Data Mahasiswa");
                                builder2.setPositiveButton("Ubah", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(TextUtils.isEmpty(etNim.getText().toString())||TextUtils.isEmpty(etNama.getText().toString())){
                                            Toast.makeText(getContext(),"Semua field harus diisi",Toast.LENGTH_SHORT).show();
                                        }else {
                                            MahasiswaDao dataMahasiswa = new MahasiswaDao(getContext());
                                            Mahasiswa m = new Mahasiswa(etNim.getText().toString(), etNama.getText().toString());
                                            dataMahasiswa.updateData(m, m.getNim());
                                            Toast.makeText(getContext(), "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            getActivity().finish();
                                            startActivity(intent);
                                        }
                                    }
                                });
                                AlertDialog dialog2 = builder2.create();
                                dialog2.show();
                            }
                        })
                        .setNegativeButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Konfirmasi")
                                        .setMessage("Anda yakin ingin menghapusnya")
                                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                MahasiswaDao dataMahasiswa = new MahasiswaDao(getContext());
                                                dataMahasiswa.deleteData(m.getNim());
                                                Toast.makeText(getContext(),"Data berhasil dihapus",Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(getContext(),MainActivity.class);
                                                getActivity().finish();
                                                startActivity(intent);
                                            }
                                        }).setNegativeButton("Tidak",null);
                                AlertDialog dialoghapus = builder.create();
                                dialoghapus.show();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        FloatingActionButton fabAdd = rootView.findViewById(R.id.fabTambah);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_mhs,null);
                final EditText etNim = dialogView.findViewById(R.id.etNIM);
                final EditText etNama = dialogView.findViewById(R.id.etNama);
                builder.setView(dialogView);
                builder.setTitle("Tambah Data Mahasiswa");
                builder.setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(TextUtils.isEmpty(etNim.getText().toString())||TextUtils.isEmpty(etNama.getText().toString())){
                            Toast.makeText(getContext(),"Semua field harus diisi",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            MahasiswaDao dataMahasiswa = new MahasiswaDao(getContext());
                            Mahasiswa m = new Mahasiswa(etNim.getText().toString(),etNama.getText().toString());
                            dataMahasiswa.insertData(m);
                            Toast.makeText(getContext(),"Data berhasil ditambahkan",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(),MainActivity.class);
                            getActivity().finish();
                            startActivity(intent);
                        }

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rootView;
    }
}
