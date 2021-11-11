package com.example.todoapp.data;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.MainActivity2;
import com.example.todoapp.R;

class ViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;
    private final CheckBox checkBox;
    private Task task;

    public ViewHolder(View v) {
        super(v);
        textView = (TextView) v.findViewById(R.id.textView);
        checkBox = (CheckBox) v.findViewById(R.id.checkBox2);
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(itemView.getContext(), MainActivity2.class);
                intent.putExtra("IND", task.getTitle());
                textView.getContext().startActivity(intent);
            }
        });
    }

    public void bind(Task task) {
        this.task=task;
        textView.setText(task.getTitle()+"\n"+task.getDdl());
    }
    static ViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item_view, parent, false);
        return new ViewHolder(view);
    }
}