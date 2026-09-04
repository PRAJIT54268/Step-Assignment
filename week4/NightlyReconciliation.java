class DeliveryAccount {
    private static int count;

    protected String studentId;
    protected double orderValue;

    static {
        count = 0;
    }

    public DeliveryAccount(String studentId, double orderValue) {
        if (orderValue < 0)
            throw new IllegalArgumentException();

        this.studentId = studentId;
        this.orderValue = orderValue;
        count++;
    }

    public DeliveryAccount(String studentId) {
        this(studentId, 0);
    }

    public final double calculateSurgeFee(int delayMinutes) {
        if (delayMinutes < 0)
            throw new IllegalArgumentException();

        if (delayMinutes == 0)
            return 0;

        double fee = Math.min(delayMinutes, 5) * orderValue * .005;

        if (delayMinutes > 5)
            fee += Math.min(delayMinutes - 5, 10) * orderValue * .01;

        if (delayMinutes > 15)
            fee += (delayMinutes - 15) * orderValue * .02;

        return fee;
    }
}

class Premium extends DeliveryAccount {
    public Premium(String studentId, double orderValue) {
        super(studentId, orderValue);
    }

    public Premium(String studentId) {
        super(studentId);
    }
}

public class NightlyReconciliation {
    static double total;

    static void processAccount(
            DeliveryAccount account, double amount, int delayMinutes) {

        if (account == null)
            return;

        double old = account.orderValue;
        account.orderValue = amount;

        if (account instanceof Premium)
            account.orderValue *= 0.5;

        total += account.calculateSurgeFee(delayMinutes);
        account.orderValue = old;
    }

    static void processBatch(
            DeliveryAccount[] accounts,
            double[] amounts,
            int[] delayMinutesArray) {

        int n = Math.min(accounts.length,
                Math.min(amounts.length, delayMinutesArray.length));

        int processed = 0, skipped = 0, premium = 0, regular = 0;

        for (int i = 0; i < n; i++) {
            if (accounts[i] == null) {
                skipped++;
                continue;
            }

            if (accounts[i] instanceof Premium)
                premium++;
            else
                regular++;

            processAccount(accounts[i], amounts[i], delayMinutesArray[i]);
            processed++;
        }

        System.out.println(
            processed + " processed | " +
            skipped + " null skipped | " +
            premium + " premium | " +
            regular + " regular"
        );

        System.out.println("Grand total surge fees = " + total);
    }

    public static void main(String[] args) {
        DeliveryAccount[] accounts = {
            new Premium("STU001", 500),
            null,
            new DeliveryAccount("STU002", 300)
        };

        double[] amounts = {500, 400, 300};
        int[] delay = {10, 5, 0};

        processBatch(accounts, amounts, delay);
    }
}