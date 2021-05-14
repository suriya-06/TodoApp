package com.bawp.todoister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.todoister.R;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.util.Utils;
import com.google.android.material.chip.Chip;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Task> taskList;
    private final onTodoClick todoClick;
    public RecyclerViewAdapter(List<Task> taskList,onTodoClick onTodoClick) {
        this.taskList = taskList;
        this.todoClick=onTodoClick;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            Task task=taskList.get(position);
            String formatted= Utils.formatDate(task.dueDate);
            holder.textView.setText(task.getTask());
            holder.chip.setText(formatted);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatRadioButton radioButton;
        public AppCompatTextView textView;
        public Chip chip;
        onTodoClick onTodoClick;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            radioButton=itemView.findViewById(R.id.todo_radio_button);
            textView=itemView.findViewById(R.id.todo_row_todo);
            chip=itemView.findViewById(R.id.todo_row_chip );
            this.onTodoClick= todoClick;
            itemView.setOnClickListener(this);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Task task=taskList.get(getAdapterPosition());
            int id=view.getId();
            if(id==R.id.todo_row_layout){
                onTodoClick.onTodoClick(getAdapterPosition(),task);
            }
            else if (id==R.id.todo_radio_button){
                onTodoClick.onTodoRadioButton(task);
            }
        }
    }
}
