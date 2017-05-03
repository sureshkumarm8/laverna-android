package com.github.android.lvrn.lvrnproject.service.extension.impl;

import com.github.android.lvrn.lvrnproject.persistent.entity.Note;
import com.github.android.lvrn.lvrnproject.persistent.entity.Notebook;
import com.github.android.lvrn.lvrnproject.persistent.entity.Tag;
import com.github.android.lvrn.lvrnproject.persistent.repository.extension.NoteRepository;
import com.github.android.lvrn.lvrnproject.service.extension.NoteService;
import com.github.android.lvrn.lvrnproject.service.extension.NotebookService;
import com.github.android.lvrn.lvrnproject.service.extension.ProfileService;
import com.github.android.lvrn.lvrnproject.service.extension.TagService;
import com.github.android.lvrn.lvrnproject.service.extension.TaskService;
import com.github.android.lvrn.lvrnproject.service.form.NoteForm;
import com.github.android.lvrn.lvrnproject.service.form.TagForm;
import com.github.android.lvrn.lvrnproject.service.form.TaskForm;
import com.github.android.lvrn.lvrnproject.service.impl.ProfileDependedServiceImpl;
import com.github.android.lvrn.lvrnproject.service.util.NoteTextParser;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class NoteServiceImpl extends ProfileDependedServiceImpl<Note, NoteForm> implements NoteService {

    private final NoteRepository mNoteRepository;

    private final TaskService mTaskService;

    private final TagService mTagService;

    private final NotebookService mNotebookService;

    @Inject
    public NoteServiceImpl(NoteRepository noteRepository,
                           TaskService taskService,
                           TagService tagService,
                           ProfileService profileService,
                           NotebookService notebookService) {

        super(noteRepository, profileService);
        mNoteRepository = noteRepository;
        mTaskService = taskService;
        mTagService = tagService;
        mNotebookService = notebookService;
    }

    /**
     * @param profileId
     * @param notebookId
     * @param title
     * @param content
     * @param isFavorite
     * @throws IllegalArgumentException
     */
    @Override
    public void create(NoteForm noteForm) {

        validate(noteForm.getProfileId(), noteForm.getNotebookId(), noteForm.getTitle());

        String noteId = UUID.randomUUID().toString();

        mNoteRepository.add(new Note(
                noteId,
                noteForm.getProfileId(),
                noteForm.getNotebookId(),
                noteForm.getTitle(),
                System.currentTimeMillis(),
                System.currentTimeMillis(),
                noteForm.getContent(),
                noteForm.isFavorite()));

        parseTasksAndTags(noteForm.getProfileId(), noteId, noteForm.getContent());
    }

    @Override
    public List<Note> getByTitle(String title, int from, int amount) {
        return mNoteRepository.getByTitle(title, from, amount);
    }

    @Override
    public List<Note> getByNotebook(Notebook notebook, int from, int amount) {
        return mNoteRepository.getByNotebook(notebook, from, amount);
    }

    @Override
    public List<Note> getByTag(Tag tag, int from, int amount) {
        return mNoteRepository.getByTag(tag, from, amount);
    }

    /**
     * @param entity to update.
     * @throws IllegalArgumentException
     */
    @Override
    public void update(String id, NoteForm noteForm) {
        //TODO: change date of update.
        //TODO: Write what fields to update in database(not to update creation time)
//        validate(entity.getProfileId(), entity.getProfileId(), entity.getTitle());
//        mNoteRepository.update(entity);
    }

    /**
     * @param profileId
     * @param noteId
     * @param content
     * @throws IllegalArgumentException
     */
    private void parseTasksAndTags(String profileId, String noteId, String content) {
        NoteTextParser.parseTasks(content)
                .forEach((description, status) ->
                        mTaskService.create(new TaskForm(profileId, noteId, description, status)));

        NoteTextParser.parseTags(content)
                .forEach(tagName -> mTagService.create(new TagForm(profileId, tagName)));
    }

    /**
     * @param profileId
     * @param notebookId
     * @param title
     * @throws IllegalArgumentException
     */
    private void validate(String profileId, String notebookId, String title) {
        super.checkProfileExistence(profileId);
        checkNotebookExistence(notebookId);
        super.checkName(title);
    }

    /**
     * @param notebookId
     * @throws IllegalArgumentException
     */
    private void checkNotebookExistence(String notebookId) {
        if (notebookId != null && !mNotebookService.getById(notebookId).isPresent()) {
            throw new IllegalArgumentException("The notebook is not found!");
        }
    }
}
