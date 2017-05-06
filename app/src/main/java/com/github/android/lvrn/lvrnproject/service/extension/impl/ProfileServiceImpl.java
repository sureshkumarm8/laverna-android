package com.github.android.lvrn.lvrnproject.service.extension.impl;

import android.support.annotation.NonNull;

import com.github.android.lvrn.lvrnproject.persistent.entity.Profile;
import com.github.android.lvrn.lvrnproject.persistent.repository.extension.ProfileRepository;
import com.github.android.lvrn.lvrnproject.service.extension.ProfileService;
import com.github.android.lvrn.lvrnproject.service.form.ProfileForm;
import com.github.android.lvrn.lvrnproject.service.impl.BasicServiceImpl;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class ProfileServiceImpl extends BasicServiceImpl<Profile, ProfileForm> implements ProfileService {

    private final ProfileRepository mProfileRepository;

    @Inject
    public ProfileServiceImpl(@NonNull ProfileRepository profileRepository) {
        super(profileRepository);
        mProfileRepository = profileRepository;
    }

    @Override
    public void create(@NonNull ProfileForm profileForm) {
        checkName(profileForm.getName());
        mProfileRepository.add(new Profile(UUID.randomUUID().toString(), profileForm.getName()));
    }

    @NonNull
    @Override
    public List<Profile> getAll() {
        return mProfileRepository.getAll();
    }
}