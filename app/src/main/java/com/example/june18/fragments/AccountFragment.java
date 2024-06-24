package com.example.june18.fragments;

import android.content.Intent;
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
import com.example.june18.database.DatabaseManager;
import com.example.june18.utils.MyPref;

public class AccountFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    DatabaseManager databaseManager;
    private Button logoutButton;
    private MyPref myPref;

    public AccountFragment() {
        // Required empty public constructor
    }

    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_acount, container, false);
        initComponent(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initComponent(View view) {
        logoutButton = view.findViewById(R.id.logoutButton);
        myPref = new MyPref(getContext());
        databaseManager = new DatabaseManager(getActivity());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutButton.setOnClickListener(v -> {
            myPref.setEmail("");
            myPref.setName("");
            myPref.setPassword("");
            databaseManager.deleteAccount();

            Intent intent = new Intent(getActivity(), RegisterActivity.class);
            getActivity().finish();
            startActivity(intent);
        });
    }
}