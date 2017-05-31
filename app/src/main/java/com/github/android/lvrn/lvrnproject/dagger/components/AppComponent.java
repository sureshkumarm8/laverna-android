package com.github.android.lvrn.lvrnproject.dagger.components;

import com.github.android.lvrn.lvrnproject.LavernaApplication;
import com.github.android.lvrn.lvrnproject.dagger.modules.RepositoryModule;
import com.github.android.lvrn.lvrnproject.dagger.modules.ServiceModule;
import com.github.android.lvrn.lvrnproject.view.activity.noteeditor.impl.NoteEditorActivityImpl;
import com.github.android.lvrn.lvrnproject.view.dialog.notebookselection.impl.NotebookSelectionDialogFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.dialog.tagediting.TagEditingDialogFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.NotebookContentFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.allnotebooks.impl.AllNotebooksFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.allentities.core.allnotes.impl.AllNotesFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.alltasks.impl.AllTasksFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.singlenote.SingleNoteFragmentImpl;
import com.github.android.lvrn.lvrnproject.view.fragment.trash.impl.TrashFragmentImpl;

import dagger.Component;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

@Component(modules = {RepositoryModule.class, ServiceModule.class})
public interface AppComponent {
    //TODO:temp compoment, remove and change it later.
    void inject(LavernaApplication application);

    void inject(AllNotesFragmentImpl allNotesFragment);

    void inject(SingleNoteFragmentImpl singleNoteFragmentImpl);

    void inject(TagEditingDialogFragmentImpl tagEditingDialogFragment);

    void inject(NoteEditorActivityImpl noteEditorActivityImpl);

    void inject(AllNotebooksFragmentImpl notebookFragment);

    void inject(NotebookContentFragmentImpl noteAndNotebookTogetherFragment);

    void inject(NotebookSelectionDialogFragmentImpl notebookSelectionDialogFragment);

    void inject(AllTasksFragmentImpl taskFragment);

    void inject(TrashFragmentImpl trashFragment);

}
