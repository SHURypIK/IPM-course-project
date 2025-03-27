package com.example.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;


public class Page<T> {
    private ArrayList<T> content;
    private int number;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private boolean empty;

    public ArrayList<T> getContent() {
        return content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isEmpty() {
        return empty;
    }

    public Page(ArrayList<T> content, int number, int size, long totalElements, int totalPages, boolean last, boolean first, boolean empty) {
        this.content = content;
        this.number = number;
        this.size = size;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
        this.last = last;
        this.first = first;
        this.empty = empty;
    }
}
