package week3;

public class LibraryMembership {

    static class LibraryMemberBroken {
        static String name;
        static String memberId;
        static int booksIssued;

        LibraryMemberBroken(String name, String memberId, int booksIssued) {
            LibraryMemberBroken.name = name;
            LibraryMemberBroken.memberId = memberId;
            LibraryMemberBroken.booksIssued = booksIssued;
        }
    }

    static class LibraryMember {
        String name, memberId;
        int booksIssued;
        static String libraryName = "SRM Central Library";
        static int memberCount = 0;

        LibraryMember(String name, int booksIssued) {
            memberCount++;
            this.memberId = "LM-" + (1000 + memberCount);
            this.name = name;
            this.booksIssued = booksIssued;
        }

        void printMemberCard() {
            System.out.println(name + " | " + memberId);
        }

        static void printTotalMembers() {
            System.out.println("Total members: " + memberCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== BROKEN VERSION ===");
        LibraryMemberBroken b1 = new LibraryMemberBroken("Aditi", "LM-001", 3);
        LibraryMemberBroken b2 = new LibraryMemberBroken("Rohan", "LM-002", 1);
        System.out.println(b1.name);
        System.out.println(b2.name);

        System.out.println("\n=== FIXED VERSION ===");
        LibraryMember m1 = new LibraryMember("Aditi", 3);
        LibraryMember m2 = new LibraryMember("Rohan", 1);
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}