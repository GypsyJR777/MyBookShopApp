package com.github.gypsyjr777.entity.author;

import java.util.Arrays;

public class AuthorBio {
    private String little = "";
    private String other = "";

    public AuthorBio(String bio) {
        int count = 0;
        StringBuilder little = new StringBuilder();
        StringBuilder other = new StringBuilder();

        for (String str: bio.split("\\.")) {
            if (count < 4) {
                little.append(str).append(".");
            } else {
                other.append(str).append(".");
            }
            count++;
        }

        this.little = little.toString();
        this.other = other.toString();
    }

    public String getLittle() {
        return little;
    }

    public void setLittle(String little) {
        this.little = little;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
