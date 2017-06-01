package com.github.android.lvrn.lvrnproject.view.fragment.entitieslist.core.favouriteslist.impl;

import com.github.android.lvrn.lvrnproject.persistent.entity.Note;
import com.github.android.lvrn.lvrnproject.service.core.NoteService;
import com.github.android.lvrn.lvrnproject.service.form.NoteForm;
import com.github.android.lvrn.lvrnproject.util.CurrentState;
import com.github.android.lvrn.lvrnproject.util.PaginationArgs;
import com.github.android.lvrn.lvrnproject.view.fragment.entitieslist.core.favouriteslist.FavouritesListPresenter;
import com.github.android.lvrn.lvrnproject.view.fragment.entitieslist.impl.EntitiesListPresenterImpl;

import java.util.List;

/**
 * @author Andrii Bei <psihey1@gmail.com>
 */

public class FavouritesListPresenterImpl extends EntitiesListPresenterImpl<Note, NoteForm> implements FavouritesListPresenter {

    private NoteService mNoteService;

    public FavouritesListPresenterImpl(NoteService entityService) {
        super(entityService);
        mNoteService = entityService;
    }

    @Override
    protected List<Note> loadMoreForPagination(PaginationArgs paginationArgs) {
        //TODO: change on new method
        return mNoteService.getByProfile(CurrentState.profileId, paginationArgs);
    }

    @Override
    protected List<Note> loadMoreForSearch(String query, PaginationArgs paginationArgs) {
        //TODO: change on new method
        return mNoteService.getByTitle(CurrentState.profileId, query, paginationArgs);
    }
}
