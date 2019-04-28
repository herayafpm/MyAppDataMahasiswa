package com.kelompok11.datamahasiswa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kelompok11.datamahasiswa.R;
import com.kelompok11.datamahasiswa.domain.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends ArrayAdapter<Mahasiswa> {
    public MahasiswaAdapter(Context context, int resource, List<Mahasiswa> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_data_mahasiswa,parent,false);
        }
        TextView tvNim = convertView.findViewById(R.id.tvNim);
        TextView tvNama = convertView.findViewById(R.id.tvNama);
        Mahasiswa m = getItem(position);
        tvNama.setText(m.getNama());
        tvNim.setText(m.getNim());
        return convertView;
    }
}
