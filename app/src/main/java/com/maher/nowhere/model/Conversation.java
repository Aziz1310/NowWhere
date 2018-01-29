package com.maher.nowhere.model;

/**
 * Created by maher on 04/01/2018.
 */

public class Conversation {
    private String id;
    private ConversationUser user1;
    private ConversationUser user2;

    public ConversationUser getUser1() {
        return user1;
    }

    public void setUser1(ConversationUser user1) {
        this.user1 = user1;
    }

    public ConversationUser getUser2() {
        return user2;
    }

    public void setUser2(ConversationUser user2) {
        this.user2 = user2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
