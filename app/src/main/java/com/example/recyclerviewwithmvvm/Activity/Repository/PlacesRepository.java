package com.example.recyclerviewwithmvvm.Activity.Repository;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewwithmvvm.Activity.Model.NicePlaces;
import com.example.recyclerviewwithmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class PlacesRepository {


    public static PlacesRepository instance;

    static List<NicePlaces> list = new ArrayList<>();

    public static PlacesRepository getInstance() {

        if (instance == null) {
            instance = new PlacesRepository();
        }
        return instance;
    }


    public MutableLiveData<List<NicePlaces>> getNicePlaces() {

        MutableLiveData<List<NicePlaces>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(getHardCodedData());
        return listMutableLiveData;
    }


    public static List<NicePlaces> getHardCodedData() {
        for (int i = 0; i < 15; i++) {

            NicePlaces nicePlaces = new NicePlaces(R.mipmap.ic_launcher, "Test Icon");
            list.add(nicePlaces);
        }
        return list;
    }
}
