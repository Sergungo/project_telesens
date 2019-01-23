package ua.golenko.simulation.Model;

import java.util.Date;

public class Bus {

    private final int maxCapacity = 60;
    private static int currentLoad;
    private int busid;
    private int routeid;
    private double speed;

    private BusStop firstBusstop;
    private BusStop lastBusstop;

    private Date routeDeparture;
    private Date routeArrival;

//    private Route route;
}
