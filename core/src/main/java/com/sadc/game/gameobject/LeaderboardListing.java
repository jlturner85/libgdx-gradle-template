package com.sadc.game.gameobject;

/**
 * @author Justin Turner (t590035)
 *         Date: 12/12/13
 */

public class LeaderboardListing {
    private String name;
    private String score;
    private long finishTime;

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(long finishTime) {
        this.finishTime = finishTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public final String toString(){
        return score + "-" + name + "-" + Long.toString(finishTime);
    }
}
