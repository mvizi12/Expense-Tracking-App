package com.example.expensetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity
{
    SharedPreferences myPreferences;
    private final String categoryKey = "category";
    private String sharedPrefFile = "com.example.expensetracking";
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static List<Expense> expenseList = new ArrayList<Expense>();
    public static List<String> categories = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        myPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        Set<String> categoriesSet = myPreferences.getStringSet(categoryKey, null);

        categories.clear();
        if (categoriesSet == null)
        {
            categories.add("Pick a category");
            categories.add("Business");
            categories.add("Educational");
            categories.add("Family");
            categories.add("Personal");
            categories.add("Recreation");
        }
        else
        {
            categories.addAll(categoriesSet);
            categories.remove("Pick a category");
            Collections.sort(categories);
            categories.add(0, "Pick a category");
        }
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

        Set<String> categoriesSet = new HashSet<String>();
        categoriesSet.addAll(categories);

        SharedPreferences.Editor preferencesEditor = myPreferences.edit();
        preferencesEditor.putStringSet(categoryKey, categoriesSet);
        preferencesEditor.apply();
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

    public void LaunchAddActivity(View view)
    {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void LaunchEditActivity(View view)
    {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void LaunchDeleteActivity(View view)
    {
        Intent intent = new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    public void LaunchTotalsActivity(View view)
    {
        Intent intent = new Intent(this, TotalsActivity.class);
        startActivity(intent);
    }

    public void LaunchAveragesActivity(View view)
    {
        Intent intent = new Intent(this, AveragesActivity.class);
        startActivity(intent);
    }

    public void Reset(View view)
    {
        SharedPreferences.Editor preferencesEditor = myPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
        expenseList.clear();
        categories.clear();
        categories.add("Pick a category");
        categories.add("Business");
        categories.add("Educational");
        categories.add("Family");
        categories.add("Personal");
        categories.add("Recreation");
    }
}