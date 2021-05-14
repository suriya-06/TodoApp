package com.bawp.todoister.adapter;

import com.bawp.todoister.model.Task;

public interface onTodoClick {
    void onTodoClick(int position, Task task);
    void onTodoRadioButton(Task task);
}
