package com.bawp.todoister.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SharedViewModel extends ViewModel {
    MutableLiveData<Task> selectedItem=new MutableLiveData<>();
    private boolean isEdit;
    public void selectItem(Task task){
        selectedItem.setValue(task);
    }
    public LiveData<Task> getSelectItem(){
        return selectedItem;
    }

    public void getEdit(boolean isEdit){
        this.isEdit=isEdit;
    }

    public boolean isEdit() {
        return isEdit;
    }
}
