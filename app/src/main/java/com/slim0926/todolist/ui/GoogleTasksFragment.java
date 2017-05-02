package com.slim0926.todolist.ui;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.slim0926.todolist.R;
import com.slim0926.todolist.helpers.adapters.GoogleTasklistAdapter;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sue on 1/19/17.
 */

public class GoogleTasksFragment extends DialogFragment {

    private static final String GOOGLETASKLISTS = "Google Tasklists";

    GoogleTasklistAdapter mAdapter;

    @BindView(R.id.googleTasksRecyclerview) RecyclerView mGoogleTasksRecyclerview;
    @BindView(R.id.downloadTasklistButton) Button mDownloadTaskListButton;
    @BindView(R.id.uploadTasklistButton) Button mUploadTaskListButton;

    public interface OnItemClickedListener {
        void onDownloadButtonClicked() throws IOException;
        void onUploadButtonClicked();
    }

    static GoogleTasksFragment newInstance() {
        GoogleTasksFragment fragment = new GoogleTasksFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int style = DialogFragment.STYLE_NORMAL;
        //int theme = android.R.style.Theme_Dialog;
        setStyle(style, getTheme());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_googletasks, container, false);
        ButterKnife.bind(this, view);

        mAdapter = new GoogleTasklistAdapter(getContext(),
                ((MainActivity)getActivity()).mGoogleTasklists,
                ((MainActivity)getActivity()).mSelected_Pos,
                ((MainActivity)getActivity()).mTasklistId);
        mGoogleTasksRecyclerview.setAdapter(mAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mGoogleTasksRecyclerview.setLayoutManager(layoutManager);

        mGoogleTasksRecyclerview.setHasFixedSize(true);

        final OnItemClickedListener mainActivity = (OnItemClickedListener)
                ((MainActivity) getActivity()).mMakeRequestTask;
        mDownloadTaskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mainActivity.onDownloadButtonClicked();
                } catch (IOException e) {

                }
            }
        });

        mUploadTaskListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.onUploadButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() == null) {
            return;
        }

        getDialog().getWindow().setWindowAnimations(R.style.dialog_animation_slide);
    }

    //    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        if (((MainActivity)getActivity()).mIsDialog == true) {
//        super.onSaveInstanceState(outState);
//
//    }


    @Override
    public void onCancel(DialogInterface dialog) {
        //super.onCancel(dialog);
        ((MainActivity)getActivity()).mIsDialog = false;
        ((MainActivity)getActivity()).mIsDialogReset = true;
    }
}
