package su.yesha.pitest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    @Test
    public void sumTest(){
        Calculator cs = new Calculator();
        assertEquals(2D, cs.sum(1D,1D));
    }
}

