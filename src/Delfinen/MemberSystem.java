package Delfinen;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MemberSystem {
    ArrayList<Member> listMember = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    // der er problem med Arrylisten, id og Arrylisten plads er ikke den samme.
    public void addMember() {
        System.out.println("Opret medlem");
        System.out.println("Indtast fulde navn på medlemmet");
        String name = scanner.nextLine();
        try {
            System.out.println("Indtast fødselsdato. yyyy-mm-dd");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            int makeId = listMember.size() + (1);
            Member m1= new Member(makeId,name, date);
            listMember.add(m1);
            System.out.println("sæt medlemskab");
            m1.setExercise(true);
            int checkAge =LocalDate.now().compareTo(date);
            if (m1.getExercise()==true && checkAge>=18 && checkAge<=65){
                m1.setPrice(1600);
            } else if (m1.getExercise()==true && checkAge<18) {
                m1.setPrice(1000);
            } else if (m1.getExercise()==true && checkAge>65) {
                m1.setPrice(1200);
            }
            System.out.println(m1.getPrice());
            System.out.println("Vil du betale nu eller senere, tryk j eller n for ja/nej.");
            String payNow = scanner.nextLine();
            if (payNow.equals("j")) {
                m1.setHasPaid(true);
            } else {
                m1.setHasPaid(false);
            }
            System.out.println("Du har oprettet et ny medlem");

        }catch (DateTimeParseException d){
            System.out.println("Du har trykket forkert husk! YYYY-MM-DD");
            addMember();
        }
    }
    public void seeIfMemberHadPaid() {
        for (Member member : listMember) {
            if(member.getHasPaid()) {
                System.out.println(member);

            }
        }
    }
    public void seeMemberPassive() {
        for (Member member : listMember) {
            if(member.getPassive()) {
                System.out.println(member);
            }
        }
    }
        // der er problem med Arrylisten, id og Arrylisten plads er ikke den samme.
    public void setNewResult(){
        Scanner scan = new Scanner(System.in);
        Disciplin crawl = new Disciplin("Crawl - 500m",500);
        Disciplin rygsvømning = new Disciplin("Rygsvømning - 500m",500);
        Disciplin freestyle = new Disciplin("freestyle - 500m",500);

        System.out.println("Liste over medlemmer:");
        viewMemberList();
        System.out.println("vælg en et medlem");
        int choice = scan.nextInt();

        System.out.println("set ny resultat, skriv tid");
        double tid = scan.nextDouble();

        Result re = new Result(tid, LocalDate.now(),rygsvømning);

        System.out.println("vægle en disciplin: ");
        System.out.println("1. for at vægle Rygsvømning - 500m");
        System.out.println("2. for at vægle Crawl - 500m");
        System.out.println("3. for at vægle freestyle - 500m");
        int menuChoice = scan.nextInt();

            switch (menuChoice) {
                case 1:
                    re.setDisplin(rygsvømning);
                    break;
                case 2:
                    re.setDisplin(crawl);
                    break;
                case 3:
                    re.setDisplin(freestyle);
                    break;
            }


        listMember.get(choice).listeResult.add(re);
        System.out.println(listMember.get(choice));

        for (int i = 0; i <listMember.get(choice).listeResult.size(); i++) {
            System.out.println(listMember.get(choice).listeResult.get(i));
        }
    }


    public void viewMemberList() {
        for (int i = 0; i < listMember.size() ; i++) {
            if (listMember.get(i).getPassive() == true){
                System.out.println(listMember.get(i));
                System.out.println("Medlem er passivt");
                //System.out.println(listMember.get(i).getPrice());
            }
            if (listMember.get(i).getExercise() == true ){
                System.out.println(listMember.get(i));
                System.out.println("Medlem er motionist");
                //System.out.println(listMember.get(i).getPrice());
            }
        }
    }

    public void deleteMember() {
        System.out.println("Indtast medlemmets ID for at slette:");
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
    /*public void deleteMembers(int index) {     //chatten hjalp til her.
            if (index>=0 && index<listMember.size()) {
                listMember.remove(index);
            } else
                System.out.println("invalid, try again.");
        }*/
    public void EkstraMember() {

        int makeId = listMember.size() + (1);
        Member m1 = new Member(makeId, "Sebastian Drumm", LocalDate.of(1960, 2, 21));
        m1.setExercise(true); m1.setPassive(false);
        m1.setHasPaid(true);
        m1.setPrice(1200.0);
        listMember.add(m1);

        int makeId2 = listMember.size() + (1);
        Member m2 = new Member(makeId2, "Viktor Rasmussen", LocalDate.of(2017, 2, 21));
        m2.setExercise(false); m2.setPassive(true);
        m2.setHasPaid(true);
        m2.setPrice(500.0);
        listMember.add(m2);

        int makeId3 = listMember.size() + (1);
        Member m3 = new Member(makeId3, "Laurits Larsen", LocalDate.of(1965, 2, 21));
        m3.setExercise(true); m3.setPassive(false);
        m3.setHasPaid(true);
        m3.setPrice(1600.0);
        listMember.add(m3);

        int makeId4 = listMember.size() + (1);
        Member m4 = new Member(makeId4, "Martin Poulsen", LocalDate.of(1997, 2, 21));
        m4.setExercise(false); m4.setPassive(true);
        m4.setHasPaid(true);
        m4.setPrice(500.0);
        listMember.add(m4);

        int makeId5 = listMember.size() + (1);
        Member m5 = new Member(makeId5, "Tunahan Turan", LocalDate.of(2018, 2, 21));
        m5.setExercise(true); m5.setPassive(false);
        m5.setHasPaid(false);
        m5.setPrice(1000.0);
        listMember.add(m5);
    }
}