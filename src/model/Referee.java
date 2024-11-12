package model;

public class Referee extends Person implements IWaterDrinker {
    private int id;
    private RefereeType refType;

    public Referee(int id, String name, String country, RefereeType refType) {
        super(name, country);
        this.id = id;
        this.refType = refType;
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

    //Método implementado de la interface
    @Override
    public String drinkWater() {
        return "Im drinking water, YEI :DD";
    }
}
