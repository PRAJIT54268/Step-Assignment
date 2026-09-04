class BusTicketAccount {
    private static int count;

    protected String bookingId;
    protected double ticketFare;

    static {
        count = 0;
    }

    public BusTicketAccount(String bookingId, double ticketFare) {
        if (ticketFare < 0)
            throw new IllegalArgumentException();

        this.bookingId = bookingId;
        this.ticketFare = ticketFare;
        count++;
    }

    public BusTicketAccount(String bookingId) {
        this(bookingId, 0);
    }

    public final double calculatePenalty(int minutesLate) {
        if (minutesLate < 0)
            throw new IllegalArgumentException();

        return ticketFare * 0.01 * minutesLate;
    }
}

class SleeperAccount extends BusTicketAccount {
    public SleeperAccount(String bookingId, double ticketFare) {
        super(bookingId, ticketFare);
    }

    public SleeperAccount(String bookingId) {
        super(bookingId);
    }
}

public class FleetReconciliation {
    static double totalPenalty;

    static void processAccount(
            BusTicketAccount account,
            double amount,
            int minutesLate) {

        if (account == null)
            return;

        double oldFare = account.ticketFare;

        if (account instanceof SleeperAccount)
            account.ticketFare = amount;
        else
            account.ticketFare = amount;

        totalPenalty += account.calculatePenalty(minutesLate);

        account.ticketFare = oldFare;
    }

    static void processBatch(
            BusTicketAccount[] accounts,
            double[] amounts,
            int[] minutesLateArray) {

        if (accounts == null ||
            amounts == null ||
            minutesLateArray == null)
            return;

        int n = Math.min(accounts.length,
                Math.min(amounts.length, minutesLateArray.length));

        int processed = 0, skipped = 0, sleeper = 0, regular = 0;

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                skipped++;
                continue;
            }

            if (accounts[i] instanceof SleeperAccount)
                sleeper++;
            else
                regular++;

            try {
                processAccount(
                    accounts[i],
                    amounts[i],
                    minutesLateArray[i]
                );
                processed++;
            } catch (IllegalArgumentException e) {
                skipped++;
            }
        }

        System.out.println(
            processed + " processed | " +
            skipped + " null skipped | " +
            sleeper + " sleeper | " +
            regular + " regular"
        );

        System.out.println(
            "Grand total penalties = " + totalPenalty
        );
    }

    public static void main(String[] args) {
        BusTicketAccount[] accounts = {
            new SleeperAccount("BK001", 2000),
            null,
            new BusTicketAccount("BK002", 1200)
        };

        double[] amounts = {1200, 900, 700};
        int[] minutesLate = {10, 5, 0};

        processBatch(accounts, amounts, minutesLate);
    }
}