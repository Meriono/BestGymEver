package Inlämning.Inlämning2;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    Path inMembers = Paths.get("src\\Inlämning\\Inlämning2\\customers.txt");
    Path outRecord = Paths.get("src\\Inlämning\\Inlämning2\\record.txt");
    public Boolean test = false;

    public List<String> getListFromFile(Path inPath){
        List<String> completeList = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(inPath.toString()))){
            while(sc.hasNextLine()){
                completeList.add(sc.nextLine());
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Filen går inte att hitta");
            System.exit(0);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        return completeList;
    }

    public String checkMembership(List<String> completeList, String input, LocalDate date){
        for (String s: completeList) {
            if(s.contains(input)){
                //placeInList beräknar ut vilken rad kunden står på
                int placeInList = completeList.indexOf(s)+1;

                //String som innehåller all kundens information, personnummer + namn
                String fullMembersInfo = completeList.get(completeList.indexOf(s));

                //dateofMembership gör om String datumet till en LocalDate
                LocalDate dateOfMembership = LocalDate.parse(completeList.get(placeInList));

                //counter jämför om dateOfMembership + 365 dagar är innan, efter eller det valda datumet och skriver ut status på medlemskapet
                int counter = dateOfMembership.plusDays(365).compareTo(date);

                if (counter < 0)
                    return "Kunden har ett oaktivt medlemskap";
                else if (counter > 0){
                    setRecord(fullMembersInfo, date);
                    return "Kunden är aktivt medlem";
                }
                else
                    return "Medlemskapet går ut idag";
            }
        }
        return "Inget medlemskap hittades";
    }

    public void setRecord(String member, LocalDate date) {
        try(PrintWriter ut = new PrintWriter(new BufferedWriter(new FileWriter(outRecord.toString(), true)))){
            ut.println(member);
            ut.println(date);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getInput(String inputForTesting){
        Scanner sc;
        if(test){
            sc = new Scanner(inputForTesting);
        }
        else{
            sc = new Scanner(System.in);
            System.out.println("Skriv in ett namn eller personnummer");
        }

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
        List<String> fullList = getListFromFile(inMembers);
        System.out.println(checkMembership(fullList, getInput(null), LocalDate.now()));
    }

    public static void main(String[] args) {
        BestGymEver start = new BestGymEver();
    }
}
