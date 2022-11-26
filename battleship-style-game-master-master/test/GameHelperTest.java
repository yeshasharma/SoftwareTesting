import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class GameHelperTest
{
    @Test
    public void getUserInput_shouldReturnNull_forNoInput() throws IOException {
        BufferedReader testReader = new BufferedReader(new StringReader(""));
        GameHelper helper = new GameHelper();
        assertNull(helper.getUserInput("enter a guess: ", testReader));
    }

    @ParameterizedTest
    @MethodSource ("caseSensitiveInputDataset")
    public void getUserInput_shouldReturnLowerCase(String inputStreams) throws IOException {
        BufferedReader testReader = new BufferedReader(new StringReader(inputStreams));
        GameHelper helper = new GameHelper();
        assertEquals(inputStreams.toLowerCase(),helper.getUserInput("enter a guess: ", testReader));
    }

    private static Stream<Arguments> caseSensitiveInputDataset () {
        return Stream.of
                (
                        Arguments.of("A1"),
                        Arguments.of("B2"),
                        Arguments.arguments("a1")
                );
    }



    @ParameterizedTest
    @MethodSource ("tooLong_IOexceptionsDataset")
    public void getUserInput_shouldThrowIOException_InputTooLong(String inputStreams) throws IOException {
        BufferedReader testReader = new BufferedReader(new StringReader(inputStreams));
        GameHelper helper = new GameHelper();

        Throwable exception = assertThrows(IOException.class, () -> helper.getUserInput("enter a guess: ", testReader));
        assertEquals("Input is too long.", exception.getMessage());

    }

    private static Stream<Arguments> tooLong_IOexceptionsDataset () {
        return Stream.of
                (
                        Arguments.of("B2ddddd"),
                        Arguments.arguments("a 1")
                );
    }

}
