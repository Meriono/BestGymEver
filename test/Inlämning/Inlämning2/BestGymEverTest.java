package Inlämning.Inlämning2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Hanna Edlund
 * Date: 2020-10-08
 * Time: 13:47
 * Project: Sprint2
 */
class BestGymEverTest {
    BestGymEver bge = new BestGymEver();
    List<String> fullList = new ArrayList<>();

    @Test
    void checkMembershipTest(){
        fullList.add("Sara");
        fullList.add("2018-12-02");
        fullList.add("Hanna");
        fullList.add("2020-01-01");

        assertTrue(bge.checkMembership(fullList, "Hanna").equals("Kunden är aktivt medlem"));
        assertFalse(bge.checkMembership(fullList, "Pelle").equals("Kunden är aktivt medlem"));
        assertTrue(bge.checkMembership(fullList, "Sara").endsWith("oaktivt medlemskap"));
    }

    @Test
    void getInputTest(){
        bge.test = true;

        String ok = "Hanna";
        assertFalse(bge.getInput(ok).isBlank());
        assertTrue(bge.getInput(ok).equals("Hanna"));
    }

    public List<String> getRecord(){
        List<String> trackRecord = bge.getListFromFile(bge.outRecord);
        return trackRecord;
    }

    @Test
    void setRecordTest(){
        fullList.add("Adam");
        fullList.add("Bertil");
        fullList.add("Cesar");
        bge.setRecord(fullList.get(1));
        assertTrue(getRecord().contains("Bertil"));
        assertFalse((getRecord().contains("Adam")));
    }
}