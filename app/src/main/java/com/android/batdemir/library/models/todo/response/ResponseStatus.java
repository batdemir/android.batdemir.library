package com.android.batdemir.library.models.todo.response;

public enum ResponseStatus {
    FAIL(0),
    SUCCESS(1),
    DUPLICATE(2),
    NOT_FOUND(3);

    private int value;

    public int getValue() {
        return value;
    }

    ResponseStatus(int value) {
        this.value = value;
    }
}
