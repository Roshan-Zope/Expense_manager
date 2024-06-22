package com.example.june18.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.june18.R;
import com.example.june18.models.Expense;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ViewHolder> {
    private List<Expense> expenses;
    private OnItemClickListener onItemClickListener;

    public ExpenseListAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ExpenseListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_list_view, parent, false);

        return new ViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListAdapter.ViewHolder holder, int position) {
        holder.expense_name.setText(expenses.get(position).getCategory());
        holder.expense_amount.setText(String.valueOf(expenses.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView expense_name;
        private TextView expense_amount;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            expense_name = itemView.findViewById(R.id.expense_name);
            expense_amount = itemView.findViewById(R.id.expense_amount);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(v, position);
                    }
                }
            });
        }
    }
}
