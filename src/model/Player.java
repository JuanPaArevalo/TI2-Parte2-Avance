package model;

public class Player extends Person implements IWaterDrinker {
    private int playerNumber;
    private PlayerPosition position;
    private int yellowCards;
    private int redCards;
    private int matchesPlayed;
    private int goals;
    private int assists;

    public Player(int playerNumber, String name, String country, PlayerPosition position) {
        super(name, country);
        this.playerNumber = playerNumber;
        this.position = position;
        this.goals = 0;    
        this.assists = 0;  
    }

    public Player(int playerNumber, String name, String country, PlayerPosition position, int yellowCards, int redCards, int matchesPlayed) {
        super(name, country);
        this.playerNumber = playerNumber;
        this.position = position;
        this.yellowCards = 0; 
        this.redCards = 0; 
        this.matchesPlayed = matchesPlayed;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }

    public void incrementGoals() {
        this.goals++;
    }

    public void incrementAssists() {
        this.assists++;
    }

    @Override
    public String drinkWater() {
        return "Im drinking water";
    }

    public void addGoal(){
        goals++;

        System.out.println(name + " has " + goals + " goals.");
    }

    public int getNumberOfGoals() {
        return goals;
    }

    public void addYellowCard() { 
        this.yellowCards++;
        System.out.println(name + " has " + yellowCards + " yellow cards.");
    }

    public void addRedCard() { 
        this.redCards++;
        System.out.println(name + " has " + redCards + " red cards.");
    }

    public int getYellowCards() { 
        return yellowCards;
    }

    public int getRedCards() { 
        return redCards;
    }

    public void addYellowCard(int t) { 
        this.yellowCards++;
    }

    public void addRedCard(int t) { 
        this.redCards++;
    }
}
