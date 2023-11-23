
import java.util.ArrayList;


public class MemberManager {
    private ArrayList<String> members;

    public MemberManager() {
        members = new ArrayList<>();
    }

    public boolean deleteMember(String memberName) {
        return members.remove(memberName);
    }

    public void printMembers() {
        System.out.println("Medlemmer:");
        for (String member : members) {
            System.out.println(member);
        }
    }

    public static void main(String[] args) {
        MemberManager memberManager = new MemberManager();

        System.out.println("Før sletning:");
        memberManager.printMembers();

        // Slet et medlem
        String memberToDelete = "Medlem2";
        boolean deleted = memberManager.deleteMember(memberToDelete);

        if (deleted) {
            System.out.println("Medlemmet '" + memberToDelete + "' er slettet.");
        } else {
            System.out.println("Medlemmet '" + memberToDelete + "' blev ikke fundet.");
        }

        System.out.println("Efter sletning:");
        memberManager.printMembers();
    }
}