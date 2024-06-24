package com.example.june18.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.june18.R;
import com.example.june18.models.ExpenseReport;

import java.util.List;

public class ExpenseReportAdapter extends RecyclerView.Adapter<ExpenseReportAdapter.ViewHolder> {
    private List<ExpenseReport> reportList;

    public ExpenseReportAdapter(List<ExpenseReport> reportList) {
        this.reportList = reportList;
    }

    @NonNull
    @Override
    public ExpenseReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anaylitics_list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseReportAdapter.ViewHolder holder, int position) {
        holder.category.setText("category: " + reportList.get(position).getCategory());
        holder.avg.setText("AVG: " + String.valueOf(reportList.get(position).getAvg()));
        holder.total.setText("Total: " + String.valueOf(reportList.get(position).getTotal()));
        holder.min.setText("MIN: " + String.valueOf(reportList.get(position).getMin()));
        holder.max.setText("MAX: " + String.valueOf(reportList.get(position).getMax()));
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView category;
        private TextView total;
        private TextView avg;
        private TextView min;
        private TextView max;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
            total = itemView.findViewById(R.id.total);
            avg = itemView.findViewById(R.id.avg);
            min = itemView.findViewById(R.id.min);
            max = itemView.findViewById(R.id.max);
        }
    }
}
