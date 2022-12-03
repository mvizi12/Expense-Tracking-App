package com.example.expensetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteActivity extends AppCompatActivity
{
    private static final String LOG_TAG = DeleteActivity.class.getSimpleName();
    String name;
    List<String> expenseNames = new ArrayList<String>();
    Spinner deleteSpinner;
    ArrayAdapter<String> deleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        GetExpenseNames();
        deleteSpinner = findViewById(R.id.spinner_delete);
        deleteAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expenseNames);
        deleteAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        deleteSpinner.setAdapter(deleteAdapter);
        deleteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                name = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    private void GetExpenseNames()
    {
        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            expenseNames.add(MainActivity.expenseList.get(i).GetName());
        }
    }

    public void DeleteElement(View view)
    {
        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            if (MainActivity.expenseList.get(i).GetName().equalsIgnoreCase(name))
            {
                MainActivity.expenseList.remove(i);
                break;
            }
        }
        finish();
    }
}