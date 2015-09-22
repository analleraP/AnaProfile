package com.allmydraft.android.mycv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.allmydraft.android.mycv.adapters.AppsRecyclerAdapter;
import com.allmydraft.android.mycv.model.App;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class AppsFragment extends Fragment {
    AppsRecyclerAdapter adapter;

    public AppsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dummy_fragment, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        Gson gson = new Gson();

        final List<App> apps = gson.fromJson(((MainActivity) getActivity()).loadJSONFromAsset("apps.json"), new TypeToken<List<App>>() {
        }.getType());

        adapter = new AppsRecyclerAdapter(getActivity(), apps);
        adapter.SetOnItemClickListener(new AppsRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final String appPackageName = apps.get(position).url;
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}