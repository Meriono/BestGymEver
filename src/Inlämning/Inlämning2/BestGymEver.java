package Inlämning.Inlämning2;

import java.io.File;
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

    public void checkMembership(List<String> fullList, String input){
        for (String s: fullList) {
            if(s.contains(input)){
                //placeInList beräknar ut vilken rad kunden står på
                int placeInList = fullList.indexOf(s)+1;
                //dateofMembership gör om String datumet till en LocalDate
                LocalDate dateOfMembership = LocalDate.parse(fullList.get(placeInList));
                //counter jämför om dateOfMembership + 365 dagar är innan, efter eller idag och skriver ut status på medlemskapet
                int counter = dateOfMembership.plusDays(365).compareTo(LocalDate.now());

                if (counter < 0)
                    System.out.println("Kunden har ett gammalt medlemskap");
                else if (counter > 0)
                    System.out.println("Kunden är medlem");
                else
                    System.out.println("Medlemskapet går ut idag");

                System.exit(0);
            }
        }
        System.out.println("Inget medlemskap hittades");
    }

    public String getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv in ett namn eller personnummer");

        while (true){
            String input = sc.nextLine();

            if(input.isBlank()){
                System.out.println("Du har inte angett något namn eller personnummer");
                continue;
            }
            return input;
        }
    }

    public BestGymEver(){
        List<String> fullList = getListFromFile(members);
        checkMembership(fullList, getInput());
    }

    public static void main(String[] args) {
        BestGymEver start = new BestGymEver();
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
