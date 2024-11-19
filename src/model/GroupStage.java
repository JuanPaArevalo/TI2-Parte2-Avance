package model;

import java.util.Random;

public class GroupStage {
    private Team[] groupA;
    private Team[] groupB;
    private Match[] matches;  // Array of Match objects instead of String[] for better data handling
    private String[] dates;
    private int[][] finalStadingsGroupA;
    private int[][] finalStadingsGroupB;

    public GroupStage() {
        this.groupA = new Team[4];
        this.groupB = new Team[4];
        this.matches = new Match[12]; // 6 matches per group for a total of 12 matches
        this.dates = new String[12];
        this.finalStadingsGroupA = new int[4][9];
        this.finalStadingsGroupB = new int[4][9];
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
    /*public String[] assignReferees(Team[] teams, Referee[] referees, String group) {
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
    }*/


    public String[] assignReferee(Match[] matches, Team[] teams, Referee[] referees) {
        String[] assignedRefereesMessages = new String[matches.length]; // Mensajes para cada partido
        int refereeIndex = 0;
    
        // Filtrar árbitros y asignarlos a los partidos
        for (int i = 0; i < matches.length; i++) {
            Match match = matches[i];
            if (match != null) {
                for (; refereeIndex < referees.length; refereeIndex++) {
                    Referee referee = referees[refereeIndex];
                    if (referee != null 
                        && !referee.getCountry().equals(match.getHomeTeam().getCountry()) 
                        && !referee.getCountry().equals(match.getAwayTeam().getCountry())) {
                        
                        // Asignar árbitro al partido
                        match.setCentralReferee(referee);
                        
                        // Generar mensaje descriptivo
                        assignedRefereesMessages[i] = "Match: " + match.getHomeTeam().getName() + " vs " + match.getAwayTeam().getName() +
                                                      " - Referee: " + referee.getName() + " (" + referee.getRefType() + ")";
                        
                        // Pasar al siguiente árbitro disponible
                        refereeIndex++;
                        break;
                    }
                }
            }
        }
    
        return assignedRefereesMessages;
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

   

   public String generateStandings() {
        int[][] standingsGroupA = new int[4][9]; // 4 equipos, columnas: PJ, G, E, P, GF, GC, DG, Pts, Índice del equipo
        int[][] standingsGroupB = new int[4][9];

        // Calcular las estadísticas para el grupo A y B
        for (Match match : matches) {
            if (match != null) {
                if (isTeamInGroup(match.getHomeTeam(), groupA)) {
                    updateStandings(standingsGroupA, match.getHomeTeam(), match.getAwayTeam(), match.getHomeScore(), match.getAwayScore(), groupA);
                } else if (isTeamInGroup(match.getHomeTeam(), groupB)) {
                    updateStandings(standingsGroupB, match.getHomeTeam(), match.getAwayTeam(), match.getHomeScore(), match.getAwayScore(), groupB);
                }
            }
        }

        // Ordenar las tablas por los criterios
        sortStandings(standingsGroupA);
        sortStandings(standingsGroupB);

        // Crear las tablas como cadenas de texto
        String result = "Group A Standings:\n";
        result += formatStandings(standingsGroupA, groupA);
        result += "\nGroup B Standings:\n";
        result += formatStandings(standingsGroupB, groupB);
        finalStadingsGroupA = standingsGroupA;
        finalStadingsGroupB = standingsGroupB;

        return result;
    }

    private void updateStandings(int[][] standings, Team home, Team away, int homeScore, int awayScore, Team[] group) {
        int homeIndex = getTeamIndex(home, group);
        int awayIndex = getTeamIndex(away, group);

        // Actualizar PJ (Partidos Jugados)
        standings[homeIndex][0]++; // PJ para el equipo local
        standings[awayIndex][0]++; // PJ para el equipo visitante

        // Calcular resultado del partido
        if (homeScore > awayScore) {
            // Victoria del equipo local
            standings[homeIndex][1]++; // G: Ganados
            standings[homeIndex][7] += 3; // Pts: Puntos
            standings[awayIndex][3]++; // P: Perdidos
        } else if (homeScore < awayScore) {
            // Victoria del equipo visitante
            standings[awayIndex][1]++; // G: Ganados
            standings[awayIndex][7] += 3; // Pts: Puntos
            standings[homeIndex][3]++; // P: Perdidos
        } else {
            // Empate
            standings[homeIndex][2]++; // E: Empatados
            standings[awayIndex][2]++;
            standings[homeIndex][7] += 1; // Pts: Puntos
            standings[awayIndex][7] += 1;
        }

        // Actualizar GF (Goles a favor) y GC (Goles en contra)
        standings[homeIndex][4] += homeScore; // GF
        standings[homeIndex][5] += awayScore; // GC
        standings[awayIndex][4] += awayScore; // GF
        standings[awayIndex][5] += homeScore; // GC

        // Calcular DG (Diferencia de Gol)
        standings[homeIndex][6] = standings[homeIndex][4] - standings[homeIndex][5];
        standings[awayIndex][6] = standings[awayIndex][4] - standings[awayIndex][5];

        // Guardar el índice del equipo
        standings[homeIndex][8] = homeIndex;
        standings[awayIndex][8] = awayIndex;
    }

    private void sortStandings(int[][] standings) {
        // Ordenar por Pts (desc), DG (desc), GF (desc), GC (asc)
        java.util.Arrays.sort(standings, (a, b) -> {
            if (b[7] != a[7]) return b[7] - a[7]; // Pts: Puntos
            if (b[6] != a[6]) return b[6] - a[6]; // DG: Diferencia de Gol
            if (b[4] != a[4]) return b[4] - a[4]; // GF: Goles a favor
            return a[5] - b[5]; // GC: Goles en contra
        });
    }

    private String formatStandings(int[][] standings, Team[] group) {
        StringBuilder sb = new StringBuilder();
        sb.append("Pos | Team                  | P | W  | D  | L  | GF | GA | GD | Pts\n");
        sb.append("--------------------------------------------------------------------\n");
        for (int i = 0; i < standings.length; i++) {
            int teamIndex = standings[i][8];
            Team team = group[teamIndex];
            sb.append(String.format("%-4d| %-20s | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-2d | %-3d\n",
                    (i + 1), // Posición
                    team.getName(), // Nombre del equipo
                    standings[i][0], // PJ
                    standings[i][1], // G
                    standings[i][2], // E
                    standings[i][3], // P
                    standings[i][4], // GF
                    standings[i][5], // GC
                    standings[i][6], // DG
                    standings[i][7]  // Pts
            ));
        }
        return sb.toString();
    }


    private boolean isTeamInGroup(Team team, Team[] group) {
        for (Team t : group) {
            if (t != null && t.equals(team)) {
                return true;
            }
        }
        return false;
    }

    private int getTeamIndex(Team team, Team[] group) {
        for (int i = 0; i < group.length; i++) {
            if (group[i] != null && group[i].equals(team)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getTeamEfficiency(Team team) {

        String groupName = "";
        int teamIndex = 0;

        for(int i=0; i<groupA.length; i++) {
             if(groupA[i]!=null && groupA[i]==team) {
                groupName = "A";
                teamIndex = i;
             }
        }

        for(int i=0; i<groupB.length; i++) {
            if(groupB[i]!=null && groupB[i]==team) {
               groupName = "B";
               teamIndex = i;
            }
        }

        int positionGamesPlayed = 0;
        int positionGamesWon = 1;

        if(groupName.equals("A")) {

            for(int i=0;i<finalStadingsGroupA.length; i++){
                if(finalStadingsGroupA[i][8]==teamIndex) {

                    int matchesPlayed = finalStadingsGroupA[i][positionGamesPlayed];
                    int matchesWon = finalStadingsGroupA[i][positionGamesWon];

                    return team.calculateTeamEfficiency(matchesPlayed, matchesWon);
                }
            }
            
        } else {

            for(int i=0;i<finalStadingsGroupB.length; i++){
                if(finalStadingsGroupB[i][8]==teamIndex) {

                    int matchesPlayed = finalStadingsGroupB[i][positionGamesPlayed];
                    int matchesWon = finalStadingsGroupB[i][positionGamesWon];

                    return team.calculateTeamEfficiency(matchesPlayed, matchesWon);
                }
            }

        }

        return "";
    }

    public String getPlayerEfficiency(Team team, Player player) {

        String groupName = "";
        int teamIndex = 0;

        for(int i=0; i<groupA.length; i++) {
             if(groupA[i]!=null && groupA[i]==team) {
                groupName = "A";
                teamIndex = i;
             }
        }

        for(int i=0; i<groupB.length; i++) {
            if(groupB[i]!=null && groupB[i]==team) {
               groupName = "B";
               teamIndex = i;
            }
        }

        int positionGamesPlayed = 0;

        if(groupName.equals("A")) {

            for(int i=0;i<finalStadingsGroupA.length; i++){
                if(finalStadingsGroupA[i][8]==teamIndex) {

                    int matchesPlayed = finalStadingsGroupA[i][positionGamesPlayed];

                    return team.calculatePlayerEfficiency(matchesPlayed, player);
                }
            }
            
        } else {

            for(int i=0;i<finalStadingsGroupB.length; i++){
                if(finalStadingsGroupB[i][8]==teamIndex) {

                    int matchesPlayed = finalStadingsGroupB[i][positionGamesPlayed];

                    return team.calculatePlayerEfficiency(matchesPlayed, player);
                }
            }

        }

        return "";
    }

    public int obtainMatchesOfAReferee(Referee referee){

        int numMatches = 0;

        for(Match m:matches) {
            if(m!=null && m.verifyCentralReferee(referee)) {
                numMatches++;
            }
        }

        return numMatches;
    }

    public Referee getCentralRefereeOfAMatch(Team homeTeam, Team awayTeam) {
        for(Match m:matches){
            if(m!=null && m.verifyTeamsOfTheGroup(homeTeam,awayTeam)) {
                return m.getCentralReferee();
            }
        }

        return null;
    }


}
