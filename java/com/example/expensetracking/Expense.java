package com.example.expensetracking;

public class Expense
{
    private String day;
    private String month;
    private String year;
    private String expenseDate;
    private String expenseName;
    private String expenseCategory;
    private String expenseCost;
    private String expenseReason;
    private String expenseNotes;

    public Expense(String expenseDate, String expenseName, String expenseCategory,
                   String expenseCost, String expenseReason, String expenseNotes)
    {
        this.expenseDate = expenseDate;
        this.month = this.expenseDate.substring(0, 2);
        this.day = this.expenseDate.substring(3, 5);
        this.year = this.expenseDate.substring(6);
        this.expenseName = expenseName;
        this.expenseCategory = expenseCategory;
        this.expenseCost = expenseCost;
        this.expenseReason = expenseReason;
        this.expenseNotes = expenseNotes;
    }

    public String GetDay() {return day;}
    public String GetMonth() {return month;}
    public String GetYear() {return year;}
    public String GetDate() {return expenseDate;}
    public String GetName() {return  expenseName;}
    public String GetCategory() {return expenseCategory;}
    public String GetCost() {return  expenseCost;}
    public String GetReason() {return expenseReason;}
    public String GetNotes() {return expenseNotes;}

    public void SetDate(String expenseDate)
    {
        this.expenseDate = expenseDate;
        month = expenseDate.substring(0, 2);
        day = expenseDate.substring(3, 5);
        year = expenseDate.substring(6);
    }
    public void SetName(String expenseName) {this.expenseName = expenseName;}
    public void SetCategory(String expenseCategory) {this.expenseCategory = expenseCategory;}
    public void SetCost(String expenseCost) {this.expenseCost = expenseCost;}
    public void SetReason(String expenseReason) {this.expenseReason = expenseReason;}
    public void SetNotes(String expenseNotes) {this.expenseNotes = expenseNotes;}
}
