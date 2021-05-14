
package com.bawp.todoister;

import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.SharedViewModel;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.bawp.todoister.util.Utils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText enterTodo;
    private ImageButton calendarButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calendarGroup;
    private SharedViewModel sharedViewModel;
    private Date dueDate;
    private boolean isEdit;
    Calendar calendar=Calendar.getInstance();

    public BottomSheetFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.bottom_sheet,container , false);
        calendarGroup=view.findViewById(R.id.calendar_group);
        calendarButton=view.findViewById(R.id.today_calendar_button);
        calendarView=view.findViewById(R.id.calendar_view);
        enterTodo=view.findViewById(R.id.enter_todo_et);
        saveButton=view.findViewById(R.id.save_todo_button);
        priorityButton=view.findViewById(R.id.priority_todo_button);
        priorityRadioGroup=view.findViewById(R.id.radioGroup_priority);
        Chip todayChip=view.findViewById(R.id.today_chip);
        todayChip.setOnClickListener(this);
        Chip tommorowChip=view.findViewById(R.id.tomorrow_chip);
        tommorowChip.setOnClickListener(this);
        Chip nextWeekChip=view.findViewById(R.id.next_week_chip);
        nextWeekChip.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        isEdit=sharedViewModel.isEdit();
        Task task1=sharedViewModel.getSelectItem().getValue();
        enterTodo.setText(task1.getTask());
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sharedViewModel=new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        //calendarView
        calendarView.setOnDateChangeListener((calendarView, year, month, day) -> {
            calendar.set(year,month,day);
            dueDate=calendar.getTime();
        });

        //calendarButton
        calendarButton.setOnClickListener(view1 -> {
            calendarGroup.setVisibility(calendarGroup.getVisibility() == View.GONE ? View.VISIBLE
                    : View.GONE);
            Utils.hideSoftKeyboard(view1);
        });

        //saveButton
        saveButton.setOnClickListener(view12 -> {
            String myTask=enterTodo.getText().toString().trim();
            if (!TextUtils.isEmpty(myTask)&&dueDate != null){
                Task task=new Task(myTask, Priority.HIGH, dueDate,
                        Calendar.getInstance().getTime(),false);
                if(isEdit==true){
                    Task updateTask=sharedViewModel.getSelectItem().getValue();
                    updateTask.setTask(myTask);
                    updateTask.setPriority(Priority.HIGH);
                    updateTask.setDueDate(dueDate);
                    updateTask.setOnCreateDate(Calendar.getInstance().getTime());
                    updateTask.setWorkDone(false);
                    TaskViewModel.update(updateTask);
                    sharedViewModel.getEdit(false);
                }
                else {
                    TaskViewModel.insert(task);
                }
                if (this.isVisible()){
                    this.dismiss();
                }

            }

        });
    }

    @Override
    public void onClick(View view) {
        int id= view.getId();
        if (id==R.id.today_chip){
            calendar.add(Calendar.DAY_OF_YEAR,0);
            dueDate=calendar.getTime();
        }
        else if(id==R.id.tomorrow_chip){
            calendar.add(Calendar.DAY_OF_YEAR,1);
            dueDate=calendar.getTime();
        }
        else if (id==R.id.next_week_chip){
            calendar.add(Calendar.DAY_OF_YEAR,7);
            dueDate= calendar.getTime();
        }
    }
}