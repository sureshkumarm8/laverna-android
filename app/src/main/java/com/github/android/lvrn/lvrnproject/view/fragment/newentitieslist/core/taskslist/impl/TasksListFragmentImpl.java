package com.github.android.lvrn.lvrnproject.view.fragment.newentitieslist.core.taskslist.impl;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.android.lvrn.lvrnproject.LavernaApplication;
import com.github.android.lvrn.lvrnproject.R;
import com.github.android.lvrn.lvrnproject.persistent.entity.Task;
import com.github.android.lvrn.lvrnproject.service.form.TaskForm;
import com.github.android.lvrn.lvrnproject.view.fragment.newentitieslist.core.taskslist.TasksListFragment;
import com.github.android.lvrn.lvrnproject.view.fragment.newentitieslist.core.taskslist.TasksListPresenter;
import com.github.android.lvrn.lvrnproject.view.fragment.newentitieslist.impl.EntitiesListFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.notecontent.NoteContentFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.util.consts.BundleKeysConst;
import com.github.android.lvrn.lvrnproject.view.util.consts.FragmentConst;

import javax.inject.Inject;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class TasksListFragmentImpl extends EntitiesListFragmentImpl<Task, TaskForm> implements TasksListFragment {

    @Inject TasksListPresenter mTasksListPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LavernaApplication.getsAppComponent().inject(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void openRelatedNote(Task task) {
        NoteContentFragmentImpl noteContentFragmentImpl = new NoteContentFragmentImpl();

        Bundle bundle = new Bundle();
        bundle.putParcelable(BundleKeysConst.BUNDLE_NOTE_OBJECT_KEY, mTasksListPresenter.getNoteByTask(task));
        noteContentFragmentImpl.setArguments(bundle);

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.constraint_container, noteContentFragmentImpl, FragmentConst.TAG_NOTE_CONTENT_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }
}
