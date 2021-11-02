package com.sprite.cloud.automation.framework.base.api.models.bookstore.account.v1.postAccount.response;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostAccountResponse {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("books")
    @Expose
    private List<Book> books = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "PostAccountResponse{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", books=" + books +
                '}';
    }
}