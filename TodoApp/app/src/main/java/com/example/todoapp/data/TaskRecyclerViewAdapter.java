package com.example.todoapp.data;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import android.view.LayoutInflater;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.todoapp.TaskViewModel;

public class TaskRecyclerViewAdapter extends ListAdapter<Task, ViewHolder> {
    private final TaskViewModel mTaskViewModel;

    public TaskRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback, TaskViewModel mTaskViewModel) {
        super(diffCallback);
        this.mTaskViewModel=mTaskViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Task current = getItem(position);
        viewHolder.bind(current, mTaskViewModel);
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
