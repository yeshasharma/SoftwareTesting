import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class DotComBustTest {
    @Test
    void testFinishGame() {
        DotComBust test = new DotComBust();
        ArrayList<Integer> numOfGuess = new ArrayList<>();

        numOfGuess.add(400);

        test.finishGame();

    }

    @Test
    void test_Check_UserGuess() {
        DotComBust test = new DotComBust();
        ArrayList<String> checkGuess = new ArrayList<String>();

        checkGuess.add("hit");

        test.checkUserGuess("hit");


    }

}