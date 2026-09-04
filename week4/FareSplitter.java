public class FareSplitter {
    private final String tripId;
    private final double totalFare;
    private final int passengerCount;

    public FareSplitter(String tripId, double totalFare, int passengerCount) {
        if (totalFare < 0 || passengerCount <= 0)
            throw new IllegalArgumentException();

        this.tripId = tripId;
        this.totalFare = totalFare;
        this.passengerCount = passengerCount;
    }

    public FareSplitter(String tripId, double totalFare) {
        this(tripId, totalFare, 2);
    }

    public FareSplitter(String tripId) {
        this(tripId, 0, 2);
    }

    public double[] fareBreakdown() {
        double[] a = new double[passengerCount];
        double share = Math.floor(totalFare / passengerCount * 100) / 100;
        double used = 0;

        for (int i = 0; i < passengerCount - 1; i++) {
            a[i] = share;
            used += share;
        }

        a[passengerCount - 1] =
                Math.round((totalFare - used) * 100) / 100.0;

        return a;
    }

    public boolean isConfirmationOverdue(int confirmed, int expected) {
        return confirmed < expected;
    }

    public static void main(String[] args) {
        double[] a = new FareSplitter("TRIP001", 100000, 3).fareBreakdown();

        for (double x : a)
            System.out.printf("%.2f ", x);
    }
}