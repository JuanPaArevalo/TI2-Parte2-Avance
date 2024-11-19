package model;

import java.util.Random;

public class Team {
    private String name;
    private String country;
    private String coach;
    private Player[] players;
    private int playerCount;
    private int yellowCards;
    private int redCards;

    public Team(String name, String country, String coach) {
        this.name = name;
        this.country = country;
        this.coach = coach;
        this.players = new Player[20]; // Maximum 20 players per team
        this.playerCount = 0;
        this.yellowCards = 0;
        this.redCards = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Player createPlayer(int number, String name, String country, PlayerPosition position) {
        Player player = new Player(number, name, country, position);
        return player;    
    }

    public String addPlayer(Player player) {
        for (int i = 0; i < players.length; i++) {
            if(players[i] == null) {
                players[i] = player;
                return "Player succesfully added to team";
            }
        }
        return "Cannot add more players to the team.";
    }

    public Player getPlayerByName(String name) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null && players[i].getName().equalsIgnoreCase(name)) {
                return players[i];
            }
        }
        return null; // player not found
    }

    public Player[] getPlayers() {
        return players;
    }

    public Player getPlayerByNameAndTshirt(String name,int tshirtNumber) {
        for(int i = 0; i < players.length; i++) {
            if(players[i] != null && players[i].getName().equalsIgnoreCase(name) && players[i].getPlayerNumber()==tshirtNumber) {
                return players[i];
            }
        }
        return null;
    }

    public void addYellowCard(){
        this.yellowCards++;
    }

    public void addRedCard(){
        this.redCards++;
    }

    public int getNumberOfCards() {
        return yellowCards + redCards;
    }

    public int getYellowCards() { 
        return yellowCards;
    }

    public int getRedCards() { 
        return redCards;
    }

    public Player getTopScorer() {
        Player topScorer = players[0];

        for(int i = 0; i < players.length; i++) {

            if(players[i] != null && topScorer.getNumberOfGoals() < players[i].getNumberOfGoals()) {
                topScorer = players[i];
            }
        }
        return topScorer;
    }

    public void addCardsToPlayers(){

        Random random = new Random();

        for(Player p:players){
            if(p!=null){
                int numberYellowCards = random.nextInt(3);
                int numberRedCards = random.nextInt(3);

                p.addYellowCard(numberYellowCards);
                yellowCards += numberYellowCards; 
                p.addRedCard(numberRedCards);
                redCards += numberRedCards;
            }
        }
    }

    public String calculateTeamEfficiency(int matchesPlayed, int matchesWon) {

        double efficiencyResult = ((double)matchesWon/matchesPlayed)*(100);

        return name + " played " + matchesPlayed + " matches, and won " + matchesWon + ". Had an efficiency of " + String.format("%.2f", efficiencyResult) + 
        "%\n";
    }

    public String getNamesOfPlayersOfTheTeam(){
        String message = "Here you have the list of the " + name + ":\n\n";

        for(Player p:players){
            if(p!=null) {
                message += p.getName() + ".\n";
            }
        }

        return message + "\n";
    }

    public String calculatePlayerEfficiency(int matchesPlayed, Player player) {
        return player.calculatePlayerEfficiency(matchesPlayed);
    }

}
