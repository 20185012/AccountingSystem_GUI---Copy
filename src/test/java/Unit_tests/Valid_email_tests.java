package Unit_tests;

import Utils.DataValidity;
import org.junit.Test;

import static org.junit.Assert.*;

public class Valid_email_tests {
    @Test
    public void emailWithEtaAndGoodDomainShouldBeCorrect()
    {
        String email = "tutyzas@gmail.com";
        assertTrue(DataValidity.isValidEmailAddress(email));
    }

    @Test
    public void emailWithBadDomainShouldBeIncorrect()
    {
        String email = "tutyzas@gmailm";
        assertFalse(DataValidity.isValidEmailAddress(email));
    }

    @Test
    public void emptyEmailShouldNotBeAllowed()
    {
        String email = "";
        assertFalse(DataValidity.isValidEmailAddress(email));
    }
}
