public class BusRoute {
    private final String routeCode, routeName;
    private final int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {
        if (priority != other.priority)
            return Integer.compare(other.priority, priority);

        int x = routeCode.compareToIgnoreCase(other.routeCode);

        if (x != 0) return x;

        return routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {
        for (int i = 0; i < routes.length - 1; i++)
            for (int j = 0; j < routes.length - i - 1; j++)
                if (routes[j].compareTo(routes[j + 1]) > 0) {
                    BusRoute t = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = t;
                }

        return routes;
    }

    public static void main(String[] args) {
        BusRoute[] a = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        rankRoutes(a);

        for (BusRoute x : a)
            System.out.println(x.routeCode);
    }
}