package model;

public class Referee extends Person implements IWaterDrinker {
    private int id;
    private RefereeType refType;
    private int numCards;


    public Referee(int id, String name, String country, RefereeType refType) {
        super(name, country);
        this.id = id;
        this.refType = refType;
        this.numCards = 0;
    }

    // Métodos getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RefereeType getRefType() {
        return refType;
    }

    public void setrefType(RefereeType refType) {
        this.refType = refType;
    }

    public int getNumCards() {
        return numCards;
    }

    public void setNumCards() {
        this.numCards++;
    }

    //Método implementado de la interface
    @Override
    public String drinkWater() {
        return "Im drinking water, YEI :DD";
    }

    public String getRefInformation() {
        return "Name: " + name +
        "\nReferee ID: " + id + 
        "\nType of referee: " + refType + "\n\n"; 
    }

    public String calculateCardIndex(int numMatches) {
        double cardIndex = ( (double) numCards / numMatches )*(100);

        return name + " refereed " + numMatches + " and showed " + numCards + " cards. His card index is: " + cardIndex + "%\n"; 
    }
}
