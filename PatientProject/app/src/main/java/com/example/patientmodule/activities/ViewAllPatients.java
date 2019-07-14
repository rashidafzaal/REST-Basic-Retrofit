package com.example.patientmodule.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.patientmodule.R;
import com.example.patientmodule.adapter.PatientsAdapter;
import com.example.patientmodule.model.PatientsDetailsModel;
import com.example.patientmodule.model.PatientsMainModel;
import com.example.patientmodule.rest.ModelManager;
import com.example.patientmodule.rest.ModelManagerListener;
import com.example.patientmodule.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class ViewAllPatients extends AppCompatActivity {

    private RecyclerView patientsRecyclerView;
    private PatientsAdapter mAdapter;
    private List<PatientsDetailsModel> patientsList = new ArrayList<>();
    private EditText editTextSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_patients);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        setUpRecyclerView();
        callApi();
    }


    private void setUpRecyclerView() {
        patientsRecyclerView = (RecyclerView) findViewById(R.id.patientsRecyclerView);
        mAdapter = new PatientsAdapter(patientsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(ViewAllPatients.this);
        patientsRecyclerView.setLayoutManager(mLayoutManager);
        patientsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(patientsRecyclerView.getContext(), ((LinearLayoutManager) mLayoutManager).getOrientation());
        patientsRecyclerView.addItemDecoration(dividerItemDecoration);
        patientsRecyclerView.setAdapter(mAdapter);
    }


    private void callApi() {

        ModelManager.getAllPatients(new ModelManagerListener() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.body() != null) {

                    PatientsMainModel model = (PatientsMainModel) response.body();
                    if (!model.getError()) {
                        patientsList.addAll(model.getPatientsList());
                        mAdapter.notifyDataSetChanged();
                    } else if (model.getError()) {
                        Toast.makeText(ViewAllPatients.this, "No Data Found", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    String message = Utils.getResponseErrorMessage(ViewAllPatients.this, response);
                    Toast.makeText(ViewAllPatients.this, ":" + message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    public void searchBtn(View view) {
        mAdapter.getFilter().filter(editTextSearch.getText().toString());
    }
}
