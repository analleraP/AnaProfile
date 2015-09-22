package com.allmydraft.android.mycv.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allmydraft.android.mycv.R;
import com.allmydraft.android.mycv.model.App;

import java.util.List;

public class AppsRecyclerAdapter extends RecyclerView.Adapter<AppsRecyclerAdapter.VersionViewHolder> {
    List<App> versionModels;

    Context context;
    OnItemClickListener clickListener;

    public AppsRecyclerAdapter(Context context, List<App> versionModels) {
        this.versionModels = versionModels;
        this.context = context;
    }

    @Override
    public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.apps_item, viewGroup, false);
        VersionViewHolder viewHolder = new VersionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VersionViewHolder versionViewHolder, int i) {

        App app = versionModels.get(i);

        int resource = getImageId(context, app.image);

        versionViewHolder.image.setImageResource(resource);
        versionViewHolder.name.setText(app.name);
        versionViewHolder.company.setText(app.company);
    }

    @Override
    public int getItemCount() {
        return versionModels == null ? 0 : versionModels.size();
    }


    class VersionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        ImageView image;
        TextView name;
        TextView company;

        public VersionViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            name = (TextView) itemView.findViewById(R.id.listitem_name);
            company = (TextView) itemView.findViewById(R.id.listitem_company);
            image = (ImageView) itemView.findViewById(R.id.listitem_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    private int getImageId(Context context, String imageName) {
        return context.getResources().getIdentifier("drawable/" + imageName, null, context.getPackageName());
    }

}