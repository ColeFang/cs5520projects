package com.example.todoapp.data;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity;
import com.example.todoapp.MainActivity2;
import com.example.todoapp.R;
import com.example.todoapp.TaskViewModel;

class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    private final CheckBox checkBox;
    private final ImageView imageView;
    private Task task;
    private TaskViewModel mTaskViewModel;


    public ViewHolder(View v) {
        super(v);
        textView = (TextView) v.findViewById(R.id.textView);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox2);
        imageView = (ImageView) v.findViewById(R.id.delete);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), MainActivity2.class);
                intent.putExtra("IND", task.getId());
                textView.getContext().startActivity(intent);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mTaskViewModel.deleteTodo(task);
            }
        });
    }

    public void bind(Task task, TaskViewModel mTaskViewModel) {
        this.task=task;
        textView.setText(task.getTitle()+"\n"+task.getDdl());
        this.mTaskViewModel=mTaskViewModel;
    }
    static ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_view, parent, false);
        return new ViewHolder(view);
    }
}