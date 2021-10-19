package com.example.todoapp.data;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.MainActivity2;
import com.example.todoapp.R;

import java.util.ArrayList;
import java.util.List;

public class TaskRecyclerViewAdapter extends ListAdapter<Task, ViewHolder> {

    public TaskRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Task current = getItem(position);
        viewHolder.bind(current);
    }

    public static class TodoDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
