package com.sadc.game.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

public class GameUtils {
    public static ArrayList<LeaderboardListing> getLeaderBoardListing(final String leaderboardTextFileName){
        FileHandle leaderboardFile = Gdx.files.internal(leaderboardTextFileName);
        String text = leaderboardFile.readString();
        String[] leaderboardStrings = text.split(",");
        ArrayList<LeaderboardListing> leaderboardList = new ArrayList<LeaderboardListing>();
        //create leaderboard object for each score, and place it in the correct order in the list
        for (int i = 0; i < leaderboardStrings.length; i++){
            String[] leaderboardItem = leaderboardStrings[i].split("-");
            LeaderboardListing leaderboardListing = new LeaderboardListing();
            leaderboardListing.setScore(leaderboardItem[0]);
            leaderboardListing.setName(leaderboardItem[1]);
            leaderboardListing.setFinishTime(Long.parseLong(leaderboardItem[2]));
            leaderboardList.add(i, leaderboardListing);
        }
        return leaderboardList;
    }

    public static void writeNewScoresToLeaderboard(final String leaderboardTextFileName, final int leaderboardPosition, final String initials,
                                                   final long leaderboardTime, final String leaderboardTimeString,
                                                   final ArrayList<LeaderboardListing> leaderboardListings){
        LeaderboardListing newLeaderboardListing = new LeaderboardListing();
        newLeaderboardListing.setFinishTime(leaderboardTime);
        newLeaderboardListing.setScore(leaderboardTimeString);
        newLeaderboardListing.setName(initials);

        leaderboardListings.add(leaderboardPosition, newLeaderboardListing);
        leaderboardListings.remove(10);
        String output = "";
        for (LeaderboardListing leaderboardListing: leaderboardListings){
            output += leaderboardListing.toString() + ",";
        }
        output = output.substring(0, output.length() - 1);
        FileHandle leaderboardFile = Gdx.files.local(leaderboardTextFileName);
        leaderboardFile.writeString(output, false);
    }
}
