package model;

public class Goal {
    private Player scorer;
    private Player assist;

    public Goal(Player scorer, Player assist) {
        this.scorer = scorer;
        this.assist = assist;
    }

    public Player getScorer() {
        return scorer;
    }

    public Player getAssist() {
        return assist;
    }
}
