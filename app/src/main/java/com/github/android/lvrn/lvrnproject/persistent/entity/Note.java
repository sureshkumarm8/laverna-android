package com.github.android.lvrn.lvrnproject.persistent.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.common.base.Optional;

/**
 * @author Vadim Boitsov <vadimboitsov1@gmail.com>
 */

public class Note extends ProfileDependedEntity {

    /**
     * An id of a notebook, which the note is belonged. In case, if the note doesn't belong to any
     * notebook, then notebookId equals to "0".
     */
    private Optional<String> notebookId;

    @NonNull
    private String title;

    /**
     * A date of the model's creation in milliseconds.
     */
    private long creationTime;

    /**
     * A date of the model's creation in milliseconds.
     */
    private long updateTime;

    /**
     * A plain text of a note's content.
     */
    @NonNull
    private String content;

    /**
     * A note's content in html.
     */
    @NonNull
    private String htmlContent;

    private boolean isFavorite = false;

    public Note(String id,
                String profileId,
                Optional<String> notebookId,
                String title,
                long creationTime,
                long updateTime,
                String content,
                String htmlContent,
                boolean isFavorite) {
        this.id = id;
        this.profileId = profileId;
        this.notebookId = notebookId;
        this.title = title;
        this.creationTime = creationTime;
        this.updateTime = updateTime;
        this.content = content;
        this.htmlContent = htmlContent;
        this.isFavorite = isFavorite;
    }

    private Note(Parcel in) {
        id = in.readString();
        profileId = in.readString();
        String notebookId = in.readString();
        this.notebookId = !TextUtils.isEmpty(notebookId) ? Optional.of(notebookId) : Optional.absent();
        title = in.readString();
        creationTime = in.readLong();
        updateTime = in.readLong();
        content = in.readString();
        htmlContent = in.readString();
        isFavorite = in.readByte() != 0;
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" + super.toString() +
                "notebookId='" + (notebookId.isPresent() ? notebookId.get() : "null") + '\'' +
                ", title='" + title + '\'' +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                ", content='" + content + '\'' +
                ", isFavorite=" + isFavorite +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(profileId);
        dest.writeString(notebookId.isPresent() ? notebookId.get() : null);
        dest.writeString(title);
        dest.writeLong(creationTime);
        dest.writeLong(updateTime);
        dest.writeString(content);
        dest.writeString(htmlContent);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public Optional<String> getNotebookId() {
        return notebookId;
    }

    public void setNotebookId(Optional<String> notebookId) {
        this.notebookId = notebookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public static final Parcelable.Creator<Note> CREATOR = new Parcelable.Creator<Note>() {

        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };
}
