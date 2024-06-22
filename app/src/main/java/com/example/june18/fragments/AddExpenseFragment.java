package com.example.june18.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.june18.R;
import com.example.june18.activities.RegisterActivity;
import com.example.june18.database.DatabaseHelper;
import com.example.june18.database.DatabaseManager;
import com.example.june18.models.Expense;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddExpenseFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    private TextInputLayout tl_expense_name;
    private TextInputLayout tl_date;
    private TextInputLayout tl_amount;
    private TextInputLayout tl_note;
    private TextInputEditText et_expense_name;
    private TextInputEditText et_date;
    private TextInputEditText et_amount;
    private TextInputEditText et_note;
    private Button add_expense_button;

    public AddExpenseFragment() {
        // Required empty public constructor
    }

    public static AddExpenseFragment newInstance(String param1, String param2) {
        AddExpenseFragment fragment = new AddExpenseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("myTag", "add expense fragment created");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_expense, container, false);
        initComponent(view);
        Log.d("myTag", "add expense fragment view created");
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("myTag", "add expense fragment view show");

        add_expense_button.setOnClickListener(v -> {
            if (validateData()) {
                Expense expense = new Expense();
                expense.setCategory(et_expense_name.getText().toString().trim());
                expense.setDate(et_date.getText().toString().trim());
                expense.setAmount(Double.parseDouble(et_amount.getText().toString().trim()));
                expense.setNote(et_note.getText().toString().trim());

                insertData(expense);
                Log.d("myTag", "after insert method");
            }
        });
    }

    private void insertData(Expense expense) {
        Log.d("myTag", "in insertData method");
        RegisterActivity.databaseManager.insertData(expense);
    }

    private boolean validateData() {
        List<Boolean> check = new ArrayList<>();

        if (!et_expense_name.getText().toString().trim().equals("")) check.add(true);
        else {
            check.add(false);
            tl_expense_name.setError("Please enter category");
        }

        if (!et_date.getText().toString().trim().equals("")) check.add(true);
        else {
            check.add(false);
            tl_date.setError("Please enter date");
        }

        if (!et_amount.getText().toString().trim().equals("")) check.add(true);
        else {
            check.add(false);
            tl_amount.setError("Please enter amount");
        }

        return !check.contains(false);
    }

    private void initComponent(View view) {
        tl_expense_name = view.findViewById(R.id.tl_expense_name);
        tl_date = view.findViewById(R.id.tl_date);
        tl_amount = view.findViewById(R.id.tl_amount);
        tl_note = view.findViewById(R.id.tl_note);
        et_expense_name = view.findViewById(R.id.et_expense_name);
        et_date = view.findViewById(R.id.et_date);
        et_amount = view.findViewById(R.id.et_amount);
        et_note = view.findViewById(R.id.et_note);
        add_expense_button = view.findViewById(R.id.add_expense_button);
    }
}