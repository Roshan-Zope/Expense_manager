package com.example.june18.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.june18.R;
import com.example.june18.activities.RegisterActivity;
import com.example.june18.models.Expense;

public class ItemViewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private TextView tv_expense_name;
    private TextView tv_expense_category;
    private TextView tv_expense_amount;
    private TextView tv_expense_date;
    private TextView tv_expense_note;
    private Button update_expense_button;
    private Button delete_expense_button;
    private Expense expense;

    public ItemViewFragment() {
        // Required empty public constructor
    }

    public ItemViewFragment(Expense expense) {
        this.expense = expense;
    }

    public static ItemViewFragment newInstance(String param1, String param2) {
        ItemViewFragment fragment = new ItemViewFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_view, container, false);
        initComponent(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initComponent(View view) {
        tv_expense_name = view.findViewById(R.id.tv_expense_name);
        tv_expense_category = view.findViewById(R.id.tv_expense_category);
        tv_expense_amount = view.findViewById(R.id.tv_expense_amount);
        tv_expense_date = view.findViewById(R.id.tv_expense_date);
        tv_expense_note = view.findViewById(R.id.tv_expense_note);
        update_expense_button = view.findViewById(R.id.update_expense_button);
        delete_expense_button = view.findViewById(R.id.delete_expense_button);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_expense_name.setText(expense.getName());
        tv_expense_category.setText(expense.getCategory());
        tv_expense_date.setText(expense.getDate());
        tv_expense_amount.setText(String.valueOf(expense.getAmount()));
        tv_expense_note.setText(expense.getNote());

        update_expense_button.setOnClickListener(v -> {
            loadFragment(new UpdateExpenseFragment(expense));
        });

        delete_expense_button.setOnClickListener(v -> {
            RegisterActivity.databaseManager.delete(expense.getExpenseId());
            removeFragment(R.id.fragment_layout);
            loadFragment(new HomeFragment());
        });
    }

    private void removeFragment(int fragmentItemView) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(fragmentItemView);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d("myTag", "Fragment loaded");
    }
}