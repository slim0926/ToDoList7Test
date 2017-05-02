package com.slim0926.todolist.helpers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.slim0926.todolist.R;
import com.slim0926.todolist.model.GoogleTasklist;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sue on 1/15/17.
 */

public class GoogleTasklistAdapter extends
        RecyclerView.Adapter<GoogleTasklistAdapter.GoogleTasklistViewHolder> {

    private GoogleTasklist[] mTasklists;
    private Context mContext;
    public String mTasklistID;
    public int mSelectedPosition;

    public GoogleTasklistAdapter(Context context, GoogleTasklist[] tasklists, int selectedPosition, String tasklistID) {
        mContext = context;
        mTasklists = tasklists;
        mSelectedPosition = selectedPosition;
        mTasklistID = tasklistID;
    }

    @Override
    public GoogleTasklistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.googletasks_item, parent, false);
        GoogleTasklistViewHolder viewHolder = new GoogleTasklistViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GoogleTasklistViewHolder holder, final int position) {
        holder.mTasklistTitle.setText(mTasklists[position].getTitle());

        if (mSelectedPosition == position) {
            holder.itemView.setBackgroundColor(mContext.getResources().getColor(R.color.colorAccent));

        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);

        }

        holder.mTasklistTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTasklistID = mTasklists[position].getID();

                notifyItemChanged(mSelectedPosition);
                if (mSelectedPosition == position) {
                    mSelectedPosition = -1;
                    mTasklistID = "";

                } else {
                    mSelectedPosition = position;

                }
                notifyItemChanged(mSelectedPosition);


            }
        });
    }

    @Override
    public int getItemCount() {
        if (mTasklists == null) {
            return 0;
        }
        return mTasklists.length;
    }


    public class GoogleTasklistViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tasklistTitle) TextView mTasklistTitle;

        public GoogleTasklistViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
