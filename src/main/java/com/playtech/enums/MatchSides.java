package com.playtech.enums;

public enum MatchSides {
    A("A"),
    B("B"),
    DRAW("DRAW");

    private final String matchSide;

    private MatchSides(String matchSide) {
        this.matchSide = matchSide;
    }

    public String getMatchSide() {
        return matchSide;
    }
}
