package com.allmydraft.android.mycv.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.allmydraft.android.mycv.R;
import com.allmydraft.android.mycv.model.Experience;

import java.util.List;

public class ExperienceRecyclerAdapter extends RecyclerView.Adapter<ExperienceRecyclerAdapter.VersionViewHolder> {
    List<Experience> versionModels;

    Context context;

    public ExperienceRecyclerAdapter(Context context, List<Experience> versionModels) {
        this.versionModels = versionModels;
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.experiences_item, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {

        Experience experience = versionModels.get(i);

        versionViewHolder.title.setText(experience.title);
        versionViewHolder.company.setText(experience.company);
        versionViewHolder.period.setText(experience.period);
        versionViewHolder.description.setText(experience.description);
        versionViewHolder.projects.setText(experience.projects);
    }

    @Override
    public int getItemCount() {
        return versionModels == null ? 0 : versionModels.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder {
        CardView cardItemLayout;
        TextView title;
        TextView company;
        TextView period;
        TextView description;
        TextView projects;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            title = (TextView) itemView.findViewById(R.id.listitem_title);
            company = (TextView) itemView.findViewById(R.id.listitem_company);
            period = (TextView) itemView.findViewById(R.id.listitem_period);
            description = (TextView) itemView.findViewById(R.id.listitem_description);
            projects = (TextView) itemView.findViewById(R.id.listitem_projects);
        }
    }
}