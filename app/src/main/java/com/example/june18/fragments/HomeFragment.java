package com.example.june18.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.june18.activities.RegisterActivity;
import com.example.june18.adapters.ExpenseListAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.june18.R;
import com.example.june18.database.DatabaseHelper;
import com.example.june18.models.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private FloatingActionButton add_expense_fab;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private ExpenseListAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Expense> expenses;
    private TextView tv_TotalAmount;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    private void initComponent(View view) {
        tv_TotalAmount = view.findViewById(R.id.tv_TotalAmount);
        add_expense_fab = view.findViewById(R.id.add_expense_fab);
        //dbHelper = new DatabaseHelper(getActivity());
        recyclerView = view.findViewById(R.id.expense_list_recyclerView);
        layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        expenses = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponent(view);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_expense_fab.setOnClickListener(v -> {
            loadFragment(new AddExpenseFragment());
        });
        expenses = RegisterActivity.databaseManager.fetchData();
        adapter = new ExpenseListAdapter(expenses);
        recyclerView.setAdapter(adapter);
        tv_TotalAmount.setText(String.valueOf("Total: " + RegisterActivity.databaseManager.getTotalAmount()));

        adapter.setOnItemClickListener((view1, position) -> {
            loadFragment(new ItemViewFragment(expenses.get(position)));
            Log.d("myTag", expenses.get(position).toString());
        });
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