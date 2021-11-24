package com.izzat.demo_web.enumeration;

public enum Status {
    COURSE_UP("COURSE_UP"),
    COURSE_DOWN("COURSE_DOWN");
    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
