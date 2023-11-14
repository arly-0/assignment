package com.playtech;

import java.util.List;
import com.playtech.utils.Utils;

public class Main {
    public static void main(String[] args) {

        List<String> playerData = Utils.readData("src/main/resources/player_data.txt");
        List<String> matchData = Utils.readData("src/main/resources/match_data.txt");
    }
}
