package ui;

import java.util.Scanner;
import model.Controller;

public class Executable {

    // Attributes of the Executable class
    private Scanner reader;
    private Controller cont;
    private static boolean flag;

    // Constructor
    private Executable() {
        reader = new Scanner(System.in);
        cont = new Controller();
    }

    // Main method to run the menu
    public void run(boolean flag) {
        flag = false;

        while (!flag) {
            System.out.println("\n\nWelcome to the soccer tournament menu:");
            System.out.println("Options:\n" 
                                + "1. Register team \n" 
                                + "2. Register player \n"
                                + "3. Register referee \n" 
                                + "4. Preload data \n"
                                + "5. Show group stage draw \n"
                                + "6. Assign referees to match \n" 
                                + "7. Register match scores \n" 
                                + "8. Register goal and assits \n"
                                + "9. Register cards \n"
                                + "10. Show group stage final positions \n"
                                + "11. Show tournament's top scorer \n" 
                                + "12. Show team with fair play \n"
                                + "13. Show the efficiency of a team \n"
                                + "14. Show the efficiency of a player \n"
                                + "15. Show the referee card index \n"  
                                + "16. Exit \n");

            int option = reader.nextInt();
            reader.nextLine(); // Clear buffer

            switch (option) {
                case 1:
                    registerTeam();
                    break;
                case 2:
                    registerPlayer();
                    break;
                case 3:
                    registerReferee();
                    break;
                case 4:
                    preloadData();
                    break;
                case 5:
                    showGroupStageDraw();
                    break;
                case 6:
                    assignRefereesToGroup();
                    break;
                case 7:
                    System.out.println(cont.registerMatchScores());
                    break;
                case 8:
                    registerGoalAndAssist();
                    break;
                case 9: 
                    registerCardsToPlayer(); 
                    break;
                case 10:
                    showGroupStagePositions();
                    break;
                case 11:
                    showTournamentsTopScorer();
                    break;
                case 12:
                    showTeamWithMostFairPlay();
                    break;
                case 13:
                    showTeamEfficiency();
                    break;
                case 14:
                    showPlayerEfficiency();
                    break;
                case 15:
                    showRefereeCardIndex();
                    break;
                case 16:
                    flag = true;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid option.");
            }
        }
    }

    // Method to register a team
    public void registerTeam() {
        System.out.print("Enter team name: ");
        String name = reader.nextLine();
        System.out.print("Enter team's country: ");
        String country = reader.nextLine();
        System.out.print("Enter team coach's name: ");
        String coach = reader.nextLine();

        // Controller call and output message
        System.out.println(cont.registerTeam(cont.createTeam(name, country, coach)));
    }

    // Method to register a player
    public void registerPlayer() {

        System.out.println("Enter the player's team: ");
        String playerTeam = reader.nextLine();

        if(cont.searchTeam(playerTeam)!=null){

        
            System.out.print("Enter player name: ");
            String name = reader.nextLine();
            System.out.print("Enter player's country: ");
            String country = reader.nextLine();
            System.out.print("Enter player's number: ");
            int number = reader.nextInt();
            reader.nextLine(); // Consume newline
            System.out.print("Enter player's position (Goalkeeper, Defender, Midfielder, Forward): ");
            String position = reader.nextLine().toUpperCase();
        

            // Player position validation
            while (cont.selectPlayerPosition(position) == null) {
                System.out.println("Invalid type. Please try again.");
                position = reader.nextLine().toUpperCase();
            }

            // Controller call and output message
            System.out.println(cont.registerPlayer(cont.searchTeam(playerTeam), number, name, country, cont.selectPlayerPosition(position)));
        } else {
            System.out.println("Team not found, please check again");
        }

    }

    // Method to register a referee
    public void registerReferee() {
        System.out.print("Enter referee ID: ");
        int id = reader.nextInt();
        reader.nextLine(); // Consume newline
        System.out.print("Enter referee name: ");
        String name = reader.nextLine();
        System.out.print("Enter referee's country: ");
        String country = reader.nextLine();
        System.out.print("Enter referee type (Central, Assistant): ");
        String refType = reader.nextLine().toUpperCase();

        // Referee type validation
        while (cont.selectRefereeType(refType) == null) {
            System.out.println("Invalid type. Please try again.");
            refType = reader.nextLine().toUpperCase();
        }

        // Controller call and output message
        System.out.println(cont.registerReferee(cont.createReferee(id, name, country, cont.selectRefereeType(refType))));
    }

    // Method to preload data
    public void preloadData() {
        System.out.println(cont.preloadData());
    }

    //Method to show the group stage fixture
    public void showGroupStageDraw() {
        System.out.println(cont.generateGroupStage());
    }

    // Main method
    public static void main(String[] args) {
        Executable mainApp = new Executable();
        mainApp.run(flag);
    }

    

    // Método para asignar árbitros a un grupo
    public void assignRefereesToGroup() {
        System.out.print("Enter group (A or B): ");
        String group = reader.nextLine().toUpperCase();

        while (!group.equals("A") && !group.equals("B")) {
            System.out.println("Invalid group. Please try again.");
            group = reader.nextLine().toUpperCase();
        }

        System.out.println(cont.assignRefereesToGroup(group));
    }





    private void registerGoalAndAssist() {


        System.out.println("Group stage final scores: ");
        System.out.println(cont.showMatches());

        String homeTeamName = "";
        String awayTeamName = "";

        boolean flag = true; 

        while(flag){
            System.out.print("Enter the name of the home team: ");
            homeTeamName = reader.nextLine();

            while(cont.searchTeam(homeTeamName)==null){
                System.out.println("That team does not exist in this tournament, please enter a valid option.");
                homeTeamName = reader.nextLine();
            }

            System.out.print("Enter the name of the away team: ");
            awayTeamName = reader.nextLine();

            while(cont.searchTeam(awayTeamName)==null){
                System.out.println("That team does not exist in this tournament, please enter a valid option.");
                awayTeamName = reader.nextLine();
            }


            if(cont.verifyMatch(homeTeamName, awayTeamName)) {
                flag = false;
            } else {
                System.out.println("That match does not exist in this tournament yet. Please enter a valid option.");
            }
        }

        
       
       
        
         
        
        System.out.print("Enter scoring player name: ");
        String scorerName = reader.nextLine();

        System.out.println("Enter t-shirt number of the scoring player: ");
        int tshirtNumberScorer = reader.nextInt();
        reader.nextLine();

        while(!cont.verifyPlayerInATeam(homeTeamName,awayTeamName,scorerName,tshirtNumberScorer)){
            System.out.println("The player does not exist in any of the teams. Enter a valid options.");
            System.out.print("Enter scoring player name: ");
            scorerName = reader.nextLine();
            System.out.println("Enter t-shirt number of the scoring player: ");
            tshirtNumberScorer = reader.nextInt();
            reader.nextLine();
        }

    
        
        System.out.println("Enter assisting player name: ");
        String assisterName = reader.nextLine();
        

        System.out.println("Enter t-shirt number of the scoring player: ");
        int tshirtNumberAssister = reader.nextInt();
        reader.nextLine();

        while(!cont.verifyPlayerInATeam(homeTeamName,awayTeamName,assisterName,tshirtNumberAssister)){
            System.out.println("The player does not exist in any of the teams. Enter a valid options.");
            System.out.println("Enter assisting player name: ");
            assisterName = reader.nextLine();
            System.out.println("Enter t-shirt number of the scoring player: ");
            tshirtNumberAssister = reader.nextInt();
        }

        if(!cont.addScorerAndAssister(cont.searchTeam(homeTeamName), cont.searchTeam(awayTeamName), cont.searchPlayer(scorerName,tshirtNumberScorer), cont.searchPlayer(assisterName,tshirtNumberAssister))){
            System.out.println("Could not register goal.");
        } else {
            System.out.println("Goal was successfully added");
        }




        /*String result = cont.registerGoalAndAssist(scorerName, assistName.equals("NONE") ? null : assistName); //matchID 
        System.out.println(result);*/
    }

    private void registerCardsToPlayer() { 

        System.out.println("Group stage matches: ");
        System.out.println(cont.showMatches());

        String homeTeamName = "";
        String awayTeamName = "";

        boolean flag = true;

        while(flag) {
            System.out.println("Enter the name of the home team: ");
            homeTeamName = reader.nextLine();

            while(cont.searchTeam(homeTeamName) == null) {
                System.out.println("That team does not exist in this tournament, please enter a valid option.");
                homeTeamName = reader.nextLine();
            }

            System.out.println("Enter the name of the away team: ");
            awayTeamName = reader.nextLine();

            while(cont.searchTeam(awayTeamName) == null) {
                System.out.println("That team does not exist in this tournament, please enter a valid option.");
                awayTeamName = reader.nextLine();
            }

            if(cont.verifyMatch(homeTeamName, awayTeamName)) {
                flag = false;
            } else {
                System.out.println("That match does not exist in this tournament yet. Please enter a valid option.");
            }
        }

        System.out.println("Enter player name: ");
        String playerName = reader.nextLine();

        System.out.println("Enter the t-shirt number of the player: ");
        int tshirtNumber = reader.nextInt();
        reader.nextLine();

        while(!cont.verifyPlayerInATeam(homeTeamName, awayTeamName, playerName, tshirtNumber)) {
            System.out.println("The player does not exist in any of the teams. Enter a valid option.");
            System.out.println("Enter player name: ");
            playerName = reader.nextLine();
            System.out.println("Enter the t-shirt number of the player: ");
            tshirtNumber = reader.nextInt();
            reader.nextLine();
        }

        System.out.println("Enter the type of card (yellow/red): ");
        String cardType = reader.nextLine().toLowerCase();

        while (!cardType.equals("yellow") && !cardType.equals("red")) {
            System.out.println("Invalid card type. Please enter 'yellow' or 'red'.");
            System.out.println("Enter the type of card (yellow/red): ");
            cardType = reader.nextLine().toLowerCase();
        }

        boolean result = cont.addCardToMatch(homeTeamName, awayTeamName, playerName, tshirtNumber, cardType);

        if(result) {
            System.out.println("Card successfully registered.");
        } else {
            System.out.println("Could not register the card.");
        }
    }

    public void showGroupStagePositions() {
        System.out.println(cont.printTablePosition());
    }

    public void showTournamentsTopScorer() {
        System.out.println(cont.tournamentsTopScorer());
    }

    public void showTeamWithMostFairPlay() {
        
        cont.cardsToTeamsByDefault();
        System.out.println(cont.showTeamWithFairPlay());
    }

    public void showTeamEfficiency() {
        System.out.println(cont.displayTeamsOfTheTournament());
        System.out.println("Please enter the name of the team: ");
        String teamName = reader.nextLine();

        if(cont.searchTeam(teamName) == null) {
            System.out.println("Team not found.");
        } else {
            System.out.println(cont.showEfficiencyOfATeam(cont.searchTeam(teamName)));
        }  
    }

    public void showPlayerEfficiency() {
        System.out.println(cont.displayTeamsOfTheTournament());
        System.out.println("Please enter the name of the team: ");
        String teamName = reader.nextLine();

        if(cont.searchTeam(teamName) == null) {
            System.out.println("Team not found.");
        } else {

            System.out.println(cont.displayPlayersOfATeam(teamName));

            System.out.println("Enter the name of the player: ");
            String playerName = reader.nextLine();

            while(cont.searchPlayer(cont.searchTeam(teamName), playerName)==null){
                System.out.println("Player not found. Please enter a valid option.");
                playerName = reader.nextLine();
            }

            System.out.println(cont.showEfficiencyOfAPlayer(cont.searchTeam(teamName), cont.searchPlayer(cont.searchTeam(teamName), playerName)));

        } 
    }

    public void showRefereeCardIndex() {

        System.out.println(cont.getCentralReferees());

        System.out.println("Please enter the referee ID to continiue: ");
        int id = reader.nextInt();

        while(cont.searchReferee(id) == null) {
            System.out.println("ID was not valid, please try again.");
            id = reader.nextInt();
        }

        System.out.println(cont.getCardIndex(cont.searchReferee(id)));


    }
    

}
