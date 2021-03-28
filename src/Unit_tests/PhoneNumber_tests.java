package Unit_tests;

import Utils.DataValidity;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhoneNumber_tests {
        @Test
        public void numberWithLessThan15symbolShouldBeCorrect()
        {
            String phoneNumber ="1257896325412";
            assertTrue(DataValidity.isValidNumber(phoneNumber));
        }
        @Test
        public void numberWithMorethan15symbolShouldBeIncorrect()
        {
            String phoneNumber = "12345678910111213";
            assertFalse(DataValidity.isValidNumber(phoneNumber));
        }
        @Test
        public void numberWithLessThan5SymbolShouldBeIncorrect()
        {
            String phoneNumber ="1";
            assertFalse(DataValidity.isValidNumber(phoneNumber));
        }
        @Test public void numberWithPlusSymbolShouldBeCorrect()
        {
            String phoneNumber="+123456789";
            assertTrue(DataValidity.isValidNumberStartWithPlus(phoneNumber));
        }
        @Test public void numberWithoutPlusSymbolShouldBeIncorrect()
        {
            String phoneNumber="123456789";
            assertFalse(DataValidity.isValidNumberStartWithPlus(phoneNumber));
        }
}
