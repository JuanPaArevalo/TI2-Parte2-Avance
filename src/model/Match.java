package model;

import java.util.ArrayList;

public class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;
    private Referee centralReferee;
    private Referee assistantReferee1;
    private Referee assistantReferee2;
    private ArrayList<Goal> goals;
    private int goalCount;
    private ArrayList<String> yellowCards; 
    private ArrayList<String> redCards; 

    // Constructor to initialize teams for the match
    public Match(Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;  // Default score is 0 since its not played yet
        this.awayScore = 0;
        this.centralReferee = null;
        this.assistantReferee1 = null;
        this.assistantReferee2 = null;
        this.goals = new ArrayList<Goal>();
        this.goalCount = 0;
        this.yellowCards = new ArrayList<>(); 
        this.redCards = new ArrayList<>(); 
    }

    public String registerGoalAndAssist(String scorerName, String assistName) {
        if (goalCount >= goals.size()) {
            return "Cannot register more goals in this match.";
        }

        Player scorer = homeTeam.getPlayerByName(scorerName) != null ? homeTeam.getPlayerByName(scorerName) : awayTeam.getPlayerByName(scorerName); //getPlayerByName
        if (scorer == null) {
            return "Scorer not found in either team.";
        }

        Player assist = assistName != null ? (homeTeam.getPlayerByName(assistName) != null ? homeTeam.getPlayerByName(assistName) : awayTeam.getPlayerByName(assistName)) : null;
        goals.add(new Goal(scorer, assist));
        return "Goal and assist registered successfully.";
    }

    // Getters and Setters for the match details
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Referee getCentralReferee() {
        return centralReferee;
    }

    public void setCentralReferee(Referee centralReferee) {
        this.centralReferee = centralReferee;
    }

    public Referee getAssistantReferee1() {
        return assistantReferee1;
    }

    public void setAssistantReferee1(Referee assistantReferee1) {
        this.assistantReferee1 = assistantReferee1;
    }

    public Referee getAssistantReferee2() {
        return assistantReferee2;
    }

    public void setAssistantReferee2(Referee assistantReferee2) {
        this.assistantReferee2 = assistantReferee2;
    }

    public void registerScore(int homeScore, int awayScore){
        this.homeScore=homeScore;
        this.awayScore=awayScore;
    }

    // Method to display match information in a readable format
    public String displayMatchInfo() {
        return "Match: " + homeTeam.getName() + " vs " + awayTeam.getName() +
               "\nScore: " + homeScore + " - " + awayScore +
               "\nReferees: " + (centralReferee != null ? centralReferee.getName() : "Not Assigned") +
               " (Central), " + (assistantReferee1 != null ? assistantReferee1.getName() : "Not Assigned") +
               " (Assistant 1), " + (assistantReferee2 != null ? assistantReferee2.getName() : "Not Assigned") +
               "\n";
    }

    public String getMatch(){
        return homeTeam.getName() + "(" + homeScore + ") " + " vs " + awayTeam.getName() + "(" + awayScore + ")\n"; 
    }


    public String obtainHomeTeamName(){
        return homeTeam.getName();
    }

    public String obtainAwayTeamName(){
        return awayTeam.getName();
    }


    public boolean addGoalToMatch(Player scorer, Player assister){

        Goal goal = new Goal(scorer,assister);
        goal.addGoalToScorer();
        goals.add(goal);
        return true;

    }



    public boolean verifyCentralReferee(Referee referee) {
        if(centralReferee!=null && centralReferee==referee) {
            return true;
        }

        return false;
    }


    public boolean verifyTeamsOfTheGroup(Team homeTeam, Team awayTeam) {
        if(this.homeTeam == homeTeam && this.awayTeam == awayTeam) {
            return true;
        }

        return false;
    }

}
