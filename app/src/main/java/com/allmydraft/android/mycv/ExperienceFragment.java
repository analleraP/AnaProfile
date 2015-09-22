package com.allmydraft.android.mycv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allmydraft.android.mycv.adapters.ExperienceRecyclerAdapter;
import com.allmydraft.android.mycv.model.Experience;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class ExperienceFragment extends Fragment {
    ExperienceRecyclerAdapter adapter;

    public ExperienceFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        Gson gson = new Gson();

        List<Experience> experiences = gson.fromJson(((MainActivity) getActivity()).loadJSONFromAsset("experiences.json"), new TypeToken<List<Experience>>() {
        }.getType());

        adapter = new ExperienceRecyclerAdapter(getActivity(), experiences);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
