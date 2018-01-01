package com.maher.nowhere.model;

import com.maher.nowhere.utiles.Urls;

import java.io.Serializable;

/**
 * Created by Lincoln on 07/01/16.
 */
public class ChatRoom implements Serializable {
    String id, name, lastMessage, timestamp;
    int unreadCount;
    int idSender;
    int idRecever;
    String urlImg;
    public ChatRoom() {
    }

    public ChatRoom(String id, String name, String lastMessage, String timestamp, int unreadCount, int idSender, int idRecever) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
        this.unreadCount = unreadCount;
        this.idSender = idSender;
        this.idRecever = idRecever;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getIdSender() {
        return idSender;
    }

    public void setIdSender(int idSender) {
        this.idSender = idSender;
    }

    public int getIdRecever() {
        return idRecever;
    }

    public void setIdRecever(int idRecever) {
        this.idRecever = idRecever;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = Urls.IMG_URL_USER+urlImg;
    }
}
