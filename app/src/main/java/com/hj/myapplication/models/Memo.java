package com.hj.myapplication.models;

import java.io.Serializable;

/**
 * Created by USER on 2017-02-07.
 */
// Serializable을 implements해서 직렬화 가능한 객체가 되었다
public class Memo implements Serializable {
    private String title;
    private String contents;

    public Memo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Memo{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
