package com.tung.reddit.models;

import java.util.Arrays;

public enum AppVoteType {
    UPVOTE(1), DOWNVOTE(-1);

    private int direction;

    AppVoteType(int direction) {
    }

    public static AppVoteType lookup(Integer direction) throws Exception {
        return Arrays.stream(AppVoteType.values())
                .filter(value -> value.getDirection().equals(direction))
                .findAny()
                .orElseThrow(() -> new Exception("Vote not found"));
    }

    public Integer getDirection() {
        return direction;
    }
}