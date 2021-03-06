package com.github.android.lvrn.lvrnproject.service.form;

import android.support.annotation.NonNull;

import com.github.android.lvrn.lvrnproject.persistent.entity.TrashDependedEntity;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public abstract class TrashDependedForm<T extends TrashDependedEntity> extends ProfileDependedForm<T> {

    protected boolean isTrash = false;

    public TrashDependedForm(@NonNull String profileId, boolean isTrash) {
        super(profileId);
        this.isTrash = isTrash;
    }

    public boolean isTrash() {
        return isTrash;
    }

    public void setTrash(boolean trash) {
        isTrash = trash;
    }
}
