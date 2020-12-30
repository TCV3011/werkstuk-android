package be.ehb.werkstukandroid.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> name;

    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<String>();
            name.setValue("Please log in to facebook to continue:");
        }
        return name;
    }
}