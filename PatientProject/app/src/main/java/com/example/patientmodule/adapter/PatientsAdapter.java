package com.example.patientmodule.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.patientmodule.R;
import com.example.patientmodule.model.PatientsDetailsModel;
import com.example.patientmodule.model.PatientsMainModel;
import com.example.patientmodule.utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientsAdapter extends RecyclerView.Adapter<PatientsAdapter.MyViewHolder> implements Filterable {

    private List<PatientsDetailsModel> patientsList;
    private List<PatientsDetailsModel> mainfilteredList;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView row_patientName, row_patientEmail, row_patientGender, row_patientAge;


        public MyViewHolder(View view) {
            super(view);
            row_patientName = (TextView) view.findViewById(R.id.row_patientName);
            row_patientEmail = (TextView) view.findViewById(R.id.row_patientEmail);
            row_patientGender = (TextView) view.findViewById(R.id.row_patientGender);
            row_patientAge = (TextView) view.findViewById(R.id.row_patientAge);

        }
    }

    public PatientsAdapter(List<PatientsDetailsModel> moviesList) {
        this.patientsList = moviesList;
        this.mainfilteredList = moviesList;
    }

    @Override
    public PatientsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_patients, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PatientsAdapter.MyViewHolder holder, int position) {
        final PatientsDetailsModel model = mainfilteredList.get(position);

        holder.row_patientName.setText(context.getResources().getString(R.string.patientName)+model.getFullName());
        holder.row_patientEmail.setText(context.getResources().getString(R.string.patientEmail)+model.getEmail());
        holder.row_patientGender.setText(context.getResources().getString(R.string.patientGender)+model.getGender());
        holder.row_patientAge.setText(context.getResources().getString(R.string.patientAge)+model.getAge());

    }

    @Override
    public int getItemCount() {
        if (mainfilteredList != null)
            return mainfilteredList.size();
        else
            return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mainfilteredList = patientsList;
                } else {
                    List<PatientsDetailsModel> filteredList = new ArrayList<>();
                    for (PatientsDetailsModel row : patientsList) {

                        if (row.getFullName().toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    if (filteredList.size() > 0) {
                        mainfilteredList = filteredList;
                    } else {
                        Toast.makeText(context, "No Match", Toast.LENGTH_SHORT).show();
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mainfilteredList;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mainfilteredList = (ArrayList<PatientsDetailsModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
