package com.example.recyclerviewwithmvvm.Activity.viewModels;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.recyclerviewwithmvvm.Activity.Model.NicePlaces;
import com.example.recyclerviewwithmvvm.Activity.Repository.PlacesRepository;

import java.util.List;

public class MyActivityViewModel extends ViewModel {

    MutableLiveData<List<NicePlaces>> listOfNicePlaces;
    MutableLiveData<Boolean> isUpdating = new MutableLiveData<>();
    PlacesRepository mRepo;


    public MutableLiveData<Boolean> getIsUpdating() {
        return isUpdating;
    }

    public void init() {

        if (listOfNicePlaces != null) {
            return;
        }
        mRepo = PlacesRepository.getInstance();
        listOfNicePlaces = mRepo.getNicePlaces();
    }


    @SuppressLint("StaticFieldLeak")
    public void addNewValue(final NicePlaces nicePlace) {
        isUpdating.postValue(true);
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<NicePlaces> nicePlaces = listOfNicePlaces.getValue();
                nicePlaces.add(nicePlace);
                listOfNicePlaces.postValue(nicePlaces);
                isUpdating.postValue(false);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.execute();


    }


    public LiveData<List<NicePlaces>> getListOfNicePlaces() {
        return listOfNicePlaces;
    }
}
