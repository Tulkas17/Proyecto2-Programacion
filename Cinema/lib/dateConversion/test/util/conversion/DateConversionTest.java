package util.conversion;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class DateConversionTest {

    @Test
    public void simpleTest() {
        java.util.Date now = Calendar.getInstance().getTime();
        System.out.println(now);
        System.out.println();

        java.sql.Timestamp ts = DateConversion.util2Timestamp(now);
        System.out.println(ts);
        java.util.Date then = DateConversion.timestamp2Util(ts);
        System.out.println(then);
        assertEquals(now, then);
        System.out.println();

        java.sql.Date sd = DateConversion.util2sql(now);
        System.out.println(sd);
        java.util.Date again = DateConversion.sql2Util(sd);
        System.out.println(again);
        assertEquals(now, again);
        System.out.println();

        assertTrue(true);
    }

}
