package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MemberSystem {
    ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);


    // der er problem med Array listen, id og Array listen plads er ikke den samme.
    public void addMember() {
        System.out.println();
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        try {
            System.out.println("Indtast fødselsdato. yyyy-mm-dd");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            int makeId = listMember.size() + 1;
            Member addMember = new Member(makeId, name, date);
            listMember.add(addMember);
            System.out.println("sæt medlemskab");
            addMember.setExercise(true);
            int checkAge = LocalDate.now().compareTo(date);
            if (addMember.getExercise() == true && checkAge >= 18 && checkAge <= 65) {
                addMember.setPrice(1600);
            } else if (addMember.getExercise() == true && checkAge < 18) {
                addMember.setPrice(1000);
            } else if (addMember.getExercise() == true && checkAge > 65) {
                addMember.setPrice(1200);
            }
            System.out.println(addMember.getPrice());
            System.out.println("Vil du betale nu eller senere, tryk j eller n for ja/nej.");
            String payNow = scanner.nextLine();
            if (payNow.equals("j")) {
                newTransaction();
            } else {
                addMember.setHasPaid(false);
            }
            System.out.println("Du har oprettet et ny medlem");

        } catch (DateTimeParseException d) {
            System.out.println("Du har trykket forkert husk! YYYY-MM-DD");
            addMember();
        }
    }

    public void seeIfMemberHadPaid() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getHasPaid() == true) {
                System.out.println(listMember.get(i));
            }
        }
    }

    public void seeMemberPassive() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) { //Henter et medlem fra arrayListen og viser hvis det er en motionist svømmer
            if (listMember.get(i).getPassive() == true) {
                System.out.println(listMember.get(i));
            }
        }
    }

    // der er problem med Array listen, id og Array listen plads er ikke den samme.
    public void setNewResult() {
        System.out.println();
        Scanner scan = new Scanner(System.in);
        Disciplin crawl = new Disciplin("Crawl - 500m", 500);
        Disciplin rygsvømning = new Disciplin("Rygsvømning - 500m", 500);
        Disciplin freestyle = new Disciplin("freestyle - 500m", 500);

        System.out.println("Liste over medlemmer:");
        viewMemberList();
        System.out.println("vælg en et medlem");
        int choice = scan.nextInt();

        System.out.println("set ny resultat, skriv tid");
        double tid = scan.nextDouble();

        Result newResult = new Result(tid, LocalDate.now(), rygsvømning);

        System.out.println("vægle en disciplin: ");
        System.out.println("1. for at vægle Rygsvømning - 500m");
        System.out.println("2. for at vægle Crawl - 500m");
        System.out.println("3. for at vægle freestyle - 500m");
        System.out.print("Skriv dit valg: ");
        int menuChoice = scan.nextInt();

        switch (menuChoice) {
            case 1:
                newResult.setDisplin(rygsvømning);
                break;
            case 2:
                newResult.setDisplin(crawl);
                break;
            case 3:
                newResult.setDisplin(freestyle);
                break;
        }


        listMember.get(choice).listeResult.add(newResult);
        System.out.println(listMember.get(choice));

        for (int i = 0; i < listMember.get(choice).listeResult.size(); i++) {
            System.out.println(listMember.get(choice).listeResult.get(i));
        }
    }


    public void viewMemberList() {
        System.out.println();
        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).getPassive() == true) {
                System.out.println(listMember.get(i));
                System.out.println("medlem er passivt");
            }
            if (listMember.get(i).getExercise() == true) {
                System.out.println(listMember.get(i));
                System.out.println("medlem er motionist");
            }
        }
    }

    public void deleteMember() {
        System.out.println();
        System.out.println("Indtast ID nummer for at slette medlem: ");
        int memberIdToDelete = scanner.nextInt();

        Iterator<Member> iterator = listMember.iterator(); // hvordan virker den her metode.
        boolean memberFound = false;

        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.getId() == memberIdToDelete) {
                iterator.remove();
                memberFound = true;
                System.out.println("Medlem slettet!");
                break;
            }
        }
        if (!memberFound) {
            System.out.println("Medlem ikke fundet med det angivne ID.");
        }
    }

    // der skal laves en rettelse så hvis man ændre sit fødsels år skal man ikke betale så meget.
    public void editMember() {
        System.out.println();
        if (listMember.isEmpty()) {
            System.out.println("Ingen medlemmer at redigere.");
            return;
        }

        System.out.println("Liste over medlemmer:");
        viewMemberList();

        System.out.println("Indtast medlemmets ID for at redigere:");
        int memberIdToEdit = scanner.nextInt();
        scanner.nextLine();

        Iterator<Member> iterator = listMember.iterator();
        boolean memberFound = false;

        while (iterator.hasNext()) {
            Member member = iterator.next();
            if (member.getId() == memberIdToEdit) {
                System.out.println("Nuværende medlemsoplysninger: " + member);

                System.out.println("Indtast nyt fulde navn på medlemmet:");
                String newName = scanner.nextLine();
                member.setName(newName);

                System.out.println("Indtast ny fødselsdato. yyyy-mm-dd:");
                LocalDate newDate = LocalDate.parse(scanner.nextLine());
                member.setAge(newDate);

                System.out.println("Ønsker medlemmet at være passivt? j/n");
                String passive = scanner.nextLine();
                if (passive.equals("j")) {
                    member.setPrice(500);
                    member.setPassive(true);
                    System.out.println("Medlemskabet er nu passivt");
                    if (passive.equals("j")) {
                        member.setExercise(false);
                    }
                }

                System.out.println("Medlemsoplysninger opdateret!");
                System.out.println(member);
                System.out.println(member.getPrice());
                System.out.println("medlem er nu passiv: " + member.getPassive());
                memberFound = true;
                break;
            }
        }
        if (!memberFound) {
            System.out.println("Medlem ikke fundet med det angivne ID.");
        }
    }

    public void EkstraMember() {
        System.out.println();
        int makeId = listMember.size() + (1);
        Member ekstraMember1 = new Member(makeId, "Martin Poulsen", LocalDate.of(1960, 2, 21));
        ekstraMember1.setExercise(true);
        ekstraMember1.setPassive(false);
        listMember.add(ekstraMember1);

        int makeId2 = listMember.size() + (1);
        Member ekstraMember2 = new Member(makeId2, "Lars Poulsen", LocalDate.of(2017, 2, 21));
        ekstraMember2.setExercise(false);
        ekstraMember2.setPassive(true);
        listMember.add(ekstraMember2);

        int makeId3 = listMember.size() + (1);
        Member ekstraMember3 = new Member(makeId3, "Hej Poulsen", LocalDate.of(1965, 2, 21));
        ekstraMember3.setExercise(true);
        ekstraMember3.setPassive(false);
        listMember.add(ekstraMember3);

        int makeId4 = listMember.size() + (1);
        Member ekstraMember4 = new Member(makeId4, "Erik Poulsen", LocalDate.of(1997, 2, 21));
        ekstraMember4.setExercise(false);
        ekstraMember4.setPassive(true);
        listMember.add(ekstraMember4);

        int makeId5 = listMember.size() + (1);
        Member ekstraMember5 = new Member(makeId5, "Godmorgen Poulsen", LocalDate.of(2018, 2, 21));
        ekstraMember5.setExercise(true);
        ekstraMember5.setPassive(false);
        listMember.add(ekstraMember5);
    }

    public void newTransaction() {
        System.out.println();
        System.out.println("vælg nr på medlem");
        int choice = scanner.nextInt();
        listMember.get(choice).MemberShipPayment(listMember.get(choice).getPrice());
        listMember.get(choice).setHasPaid(true);
        System.out.println("kontingent er betalt");
    }

    public void listOfPayment(){
        double total=0;
        double total3=0;

        for (int i = 0; i < listMember.size(); i++) {
            if (listMember.get(i).hasPaid == true) {
                total = listMember.get(i).listTransaction.get(0).amount;
               total3 += total;
            }
        }
        System.out.println(total3);
        
    }
}