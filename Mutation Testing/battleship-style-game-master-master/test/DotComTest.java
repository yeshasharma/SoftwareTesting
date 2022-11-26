import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DotComTest
{
    @Test
    void test_CheckYourself_miss_pass() {
        DotCom test = new DotCom();

        ArrayList<String> hitConditions = new ArrayList<String>();

        hitConditions.add("hit");

        test.setLocationCells(hitConditions);

        String result = test.checkYourself("DNE");

        assertEquals("hit", result);
    }

    @Test
    void test_CheckYourself_kill_pass() {
        DotCom test = new DotCom();

        ArrayList<String> hitConditions = new ArrayList<String>();

        hitConditions.add("hit");

        test.setLocationCells(hitConditions);

        String result = test.checkYourself("hit");

        assertEquals("kill", result);
    }

    @Test
    void test_CheckYourself_hit_pass() {
        DotCom test = new DotCom();

        ArrayList<String> hitConditions = new ArrayList<String>();

        hitConditions.add("hit");
        hitConditions.add("hit1");

        test.setLocationCells(hitConditions);

        String result = test.checkYourself("hit");

        assertEquals("hit", result);
    }

    @Test
    void testSetLocationCells() {
        DotCom test = new DotCom();

        ArrayList<String> hitConditions = new ArrayList<String>();

        hitConditions.add("hit");
        hitConditions.add("hit1");

        test.setLocationCells(hitConditions);

        assertEquals(hitConditions, test.locationCells);
    }

    @Test
    void testSetName() {
        DotCom test = new DotCom();

        test.setName("test");

        assertEquals("test", test.getName());
    }

    @Test
    void testGetName() {
        DotCom test = new DotCom();
        DotCom test2 = new DotCom();
        DotCom test3 = new DotCom();

        test.setName("test");
        test2.setName("check");
        test3.setName("yep");

        assertEquals("check", test2.getName());
    }
}
