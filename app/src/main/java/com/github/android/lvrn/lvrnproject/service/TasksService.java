package com.github.android.lvrn.lvrnproject.service;

import com.github.android.lvrn.lvrnproject.persistent.entity.impl.Profile;
import com.github.android.lvrn.lvrnproject.persistent.entity.impl.Task;
import com.github.android.lvrn.lvrnproject.service.core.ProfileDependedService;

import java.util.List;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public interface TasksService extends ProfileDependedService<Task> {

    /**
     * @param profileId
     * @param noteId
     * @param description
     * @param isCompleted
     * @throws IllegalArgumentException
     */
    void create(String profileId, String noteId, String description, boolean isCompleted);

    List<Task> getUncompletedByProfile(Profile profile, int from, int amount);
}