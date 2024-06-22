package com.example.june18.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.june18.R;
import com.example.june18.activities.RegisterActivity;
import com.example.june18.models.Expense;
import com.google.android.material.textfield.TextInputEditText;

public class UpdateExpenseFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private Expense expense;
    private TextInputEditText et_expense_name;
    private TextInputEditText et_date;
    private TextInputEditText et_amount;
    private TextInputEditText et_note;
    private Button update_expense_button;

    public UpdateExpenseFragment() {
        // Required empty public constructor
    }

    public UpdateExpenseFragment(Expense expense) {
        this.expense = expense;
    }

    public static UpdateExpenseFragment newInstance(String param1, String param2) {
        UpdateExpenseFragment fragment = new UpdateExpenseFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_update_expense, container, false);
        initComponent(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initComponent(View view) {
        et_expense_name = view.findViewById(R.id.et_expense_name);
        et_amount = view.findViewById(R.id.et_amount);
        et_date = view.findViewById(R.id.et_date);
        et_note = view.findViewById(R.id.et_note);
        update_expense_button = view.findViewById(R.id.update_expense_button);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        et_expense_name.setText(expense.getCategory());
        et_amount.setText(String.valueOf(expense.getAmount()));
        et_date.setText(expense.getDate());
        et_note.setText(expense.getNote());

        update_expense_button.setOnClickListener(v -> {
            expense.setCategory(String.valueOf(et_expense_name.getText()));
            expense.setAmount(Double.parseDouble(String.valueOf(et_amount.getText())));
            expense.setDate(String.valueOf(et_date.getText()));
            expense.setNote(String.valueOf(et_note.getText()));
            RegisterActivity.databaseManager.update(expense);
        });
    }
}