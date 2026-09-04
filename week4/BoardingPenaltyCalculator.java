public final class BoardingPenaltyCalculator {
    private final double minimumPenaltyPercent;

    public BoardingPenaltyCalculator(double minimumPenaltyPercent) {
        if (minimumPenaltyPercent < 0)
            throw new IllegalArgumentException();

        this.minimumPenaltyPercent = minimumPenaltyPercent;
    }

    public final double calculatePenalty(double ticketFare, int minutesLate) {
        if (ticketFare < 0 || minutesLate < 0)
            throw new IllegalArgumentException();

        if (minutesLate == 0)
            return 0.0;

        double p = Math.min(minutesLate, 5) * ticketFare * 0.005;

        if (minutesLate > 5)
            p += Math.min(minutesLate - 5, 10) * ticketFare * 0.01;

        if (minutesLate > 15)
            p += (minutesLate - 15) * ticketFare * 0.02;

        p = Math.max(p, ticketFare * minimumPenaltyPercent / 100);

        return Math.round(p * 100) / 100.0;
    }

    public static void main(String[] args) {
        BoardingPenaltyCalculator b =
                new BoardingPenaltyCalculator(1);

        System.out.println(b.calculatePenalty(1000, 0));
        System.out.println(b.calculatePenalty(1000, 1));
        System.out.println(b.calculatePenalty(1000, 16));
    }
}