package model;

import java.util.Random;

public class GroupStage {
    private Team[] groupA;
    private Team[] groupB;
    private Match[] matches;  // Array of Match objects instead of String[] for better data handling
    private String[] dates;

    public GroupStage() {
        this.groupA = new Team[4];
        this.groupB = new Team[4];
        this.matches = new Match[12]; // 6 matches per group for a total of 12 matches
        this.dates = new String[12];
    }

    // Método para distribuir aleatoriamente 8 equipos en 2 grupos de 4
    public void setTeams(Team[] teams) {
        Random random = new Random();
        boolean[] assigned = new boolean[teams.length]; // Marcadores para saber si un equipo ya fue asignado
        int countGroupA = 0;
        int countGroupB = 0;

        while (countGroupA < 4 || countGroupB < 4) {
            int rand = random.nextInt(teams.length);

            if (!assigned[rand]) {
                assigned[rand] = true;
                
                if (countGroupA < 4) {
                    groupA[countGroupA] = teams[rand];
                    countGroupA++;
                } else if (countGroupB < 4) {
                    groupB[countGroupB] = teams[rand];
                    countGroupB++;
                }
            }
        }
    }

    // Método para crear los partidos en cada grupo
    public String createMatches() {
        String result = "Group Stage Matches:\n";
        int matchIndex = 0;

        // Generación de partidos para el grupo A
        result += "\nGroup A Matches:\n";
        for (int i = 0; i < groupA.length; i++) {
            for (int j = i + 1; j < groupA.length; j++) {
                // Create match instance with teams and assign referees
                Match match = new Match(groupA[i], groupA[j]);
                matches[matchIndex] = match;
                dates[matchIndex] = "Date for match " + (matchIndex + 1);
                result += match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName() +
                        " on " + dates[matchIndex] + "\n";
                matchIndex++;
            }
        }

        // Generación de partidos para el grupo B
        result += "\nGroup B Matches:\n";
        for (int i = 0; i < groupB.length; i++) {
            for (int j = i + 1; j < groupB.length; j++) {
                // Create match instance with teams and assign referees
                Match match = new Match(groupB[i], groupB[j]);
                matches[matchIndex] = match;
                dates[matchIndex] = "Date for match " + (matchIndex + 1);
                result += match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName() +
                        " on " + dates[matchIndex] + "\n";
                matchIndex++;
            }
        }

        return result;
    }

    // Método para asignar árbitros a los partidos según nacionalidad
    public String[] assignReferees(Team[] teams, Referee[] referees, String group) {
        String[] assignedReferees = new String[6]; // Cada grupo tiene 6 partidos

        // Filtrar árbitros disponibles por nacionalidad
        int index = 0;
        for (int i = 0; i < referees.length; i++) {
            if (referees[i] != null) {
                for (int j = 0; j < teams.length; j++) {
                    if (teams[j] != null && !teams[j].getCountry().equals(referees[i].getCountry())) {
                        // Evitar asignar árbitros del mismo país que el equipo
                        if (index < 6) {
                            // Assuming central referees first, followed by assistant referees
                            if (referees[i].getRefType() == RefereeType.CENTRAL) {
                                assignedReferees[index++] = referees[i].getName() + " (Central)";
                            } else {
                                assignedReferees[index++] = referees[i].getName() + " (Assistant)";
                            }
                        }
                    }
                }
            }
        }

        return assignedReferees;
    }

    // Method to register match scores
    public String registerMatchScores() {
        StringBuilder result = new StringBuilder("Match Scores:\n");

        // Generate random scores or get from input
        Random random = new Random();
        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            int homeScore = random.nextInt(5);  // Random score between 0-4
            int awayScore = random.nextInt(5);  // Random score between 0-4
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);

            result.append(match.getHomeTeam().getName())
                  .append(" vs ")
                  .append(match.getAwayTeam().getName())
                  .append(": ")
                  .append(homeScore)
                  .append(" - ")
                  .append(awayScore)
                  .append("\n");
        }

        return result.toString();
    }

    /*
    public String registerGoalAndAssist(int matchID, String scorerName, String assistName) {
        if (matchID < 0 || matchID >= matches.length || matches[matchID] == null) {
            return "invalid match ID.";
        }
        return matches[matchID].registerGoalAndAssist(scorerName, assistName);
    }
    */

   public String showMatchesWithScores(){
        String message = "\n";
        for(int i=0; i<matches.length;i++){
            if(matches[i]!=null){
                message += matches[i].getMatch();
            }
        }

        return message;
   }

   public boolean verifyMatch(String homeTeamName, String awayTeamName) {
        for(Match m: matches) {
            if(m != null && m.obtainHomeTeamName().equalsIgnoreCase(homeTeamName) && m.obtainAwayTeamName().equalsIgnoreCase(awayTeamName)){
                return true;
            }
        }

        return false;
   }


   public boolean addScorerAndAssister(Team homeTeam, Team awayTeam, Player scorer, Player assister){

        for(Match m:matches){
            if(m!=null && m.getHomeTeam()==homeTeam && m.getAwayTeam()==awayTeam){
                return m.addGoalToMatch(scorer,assister);
            }
        }

        return false;

   }
}
