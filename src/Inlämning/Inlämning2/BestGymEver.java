package Inlämning.Inlämning2;

import java.io.File;
import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hanna Edlund
 * Date: 2020-10-08
 * Time: 13:36
 * Project: Sprint2
 */
public class BestGymEver {

    String members = "customers.txt";

    public List<String> getListFromFile(String members){
        List<String> fullList = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(members));
            while(sc.hasNextLine()){
                fullList.add(sc.nextLine());
            }
        }
        catch (Exception e){
            System.out.println("Error");
            System.exit(0);
        }
        return fullList;
    }

    public void printMember(List<String> fullList, String input){
        for (String s: fullList) {
            if(s.contains(input)){
                System.out.println("MEDLEM");
                int placeInList = fullList.indexOf(s)+1;
                System.out.println("Finns på rad: " + placeInList);
                System.out.println("Medlem sedan: " + fullList.get(placeInList));
                System.out.println(LocalDate.parse(fullList.get(placeInList)));
                LocalDate gammal = LocalDate.parse(fullList.get(placeInList));

                System.out.println(gammal.plusDays(365));
                int counter = gammal.plusDays(365).compareTo(LocalDate.now());
                if (counter < 0)
                    System.out.println("GAMMALT MEDLEMSKAP");
                else if (counter > 0)
                    System.out.println("MEDLEM");
                else
                    System.out.println("Medlemskapet går ut idag");
            }
        }
    }

    public String getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv in en medlems namn eller personnummer");
        String input = null;

        while (sc.hasNextLine()){
            input = sc.nextLine();
            if(input.equals("KLAR"))
                break;

            return input;
        }
        return input;
    }

    public BestGymEver(){
        List<String> fullList = getListFromFile(members);
        printMember(fullList, getInput());
    }

    public static void main(String[] args) {
        BestGymEver bge = new BestGymEver();
    }
}

/*
        Ta input, namn eller personnummer
        läs i filen om personen finns.
            finns? När betalades årsavgiften.
                inom ett år - MEDLEM
                för över ett år sen - FÖREDETTA MEDLEM
            finns inte? - INTE MEDLEM


            Om MEDLEM
            skriv ner när kund besökt gymmet i en egen fil.

 */
