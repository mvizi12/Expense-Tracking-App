package com.example.expensetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EditActivity extends AppCompatActivity
{
    private static final String LOG_TAG = EditActivity.class.getSimpleName();
    private String category, expense = "";
    private List<String> expenseNames = new ArrayList<String>();
    Spinner spinnerCategories, spinnerExpenses;
    ArrayAdapter<String> categoriesAdapter, expensesAdapter;
    private EditText editDate, editName, editCategory, editCost, editReason, editNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        editDate = findViewById(R.id.editText_Date);
        editName = findViewById(R.id.editText_Name);
        editCategory = findViewById(R.id.editText_Category);
        editCost = findViewById(R.id.editText_Cost);
        editReason = findViewById(R.id.editText_Reason);
        editNotes = findViewById(R.id.editText_Note);

        spinnerCategories = findViewById(R.id.spinner_categories);
        spinnerExpenses = findViewById(R.id.spinner_expenses);
        categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCategories.setAdapter(categoriesAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                expenseNames.clear();
                category = adapterView.getItemAtPosition(i).toString();
                GetExpensesByCategory();
                FillSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        spinnerExpenses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                expense = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
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

    //Goes through each expense within the expense list in the "MainActivity" file
    //Adds each expense's name to the "expenseNames" list if it's category matches the user picked category
    private void GetExpensesByCategory()
    {
        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            if (MainActivity.expenseList.get(i).GetCategory().equalsIgnoreCase(category))
            {
                expenseNames.add(MainActivity.expenseList.get(i).GetName());
            }
        }
        Collections.sort(expenseNames);
    }

    private void FillSpinner()
    {
        expensesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, expenseNames);
        expensesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerExpenses.setAdapter(expensesAdapter);
    }

    public void UpdateExpense(View view)
    {
        String date = editDate.getText().toString();
        String name = editName.getText().toString();
        String thisCategory = editCategory.getText().toString();
        String cost = editCost.getText().toString();
        String reason = editReason.getText().toString();
        String notes = editNotes.getText().toString();

        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            if (MainActivity.expenseList.get(i).GetName().equalsIgnoreCase(expense))
            {
                MainActivity.expenseList.get(i).SetDate(date);
                MainActivity.expenseList.get(i).SetName(name);
                MainActivity.expenseList.get(i).SetCategory(thisCategory);
                MainActivity.expenseList.get(i).SetCost(cost);
                MainActivity.expenseList.get(i).SetReason(reason);
                MainActivity.expenseList.get(i).SetNotes(notes);
                break;
            }
        }

        if (!MainActivity.categories.contains(thisCategory))
        {
            MainActivity.categories.add(thisCategory);
            MainActivity.categories.remove("Pick a category");
            Collections.sort(MainActivity.categories);
            MainActivity.categories.add(0, "Pick a category");
        }
    }
}