import Utils.DataValidity;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Alphabetical_string_tests {

    @Test
    public void stringWithNumbersShouldBeIncorrect()
    {
        String word = "sdfdsf456";
        assertFalse(DataValidity.isOnlyAlphabeticalChars(word));
    }

    @Test
    public void stringWithoutNumbersShouldBeCorrect()
    {
        String word = "asdjklf";
        assertTrue(DataValidity.isOnlyAlphabeticalChars(word));
    }

    @Test
    public void emptyStringShouldBeIncorrect()
    {
        String word = "";
        assertFalse(DataValidity.isOnlyAlphabeticalChars(word));
    }
}
