package com.dovo.wibe.models;

/**
 * Created by Narendran on 19-12-2017.
 */

import com.google.firebase.firestore.IgnoreExtraProperties;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Guest POJO.
 */
@IgnoreExtraProperties
public class Guest {
    public static final String TABLE_NAME = "guest";
    private String guestUserId;
    private @ServerTimestamp Date timestamp;
    private List<String> answers;

    public Guest() {
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = Arrays.asList(answers);
    }

    public String getGuestUserId() {
        return guestUserId;
    }

    public void setGuestUserId(String guestUserId) {
        this.guestUserId = guestUserId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
