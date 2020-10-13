package Inlämning.Inlämning2;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
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
    BestGymEver bge = new BestGymEver(true);
    List<String> fullList = new ArrayList<>();
    LocalDate date = LocalDate.of(2020,10,30);

    @Test
    void getListFromFileTest(){
        Path pathForTesting = Paths.get("test\\Inlämning\\Inlämning2\\inputFileTesting");
        List<String> listForTesting = bge.getListFromFile(pathForTesting);

        assertTrue(listForTesting.size() == 6);
        assertTrue(listForTesting.get(0).endsWith("Aromes"));
        assertTrue(listForTesting.get(5).endsWith("03-12"));
    }

    @Test
    void checkMembershipTest(){
        fullList.add("9805101234, Sara Ettefternamn");
        fullList.add("2018-12-02");
        fullList.add("9612311234, Hanna Edlund");
        fullList.add("2020-01-01");

        assertTrue(bge.checkMembership(fullList, "Hanna", date).equals("Kunden är aktivt medlem"));
        assertFalse(bge.checkMembership(fullList, "Pelle", date).equals("Kunden är aktivt medlem"));
        assertTrue(bge.checkMembership(fullList, "Sara", date).endsWith("oaktivt medlemskap"));
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

    boolean doesRecordContain(String input){
        for (String s: getRecord()) {
            if(s.toLowerCase().contains(input.toLowerCase()))
                return true;
        }
        return false;
    }

    @Test
    void setRecordTest(){
        fullList.add("Adam");
        fullList.add("8808080000, Bertil Bertilsson");
        fullList.add("Cesar");
        bge.setRecord(fullList.get(1), date);

        assertTrue(doesRecordContain("Bertil"));
        assertFalse(doesRecordContain("Adam"));
    }
}