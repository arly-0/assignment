package com.playtech.enums;

public enum MatchSide {
    A("A"),
    B("B"),
    DRAW("DRAW");

    private final String matchSide;

    private MatchSide(String matchSide) {
        this.matchSide = matchSide;
    }

    public String getMatchSide() {
        return matchSide;
    }
}
