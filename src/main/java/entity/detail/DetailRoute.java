package entity.detail;

import entity.Route;

import java.util.ArrayList;

public class DetailRoute {


    private Route route;
    private int numberOfTurns;

    public DetailRoute(Route route, int numberOfTurns) {
        this.route = route;
        this.numberOfTurns = numberOfTurns;
    }

    public DetailRoute() {

    }
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    @Override
    public String toString() {
        return "DetailRoute{" +
                "route=" + route +
                ", numberOfTurns=" + numberOfTurns +
                '}';
    }
}
