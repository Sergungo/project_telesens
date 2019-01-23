package ua.golenko.simulation.Model;

//enum BusStops {
//    SPORTIVNA, MYASOKOMBINAT, BOLNITSA13, SHKOLA, ZERNOVAYA, ODESSKAYA, DEPO2, SALUT, MOROZOVA, VOYKOVA,
//    PARKARTEMA, MALYSHEVA, POLEVAYA, MOSKOVSKIYPRWOSPEKT, DVORETSSPORTA, YUJNOPROEKTNAYA, EPICENTR
//
//}

public class BusStop {

    private String name;

    public BusStop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

     