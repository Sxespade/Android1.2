package com.example.myapplication;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PrevDisplCitiesAdapter extends RecyclerView.Adapter<PrevDisplCitiesAdapter.ViewHolder> {

    private List<String> dataSource;
    private OnItemClickListener itemClickListener;


    public PrevDisplCitiesAdapter(List<String> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item3, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getTextView().setText(dataSource.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    // Сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View v;
        private TextView textView;
        private String TAG = "Sec";

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v = itemView;
            textView = v.findViewById(R.id.city);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

        }

        public TextView getTextView() {
            return textView;
        }

    }
}