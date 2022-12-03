package com.example.expensetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Collections;

public class AddActivity extends AppCompatActivity
{
    private static final String LOG_TAG = AddActivity.class.getSimpleName();
    private EditText editDate, editName, editCategory, editCost, editReason, editNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        editDate = findViewById(R.id.editText_Date);
        editName = findViewById(R.id.editText_Name);
        editCategory = findViewById(R.id.editText_Category);
        editCost = findViewById(R.id.editText_Cost);
        editReason = findViewById(R.id.editText_Reason);
        editNotes = findViewById(R.id.editText_Note);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
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

    public void AddExpense(View view)
    {
        String date = editDate.getText().toString();
        String name = editName.getText().toString();
        String category = editCategory.getText().toString();
        String cost = editCost.getText().toString();
        String reason = editReason.getText().toString();
        String notes = editNotes.getText().toString();
        Expense expense = new Expense(date, name, category, cost, reason, notes);
        MainActivity.expenseList.add(expense);

        if (!MainActivity.categories.contains(category))
        {
            MainActivity.categories.add(category);
            MainActivity.categories.remove("Pick a category");
            Collections.sort(MainActivity.categories);
            MainActivity.categories.add(0, "Pick a category");
        }
        finish();
    }
}