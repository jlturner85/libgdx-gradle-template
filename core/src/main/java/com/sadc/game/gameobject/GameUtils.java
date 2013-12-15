package com.sadc.game.gameobject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public static String framesToTimeString(long frames) {
        int seconds = (int)(frames % 3600) / 60;
        int millis = (int)(frames % 60) * 100 / 60;
        return frames / 3600 + ":" + (seconds < 10 ? "0" : "") + seconds + "." + (millis < 10 ? "0" : "") + millis;
    }

    public static float setColorByDrawDistance(float drawDistance, SpriteBatch spriteBatch) {
        float color = 1;
        if (drawDistance < 0.23f) {
            color = (drawDistance - 0.03f) * 5;
            if (color < 0) {
                color = 0;
            }
        }
        spriteBatch.setColor(color, color, color, 1);
        //spriteBatch.setColor(color, color, color, 1);
        return color;
    }
}
