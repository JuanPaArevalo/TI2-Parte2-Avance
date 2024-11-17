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
        this.goals = 0;    // Inicializamos en 0
        this.assists = 0;  // Inicializamos en 0
    }

    public Player(int playerNumber, String name, String country, PlayerPosition position, int yellowCards, int redCards, int matchesPlayed) {
        super(name, country);
        this.playerNumber = playerNumber;
        this.position = position;
        this.yellowCards = yellowCards;
        this.redCards = redCards;
        this.matchesPlayed = matchesPlayed;
    }

    // Métodos getters y setters
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

    //Método implementado de la interface
    @Override
    public String drinkWater() {
        return "Im drinking water";
    }

    public void addGoal(){
        goals++;

        System.out.println(name + " tiene " + goals + " goles.");
    }
}
