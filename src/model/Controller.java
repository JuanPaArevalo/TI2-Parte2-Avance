package model;

import model.Team;
import model.Match; 
import model.Player;
import model.Referee;
import model.PlayerPosition;
import model.RefereeType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.GroupStage;

public class Controller {
    private Team[] teams;
    private Referee[] referees;
    private int refereeCount;
    private GroupStage groupStage;
    private Match[] matches; 

    public Controller() {
        this.teams = new Team[8];
        this.referees = new Referee[12];
        this.refereeCount = 0;
        this.groupStage = new GroupStage();
        this.matches = new Match[12]; 
    }

    /**
     * Description: Creates a new Team instance with provided attributes.
     * @param String name, name of the team. Must not be null or empty.
     * @param String country, country of the team. Must not be null or empty.
     * @param String coach, name of the team's coach. Must not be null or empty.
     * @return Team, returns a new Team instance initialized with provided values.
     */
    public Team createTeam(String name, String country, String coach) {
        Team team = new Team(name, country, coach);
        return team;    
    }

    /**
     * Description: Registers a Team in the array if there is space available.
     * @param Team team, the team object to register. Must not be null.
     * @return String, returns a message indicating success or failure in registering the team.
     */
    public String registerTeam(Team team) {
        for (int i = 0; i < teams.length; i++) {
            if (teams[i] == null) {
                teams[i] = team;
                return "Team registered successfully.";
            }
        } 
        return "Cannot register more teams.";
    }

    /**
     * Description: Searches for a team by name in the teams array.
     * @param String playerTeam, name of the team to search for. Must not be null or empty.
     * @return Team, returns the matching team if found, or null if not found.
     */
    public Team searchTeam(String playerTeam) {
        for(int i = 0; i < teams.length; i++) {
            if(teams[i] != null && teams[i].getName().equalsIgnoreCase(playerTeam)) {
                return teams[i];
            }
        }
        return null;
    }

    /**
     * Description: Registers a player to the specified team by creating and adding a Player.
     * @param Team team, the team to which the player is being added. Must not be null.
     * @param int number, the player's jersey number. Must be unique for each team.
     * @param String name, player's name. Must not be null or empty.
     * @param String country, player's country. Must not be null or empty.
     * @param PlayerPosition position, player's position on the field. Must not be null.
     * @return String, returns the result of the addPlayer method in the Team class.
     */
    public String registerPlayer(Team team, int number, String name, String country, PlayerPosition position) {
        return team.addPlayer(team.createPlayer(number, name, country, position));
    }

    /**
     * Description: Validates and returns the PlayerPosition type based on input string.
     * @param String position, the position name to validate. Acceptable values are "GOALKEEPER", "DEFENDER", "MIDFIELDER", "FORWARD".
     * @return PlayerPosition, returns the matching PlayerPosition enum value or null if invalid input.
     */
    public PlayerPosition selectPlayerPosition(String position) {
        PlayerPosition type = null;
        switch (position) {
            case "GOALKEEPER":
                type = PlayerPosition.GOALKEPEER;
                break;
            case "DEFENDER":
                type = PlayerPosition.DEFENDER;
                break;
            case "MIDFIELDER":
                type = PlayerPosition.MIDFIELDER;
                break;
            case "FORWARD":
                type = PlayerPosition.FORWARD;
                break;
            default:
                type = null;
        }
        return type;
    }

    /**
     * Description: Creates a new Referee instance with provided attributes.
     * @param int id, unique identifier for the referee. Must be positive.
     * @param String name, the name of the referee. Must not be null or empty.
     * @param String country, referee's country. Must not be null or empty.
     * @param RefereeType refType, referee's type (CENTRAL, ASSISTANT). Must not be null.
     * @return Referee, returns a new Referee instance initialized with provided values.
     */
    public Referee createReferee(int id, String name, String country, RefereeType refType) {
        Referee referee = new Referee(id, name, country, refType);
        return referee;    
    }

    /**
     * Description: Registers a referee in the referees array if space is available.
     * @param Referee referee, the referee object to register. Must not be null.
     * @return String, returns a message indicating success or failure in registering the referee.
     */
    public String registerReferee(Referee referee) {
        for (int i = 0; i < referees.length; i++) {
            if (referees[i] == null) {
                referees[i] = referee;
                return "Referee registered successfully.";
            }
        } 
        return "Cannot register referee.";
    }

    /**
     * Description: Validates and returns the RefereeType based on input string.
     * @param String refType, the referee type to validate. Acceptable values are "CENTRAL" and "ASSISTANT".
     * @return RefereeType, returns the matching RefereeType enum value or null if invalid input.
     */
    public RefereeType selectRefereeType(String refType) {
        RefereeType type = null;
        switch (refType) {
            case "CENTRAL":
                type = RefereeType.CENTRAL;
                break;
            case "ASSISTANT":
                type = RefereeType.ASSISTANT;
                break;
            default:
                type = null;
        }
        return type;
    }

    /**
     * Description: Preloads default data for teams, players, and referees.
     * @return String, returns a message indicating successful data preload.
     */
    public String preloadData() {
        Team team1 = createTeam("Barcelona", "Espana", "Hansi Flick");
        registerTeam(team1);
        registerPlayer(team1, 1, "Marc-André ter Stegen", "Alemania", PlayerPosition.GOALKEPEER);
        registerPlayer(team1, 2, "João Cancelo", "Portugal", PlayerPosition.DEFENDER);
        registerPlayer(team1, 3, "Andreas Christensen", "Dinamarca", PlayerPosition.DEFENDER);
        registerPlayer(team1, 4, "Ronald Araújo", "Uruguay", PlayerPosition.DEFENDER);
        registerPlayer(team1, 5, "Inigo Martinez", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team1, 6, "Gavi", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 7, "Ferran Torres", "Espana", PlayerPosition.FORWARD);
        registerPlayer(team1, 8, "Pedri", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 9, "Robert Lewandowski", "Polonia", PlayerPosition.FORWARD);
        registerPlayer(team1, 10, "Ansu Fati", "Espana", PlayerPosition.FORWARD);
        registerPlayer(team1, 11, "Raphinha", "Brasil", PlayerPosition.FORWARD);
        registerPlayer(team1, 15, "João Félix", "Portugal", PlayerPosition.FORWARD);
        registerPlayer(team1, 16, "Oriol Romeu", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 17, "Marcos Alonso", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team1, 18, "Alejandro Balde", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team1, 20, "Sergi Roberto", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 21, "Frenkie de Jong", "Países Bajos", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 22, "Ilkay Gündogan", "Alemania", PlayerPosition.MIDFIELDER);
        registerPlayer(team1, 23, "Jules Koundé", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team1, 24, "Inigo Martinez", "Espana", PlayerPosition.DEFENDER);

        Team team2 = createTeam("Real Vadrid", "Espana", "Ancelotti");
        registerTeam(team2);
        registerPlayer(team2, 1, "Thibaut Courtois", "Bélgica", PlayerPosition.GOALKEPEER);
        registerPlayer(team2, 2, "Dani Carvajal", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team2, 3, "Éder Militão", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team2, 4, "David Alaba", "Austria", PlayerPosition.DEFENDER);
        registerPlayer(team2, 5, "Fran García", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team2, 6, "Eduardo Camavinga", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 7, "Vinícius Jr.", "Brasil", PlayerPosition.FORWARD);
        registerPlayer(team2, 8, "Toni Kroos", "Alemania", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 9, "Joselu", "Espana", PlayerPosition.FORWARD);
        registerPlayer(team2, 10, "Luka Modrić", "Croacia", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 11, "Rodrygo", "Brasil", PlayerPosition.FORWARD);
        registerPlayer(team2, 12, "Aurélien Tchouaméni", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 13, "Andriy Lunin", "Ucrania", PlayerPosition.GOALKEPEER);
        registerPlayer(team2, 14, "Brahim Díaz", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 15, "Federico Valverde", "Uruguay", PlayerPosition.MIDFIELDER);
        registerPlayer(team2, 16, "Antonio Rüdiger", "Alemania", PlayerPosition.DEFENDER);
        registerPlayer(team2, 17, "Lucas Vázquez", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team2, 18, "Ferland Mendy", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team2, 19, "Alvaro Odriozola", "Espana", PlayerPosition.DEFENDER);
        registerPlayer(team2, 20, "Bellingham", "Inglaterra", PlayerPosition.MIDFIELDER);

        Team team3 = createTeam("Liverpool", "Inglaterra", "Jurgen Kloop");
        registerTeam(team3);
        registerPlayer(team3, 1, "Alisson Becker", "Brasil", PlayerPosition.GOALKEPEER);
        registerPlayer(team3, 2, "Trent Alexander-Arnold", "Inglaterra", PlayerPosition.DEFENDER);
        registerPlayer(team3, 3, "Virgil van Dijk", "Paises Bajos", PlayerPosition.DEFENDER);
        registerPlayer(team3, 4, "Ibrahima Konate", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team3, 5, "Andy Robertson", "Escocia", PlayerPosition.DEFENDER);
        registerPlayer(team3, 6, "Fabinho", "Brasil", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 7, "Alexis Mac Allister", "Argentina", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 8, "Dominik Szoboszlai", "Hungria", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 9, "Mohamed Salah", "Egipto", PlayerPosition.FORWARD);
        registerPlayer(team3, 10, "Darwin Nunez", "Uruguay", PlayerPosition.FORWARD);
        registerPlayer(team3, 11, "Diogo Jota", "Portugal", PlayerPosition.FORWARD);
        registerPlayer(team3, 12, "Cody Gakpo", "Países Bajos", PlayerPosition.FORWARD);
        registerPlayer(team3, 13, "Stefan Bajcetic", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 14, "Harvey Elliott", "Inglaterra", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 15, "Curtis Jones", "Inglaterra", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 16, "Thiago Alcantara", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team3, 17, "Joe Gomez", "Inglaterra", PlayerPosition.DEFENDER);
        registerPlayer(team3, 18, "Joel Matip", "Camerún", PlayerPosition.DEFENDER);
        registerPlayer(team3, 19, "Adrian", "Espana", PlayerPosition.GOALKEPEER);
        registerPlayer(team3, 20, "Kostas Tsimikas", "Grecia", PlayerPosition.DEFENDER);

        Team team4 = createTeam("Manchester United", "Inglaterra", "Eric Tenhag");
        registerTeam(team4);
        registerPlayer(team4, 1, "Andre Onana", "Camerun", PlayerPosition.GOALKEPEER);
        registerPlayer(team4, 2, "Diogo Dalot", "Portugal", PlayerPosition.DEFENDER);
        registerPlayer(team4, 3, "Victor Lindelof", "Suecia", PlayerPosition.DEFENDER);
        registerPlayer(team4, 4, "Raphael Varane", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team4, 5, "Harry Maguire", "Inglaterra", PlayerPosition.DEFENDER);
        registerPlayer(team4, 6, "Lisandro Martinez", "Argentina", PlayerPosition.DEFENDER);
        registerPlayer(team4, 7, "Casemiro", "Brasil", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 8, "Bruno Fernandes", "Portugal", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 9, "Christian Eriksen", "Dinamarca", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 10, "Mason Mount", "Inglaterra", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 11, "Marcus Rashford", "Inglaterra", PlayerPosition.FORWARD);
        registerPlayer(team4, 12, "Rasmus Hojlund", "Dinamarca", PlayerPosition.FORWARD);
        registerPlayer(team4, 13, "Antony", "Brasil", PlayerPosition.FORWARD);
        registerPlayer(team4, 14, "Jadon Sancho", "Inglaterra", PlayerPosition.FORWARD);
        registerPlayer(team4, 15, "Alejandro Garnacho", "Argentina", PlayerPosition.FORWARD);
        registerPlayer(team4, 16, "Scott McTominay", "Escocia", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 17, "Aaron Wan-Bissaka", "Inglaterra", PlayerPosition.DEFENDER);
        registerPlayer(team4, 18, "Luke Shaw", "Inglaterra", PlayerPosition.DEFENDER);
        registerPlayer(team4, 19, "Sofyan Amrabat", "Marruecos", PlayerPosition.MIDFIELDER);
        registerPlayer(team4, 20, "Tom Heaton", "Inglaterra", PlayerPosition.GOALKEPEER);

        Team team5 = createTeam("Inter de Milan", "Italia", "Simone Inzaghi");
        registerTeam(team5);
        registerPlayer(team5, 1, "Yann Sommer", "Suiza", PlayerPosition.GOALKEPEER);
        registerPlayer(team5, 2, "Matteo Darmian", "Italia", PlayerPosition.DEFENDER);
        registerPlayer(team5, 3, "Francesco Acerbi", "Italia", PlayerPosition.DEFENDER);
        registerPlayer(team5, 4, "Stefan de Vrij", "Paises Bajos", PlayerPosition.DEFENDER);
        registerPlayer(team5, 5, "Benjamin Pavard", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team5, 6, "Alessandro Bastoni", "Italia", PlayerPosition.DEFENDER);
        registerPlayer(team5, 7, "Federico Dimarco", "Italia", PlayerPosition.DEFENDER);
        registerPlayer(team5, 8, "Hakan Calhanoğlu", "Turquia", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 9, "Henrikh Mkhitaryan", "Armenia", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 10, "Davide Frattesi", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 11, "Nicolo Barella", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 12, "Marcus Thuram", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team5, 13, "Lautaro Martínez", "Argentina", PlayerPosition.FORWARD);
        registerPlayer(team5, 14, "Carlos Augusto", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team5, 15, "Kristjan Asllani", "Albania", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 16, "Juan Cuadrado", "Colombia", PlayerPosition.MIDFIELDER);
        registerPlayer(team5, 17, "Denzel Dumfries", "Paises Bajos", PlayerPosition.DEFENDER);
        registerPlayer(team5, 18, "Alexis Sanchez", "Chile", PlayerPosition.FORWARD);
        registerPlayer(team5, 19, "Francesco Di Gennaro", "Italia", PlayerPosition.GOALKEPEER);
        registerPlayer(team5, 20, "Emil Audero", "Italia", PlayerPosition.GOALKEPEER);

        Team team6 = createTeam("Juventus", "Italia", "Thiago Motta");
        registerTeam(team6);
        registerPlayer(team6, 1, "Wojciech Szczesny", "Polonia", PlayerPosition.GOALKEPEER);
        registerPlayer(team6, 2, "Danilo", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team6, 3, "Gleison Bremer", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team6, 4, "Federico Gatti", "Italia", PlayerPosition.DEFENDER);
        registerPlayer(team6, 5, "Alex Sandro", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team6, 6, "Manuel Locatelli", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 7, "Adrien Rabiot", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 8, "Filip Kostic", "Serbia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 9, "Weston McKennie", "Estados Unidos", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 10, "Nicolo Fagioli", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 11, "Fabio Miretti", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 12, "Federico Chiesa", "Italia", PlayerPosition.FORWARD);
        registerPlayer(team6, 13, "Dusan Vlahovic", "Serbia", PlayerPosition.FORWARD);
        registerPlayer(team6, 14, "Arkadiusz Milik", "Polonia", PlayerPosition.FORWARD);
        registerPlayer(team6, 15, "Moise Kean", "Italia", PlayerPosition.FORWARD);
        registerPlayer(team6, 16, "Mattia Perin", "Italia", PlayerPosition.GOALKEPEER);
        registerPlayer(team6, 17, "Carlo Pinsoglio", "Italia", PlayerPosition.GOALKEPEER);
        registerPlayer(team6, 18, "Samuel Iling-Junior", "Inglaterra", PlayerPosition.FORWARD);
        registerPlayer(team6, 19, "Paul Pogba", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team6, 20, "Leonardo Bonucci", "Italia", PlayerPosition.DEFENDER);

        Team team7 = createTeam("Paris saint germain", "Francia", "Luis Enrique");
        registerTeam(team7);
        registerPlayer(team7, 1, "Gianluigi Donnarumma", "Italia", PlayerPosition.GOALKEPEER);
        registerPlayer(team7, 2, "Achraf Hakimi", "Marruecos", PlayerPosition.DEFENDER);
        registerPlayer(team7, 3, "Marquinhos", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team7, 4, "Milan Skriniar", "Eslovaquia", PlayerPosition.DEFENDER);
        registerPlayer(team7, 5, "Lucas Hernandez", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team7, 6, "Manuel Ugarte", "Uruguay", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 7, "Vitinha", "Portugal", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 8, "Warren Zaire-Emery", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 9, "Ousmane Dembele", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team7, 10, "Kylian Mbappe", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team7, 11, "Randal Kolo Muani", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team7, 12, "Marco Asensio", "Espana", PlayerPosition.FORWARD);
        registerPlayer(team7, 13, "Lee Kang-in", "Corea del Sur", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 14, "Fabian Ruiz", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 15, "Danilo Pereira", "Portugal", PlayerPosition.DEFENDER);
        registerPlayer(team7, 16, "Keylor Navas", "Costa Rica", PlayerPosition.GOALKEPEER);
        registerPlayer(team7, 17, "Layvin Kurzawa", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team7, 18, "Cher Ndour", "Italia", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 19, "Carlos Soler", "Espana", PlayerPosition.MIDFIELDER);
        registerPlayer(team7, 20, "Bradley Barcola", "Francia", PlayerPosition.FORWARD);

        Team team8 = createTeam("AS Monaco", "Francia", "Adolf Hütter");
        registerTeam(team8);
        registerPlayer(team8, 1, "Philipp Kohn", "Suiza", PlayerPosition.GOALKEPEER);
        registerPlayer(team8, 2, "Caio Henrique", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team8, 3, "Guillermo Maripán", "Chile", PlayerPosition.DEFENDER);
        registerPlayer(team8, 4, "Chrislain Matsima", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team8, 5, "Vanderson", "Brasil", PlayerPosition.DEFENDER);
        registerPlayer(team8, 6, "Mohammed Salisu", "Ghana", PlayerPosition.DEFENDER);
        registerPlayer(team8, 7, "Soungoutou Magassa", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team8, 8, "Denis Zakaria", "Suiza", PlayerPosition.MIDFIELDER);
        registerPlayer(team8, 9, "Youssouf Fofana", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team8, 10, "Aleksandr Golovin", "Rusia", PlayerPosition.MIDFIELDER);
        registerPlayer(team8, 11, "Takumi Minamino", "Japon", PlayerPosition.FORWARD);
        registerPlayer(team8, 12, "Wissam Ben Yedder", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team8, 13, "Myron Boadu", "Paises Bajos", PlayerPosition.FORWARD);
        registerPlayer(team8, 14, "Maghnes Akliouche", "Francia", PlayerPosition.MIDFIELDER);
        registerPlayer(team8, 15, "Ismail Jakobs", "Senegal", PlayerPosition.DEFENDER);
        registerPlayer(team8, 16, "Breel Embolo", "Suiza", PlayerPosition.FORWARD);
        registerPlayer(team8, 17, "Eliesse Ben Seghir", "Francia", PlayerPosition.FORWARD);
        registerPlayer(team8, 18, "Ruben Aguilar", "Francia", PlayerPosition.DEFENDER);
        registerPlayer(team8, 19, "Folarin Balogun", "Estados Unidos", PlayerPosition.FORWARD);
        registerPlayer(team8, 20, "Kevin Volland", "Alemania", PlayerPosition.FORWARD);

        registerReferee(createReferee(54309, "Gil Manzano", "Egipto", RefereeType.CENTRAL));
        
        Referee referee2 = createReferee(98427, "Juan Joe", "Canada", RefereeType.ASSISTANT);
        registerReferee(referee2);
        Referee referee3 = createReferee(82615, "Tomás Jaramillo", "Colombia", RefereeType.ASSISTANT);
        registerReferee(referee3);
        Referee referee4 = createReferee(93617, "Victor Manuel", "Espana", RefereeType.ASSISTANT);
        registerReferee(referee4);
        Referee referee5 = createReferee(61928, "Andres Zeng", "China", RefereeType.ASSISTANT);
        registerReferee(referee5);
        Referee referee6 = createReferee(28162, "Edwar Estacio", "Brasil", RefereeType.ASSISTANT);
        registerReferee(referee6);
        Referee referee7 = createReferee(98251, "Jacobo Rodriguez", "Alemania", RefereeType.ASSISTANT);
        registerReferee(referee7);
        Referee referee8 = createReferee(62819, "Felipe Cabrera", "Nicaragua", RefereeType.ASSISTANT);
        registerReferee(referee8);
        Referee referee9 = createReferee(728392, "Felipe Gonzalez", "Mexico", RefereeType.ASSISTANT);
        registerReferee(referee9);
        Referee referee10 = createReferee(15263, "Luis Miranda", "Argentina", RefereeType.CENTRAL);
        registerReferee(referee10);
        Referee referee11 = createReferee(72832, "Sebastian Acosta", "Chile", RefereeType.CENTRAL);
        registerReferee(referee11);
        Referee referee12 = createReferee(54823, "Sebastian Hidalgo", "Paraguay", RefereeType.CENTRAL);
        registerReferee(referee12);


        return "Data preloaded successfully.";
    }

    public boolean verifyArrayTeams() {

        int countNull = 0;
        for(int i = 0; i < teams.length; i++) {
            if(teams[i] == null) {
                countNull++;
            }
        }
        if(countNull > 0) {
            return false;
        }
        return true;
    }

    public String generateGroupStage() {
        if(verifyArrayTeams()) {
            groupStage.setTeams(teams);
            return groupStage.createMatches(); 
        }
        return "Please register all 8 teams in order to start the tournament";
    }

    public String assignRefereesToGroup(String group) {
        String message = "Assignment of referees for the group " + group + ": ";
    
        // Loop over all matches in the group
        for (int matchIndex = 0; matchIndex < 6; matchIndex++) {
            // Shuffle the referees before each match assignment
            shuffleReferees(referees);
    
            // Filter and assign referees (1 central and 2 assistants) for the match
            String[] assignedReferees = assignReferees(referees, group);
    
            // Check if referees are assigned and build the message for each match
            if (assignedReferees != null) {
                message += "\nMatch " + (matchIndex + 1) + " referees: ";
                for (String referee : assignedReferees) {
                    message += "\n" + referee;
                }
            } else {
                message += "\nNo referees available for match " + (matchIndex + 1);
            }
        }
    
        return message;
    }
    /*
    public String registerGoalAndAssist(String scorerName, String assistName) { //int matchID
        return groupStage.registerGoalAndAssist(scorerName, assistName); //matchID
    }*/

    public String registerGoal(String scoringTeamName, String scorerName, String assistName) {
        Team team = searchTeam(scoringTeamName);

        if (team == null) {
            return "The team " + scoringTeamName + " was not found.";
        }

        Player scorer = team.getPlayerByName(scorerName);
        Player assist = team.getPlayerByName(assistName);

        if (scorer == null) {
            return "The player " + scorerName + " was not found in the team " + scoringTeamName + ".";
        }

        if (assist == null) {
            return "The player " + assistName + " was not found in the team " + scoringTeamName + ".";
        }

        scorer.incrementGoals();
        assist.incrementAssists();
        return "Goal registered: " + scorerName + " scored with an assist from " + assistName + ".";


    }

    public String[] assignReferees(Referee[] referees, String group) {
        // Separate central and assistant referees
        List<Referee> centralReferees = new ArrayList<>();
        List<Referee> assistantReferees = new ArrayList<>();

        // Split referees by type
        for (Referee referee : referees) {
            if (referee != null) {
                if (referee.getRefType() == RefereeType.CENTRAL) {
                    centralReferees.add(referee);
                } else if (referee.getRefType() == RefereeType.ASSISTANT) {
                    assistantReferees.add(referee);
                }
            }
        }

        // Ensure we have at least one central and two assistants
        if (centralReferees.size() > 0 && assistantReferees.size() > 1) {
            // Randomly shuffle both lists
            Collections.shuffle(centralReferees);
            Collections.shuffle(assistantReferees);

            // Select one central referee and two assistant referees
            Referee centralReferee = centralReferees.get(0);
            Referee assistantReferee1 = assistantReferees.get(0);
            Referee assistantReferee2 = assistantReferees.get(1);

            // Return the assigned referees as a formatted string array
            return new String[]{
                centralReferee.getName() + " (" + centralReferee.getCountry() + ") - Central",
                assistantReferee1.getName() + " (" + assistantReferee1.getCountry() + ") - Assistant",
                assistantReferee2.getName() + " (" + assistantReferee2.getCountry() + ") - Assistant"
            };
        }

        return null; // If we don't have enough referees, return null
    }

    
    

    // Shuffle referees method using Random
    private void shuffleReferees(Referee[] referees) {
        Random rand = new Random();
        for (int i = referees.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Referee temp = referees[i];
            referees[i] = referees[j];
            referees[j] = temp;
        }
    }
    
    public String registerMatchScores() {
        return groupStage.registerMatchScores();  // Delegate to GroupStage to handle match score registration
    } 


    public String showMatches(){
        return groupStage.showMatchesWithScores();
    }

    public boolean verifyMatch(String homeTeamName, String awayTeamName) {
        return groupStage.verifyMatch(homeTeamName, awayTeamName);
    }

    public boolean verifyPlayerInATeam(String homeTeamName, String awayTeamName,String playerName, int tshirtNumberScorer) {
        if( (searchTeam(homeTeamName) != null && searchTeam(awayTeamName) != null) && (searchTeam(homeTeamName).getPlayerByNameAndTshirt(playerName, tshirtNumberScorer) != null || searchTeam(awayTeamName).getPlayerByNameAndTshirt(playerName, tshirtNumberScorer) != null)) {
            return true;
        }
        return false;
    }

    public Player searchPlayer(String name, int tshirt){
        for(Team t:teams){
            if(t!=null && t.getPlayerByNameAndTshirt(name,tshirt)!=null){

                return t.getPlayerByNameAndTshirt(name,tshirt);

            }
        }

        return null;
    }


    public boolean addScorerAndAssister(Team homeTeam, Team awayTeam, Player scorer, Player assister){

        return groupStage.addScorerAndAssister(homeTeam,awayTeam,scorer,assister);

    }

    public boolean addCardToMatch(String homeTeamName, String awayTeamName, String playerName, int tshirtNumber, String cardType) {
        Team homeTeam = searchTeam(homeTeamName);
        Team awayTeam = searchTeam(awayTeamName);

        if(homeTeam == null || awayTeam == null) {
            return false;
        }

        Player player = searchPlayer(playerName, tshirtNumber);

        if(player == null) {
            return false;
        }
        Team playerTeam = getPlayerTeam(homeTeamName, awayTeamName, playerName, tshirtNumber);

        if(cardType.equalsIgnoreCase("Yellow")){
            player.addYellowCard();
            playerTeam.addYellowCard();
            return true;
        }
        if(cardType.equalsIgnoreCase("red")){

            player.addRedCard();
            playerTeam.addRedCard();
            return true;
        }
        return false;
    }

    public Team getPlayerTeam(String homeTeamName, String awayTeamName,String playerName, int tshirtNumberScorer) {
        if((searchTeam(homeTeamName) != null && searchTeam(awayTeamName) != null)) {
            if((searchTeam(homeTeamName).getPlayerByNameAndTshirt(playerName, tshirtNumberScorer) != null)){
                return searchTeam(homeTeamName);
            } else {
                return searchTeam(awayTeamName);
            }
        }
        return null;
    }


    public String printTablePosition(){
        return groupStage.generateStandings();
    }

    public String tournamentsTopScorer() {

        ArrayList<Player> topScorers = new ArrayList<>();
        ArrayList<Team> playersTeam = new ArrayList<>();

        for(int i = 0; i < teams.length; i++) {

            if(teams[i] != null) {

                if(teams[i].getTopScorer().getNumberOfGoals() > 0) {
                    topScorers.add(teams[i].getTopScorer());
                    playersTeam.add(teams[i]);
                }
            }
        }

        if(topScorers.isEmpty()) {
            return "No players are registered yet, maybe try again later.";
        }
        return sortTopScorer(topScorers, playersTeam);
    }

    public String sortTopScorer(ArrayList<Player> topScorers, ArrayList<Team> playersTeam) {
        Player tempPlayer = null;
        Team tempTeam = null;

        for(int i = 0; i < topScorers.size(); i++) {

            for(int j=(i + 1); j < topScorers.size(); j++) {
                if(topScorers.get(i).getNumberOfGoals() < topScorers.get(j).getNumberOfGoals()) {
                    tempPlayer = topScorers.get(j);
                    topScorers.set(j, topScorers.get(i));
                    topScorers.set(i, tempPlayer);

                    tempTeam = playersTeam.get(j);
                    playersTeam.set(j, playersTeam.get(i));
                    playersTeam.set(i, tempTeam);
                }
            }
        }
        return topScorersMessage(topScorers, playersTeam);
    }

    public String topScorersMessage(ArrayList<Player> topScorers, ArrayList<Team> playersTeam) {
        String message = "\n Tournament's top scorers:  \n";

        message += "Pos     | Player    | Goals     |Team   \n";

        for(int i = 0; i < topScorers.size(); i++) {
            message += (i + 1) + "          " + topScorers.get(i).getName() + "          " + topScorers.get(i).getNumberOfGoals() + "          " + playersTeam.get(i).getName() + " \n";
        }
        return message;
    }

    
    public String showTeamWithFairPlay(){

        Team teamWithMostFairPlay = teams[0];

        for(int i = 0; i < teams.length; i++){
            if(teams[i] != null && teamWithMostFairPlay.getNumberOfCards() <teams[i].getNumberOfCards()){
                teamWithMostFairPlay = teams[i];
            }
        }

        return "The team with the most Fair Play is: " + teamWithMostFairPlay.getName() + " with " +teamWithMostFairPlay.getYellowCards() + " yellow cards and " + teamWithMostFairPlay.getRedCards() + " red cards.\n";
    }



    private boolean preloadCards = false;

    public void cardsToTeamsByDefault(){

        if(!preloadCards){

            for(Team t:teams){
                if(t!=null){
                    t.addCardsToPlayers();
                }
            }

        }

        preloadCards = true;

    }

    public String showEfficiencyOfATeam(Team team) {
        return groupStage.getTeamEfficiency(team);
    }


    public String displayTeamsOfTheTournament(){
        String message = "Here you have the list of the teams of the tournament: \n\n";

        for(Team t:teams){
            if(t!=null){
                message += t.getName() + "\n";
            }
        }

        return message;
    }

}
