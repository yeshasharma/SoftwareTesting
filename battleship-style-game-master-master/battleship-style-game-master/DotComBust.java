import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * DotComBust
 */
public class DotComBust {

    private final GameHelper helper = new GameHelper();
    private final ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses;



    //TODO add dependency injection that allows us to pass a gamestate object into setUpGame Constructor
    public void setUpGame()
    {

        //todo upper/lower limit for number of sites
        //todo user input for ships

        DotCom one = new DotCom();
        one.setName("Pets.com");
        DotCom two = new DotCom();
        two.setName("eToys.com");
        DotCom three = new DotCom();
        three.setName("Go2.com");

        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);
        System.out.println("Your goal is to sink three dot coms.");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to sink them all in the fewest number of guesses");

        //TODO battleship game only works for ship size of 3, add user input to allow user to specify amount and size of ships.
        for (DotCom dotCom : dotComList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);

            dotCom.setLocationCells(newLocation);
        }
    }

    public void startPlaying() {
        ArrayList<String> locationCells = new ArrayList<String>();

        //Contains all the locations of ships
        for (DotCom dotCom : dotComList)
        {
            locationCells.addAll(dotCom.locationCells);
        }

        ArrayList<String> wrongUserGuesses = new ArrayList<String>();
        ArrayList<String> correctUserGuesses = new ArrayList<String>();


        while (!dotComList.isEmpty())
        {
            helper.gridDisplay(correctUserGuesses, wrongUserGuesses);

            try
            {
                String userGuess = helper.getUserInput("enter a guess", helper.getReader());

                if (locationCells.contains(userGuess)) {
                    correctUserGuesses.add(userGuess);
                }
                else {
                    wrongUserGuesses.add(userGuess);
                }
                checkUserGuess(userGuess);
            }
            catch (IOException e) {
                System.out.println(e);
            }

        }
        finishGame();
    }

    public void checkUserGuess(String userGuess) {
        numOfGuesses += 1;
        String result = "miss";
        for (DotCom dotCom : dotComList) {
            result = dotCom.checkYourself(userGuess);
            if (result.equals("hit")) {
                System.out.println("test hit" + result + dotCom + dotComList);
                break;
            }
            if (result.equals("kill")) {
                result += " " + dotCom.getName();
                System.out.println("test miss" + result+ dotCom + dotComList);
                dotComList.remove(dotCom);
                break;
            }

        }

        System.out.println("Result: " + result);
    }

    public void finishGame() {
        System.out.println("All Dot Coms are dead! Your stock is now worthless.");
        if (numOfGuesses <= 18) {
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank.");
        } else {
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options.");
        }
    }


    public static void main(String[] args) throws IOException {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}