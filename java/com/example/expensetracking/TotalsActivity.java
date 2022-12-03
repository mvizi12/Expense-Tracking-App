package com.example.expensetracking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TotalsActivity extends AppCompatActivity
{
    private static final String LOG_TAG = EditActivity.class.getSimpleName();
    private String category="", year="", month="", day="";
    Spinner spinnerCategories, spinnerYears, spinnerMonths, spinnerDays;
    ArrayAdapter<String> categoriesAdapter, yearsAdapter, monthsAdapter, daysAdapter;
    private List<String> years = new ArrayList<String>();
    private List<String> months = new ArrayList<String>();
    private List<String> days = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_totals);

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");

        spinnerCategories = findViewById(R.id.spinner_categories);
        spinnerYears = findViewById(R.id.spinner_years);
        spinnerMonths = findViewById(R.id.spinner_months);
        spinnerDays = findViewById(R.id.spinner_days);

        FillYearsList();
        FillMonthsList();
        FillDaysList();

        categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, MainActivity.categories);
        categoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCategories.setAdapter(categoriesAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                category = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        yearsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, years);
        yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerYears.setAdapter(yearsAdapter);
        spinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                year = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        monthsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, months);
        monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMonths.setAdapter(monthsAdapter);
        spinnerMonths.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                month = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        daysAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, days);
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerDays.setAdapter(daysAdapter);
        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
            {
                day = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
    }

    //Goes through each expense within the expense list in the "MainActivity" file
    //Adds each expense's year to the "years" list if it's year isn't already there
    private void FillYearsList()
    {
        years.clear();
        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            String expenseYear = MainActivity.expenseList.get(i).GetYear();
            if (!years.contains(expenseYear)) {years.add(expenseYear);}
        }
        Collections.sort(years);
        years.add(0, "Pick a year");
    }

    private void FillMonthsList()
    {
        months.clear();
        months.add("Pick a month");
        months.add("01");
        months.add("02");
        months.add("03");
        months.add("04");
        months.add("05");
        months.add("06");
        months.add("07");
        months.add("08");
        months.add("09");
        months.add("10");
        months.add("11");
        months.add("12");
    }

    private void FillDaysList()
    {
        days.clear();
        days.add("Pick a day");
        days.add("01");
        days.add("02");
        days.add("03");
        days.add("04");
        days.add("05");
        days.add("06");
        days.add("07");
        days.add("08");
        days.add("09");
        days.add("10");
        days.add("11");
        days.add("12");
        days.add("13");
        days.add("14");
        days.add("15");
        days.add("16");
        days.add("17");
        days.add("18");
        days.add("19");
        days.add("20");
        days.add("21");
        days.add("22");
        days.add("23");
        days.add("24");
        days.add("25");
        days.add("26");
        days.add("27");
        days.add("28");
        days.add("29");
        days.add("30");
        days.add("31");
    }

    public void TotalByYear(View view)
    {
        float total = 0;

        if (year.isEmpty() || year.equals("Pick a year"))
        {
            Log.d(LOG_TAG, "Must pick a year");
            Toast.makeText(TotalsActivity.this, "Must pick a year", Toast.LENGTH_SHORT).show();
            return;
        }
        if (category.isEmpty() || category.equals("Pick a category")) //If user doesn't want to include category
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                if (year.equals(expenseYear)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year is: $" + total, Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                String expenseCategory = MainActivity.expenseList.get(i).GetCategory();
                if (year.equals(expenseYear) && category.equals(expenseCategory)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year and category is: $" + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void TotalByMonth(View view)
    {
        float total = 0;

        if (year.isEmpty() || year.equals("Pick a year") || month.isEmpty() || month.equals("Pick a month"))
        {
            Log.d(LOG_TAG, "Must pick a year and a month");
            Toast.makeText(TotalsActivity.this, "Must pick a year and a month", Toast.LENGTH_SHORT).show();
            return;
        }
        if (category.isEmpty() || category.equals("Pick a category")) //If user doesn't want to include category
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                String expenseMonth = MainActivity.expenseList.get(i).GetMonth();
                Log.d(LOG_TAG, expenseMonth);
                if (year.equals(expenseYear) && month.equals(expenseMonth)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year/month is: $" + total, Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                String expenseMonth = MainActivity.expenseList.get(i).GetMonth();
                String expenseCategory = MainActivity.expenseList.get(i).GetCategory();
                if (year.equals(expenseYear) && month.equals(expenseMonth) && category.equals(expenseCategory)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year/month and category is: $" + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void TotalByDay(View view)
    {
        float total = 0;

        if (year.isEmpty() || year.equals("Pick a year") || month.isEmpty() || month.equals("Pick a month") || day.isEmpty() || day.equals("Pick a day"))
        {
            Log.d(LOG_TAG, "Must pick a year, month, and day");
            Toast.makeText(TotalsActivity.this, "Must pick a year, month, and day", Toast.LENGTH_SHORT).show();
            return;
        }
        if (category.isEmpty() || category.equals("Pick a category")) //If user doesn't want to include category
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                String expenseMonth = MainActivity.expenseList.get(i).GetMonth();
                String expenseDay = MainActivity.expenseList.get(i).GetDay();
                if (year.equals(expenseYear) && month.equals(expenseMonth) && day.equals(expenseDay)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year/month/day is: $" + total, Toast.LENGTH_SHORT).show();
        }
        else
        {
            for (int i = 0; i < MainActivity.expenseList.size(); i++)
            {
                String expenseYear = MainActivity.expenseList.get(i).GetYear();
                String expenseMonth = MainActivity.expenseList.get(i).GetMonth();
                String expenseDay = MainActivity.expenseList.get(i).GetDay();
                String expenseCategory = MainActivity.expenseList.get(i).GetCategory();
                if (year.equals(expenseYear) && month.equals(expenseMonth) && day.equals(expenseDay) && category.equals(expenseCategory)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
            }
            Log.d(LOG_TAG, String.valueOf(total));
            Toast.makeText(TotalsActivity.this, "Your total by chosen year/month/day and category is: $" + total, Toast.LENGTH_SHORT).show();
        }
    }

    public void TotalByCategory(View view)
    {
        float total = 0;

        if (category.isEmpty() || year.equals("Pick a category"))
        {
            Log.d(LOG_TAG, "Must pick a category");
            Toast.makeText(TotalsActivity.this, "Must pick a category", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            String expenseCategory = MainActivity.expenseList.get(i).GetCategory();
            if (category.equals(expenseCategory)) {total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());}
        }
        Log.d(LOG_TAG, String.valueOf(total));
        Toast.makeText(TotalsActivity.this, "Your total by category is: $" + total, Toast.LENGTH_SHORT).show();
    }

    public void TotalCumulative(View view)
    {
        float total = 0;

        for (int i = 0; i < MainActivity.expenseList.size(); i++)
        {
            total += Float.parseFloat(MainActivity.expenseList.get(i).GetCost());
        }
        Log.d(LOG_TAG, String.valueOf(total));
        Toast.makeText(TotalsActivity.this, "Your cumulative total is: $" + total, Toast.LENGTH_SHORT).show();
    }
}