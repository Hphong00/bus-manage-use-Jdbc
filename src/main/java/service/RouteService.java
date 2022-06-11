package service;

import connectDatabase.ConnectData;
import entity.Driver;
import entity.Route;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class RouteService {
    static List<Route> routeList = new ArrayList<Route>();
    public static void addRouteToList() throws IOException {
        System.out.println("Nhập số tuyến: ");
        do {
            int numberRoute = -1;
            Route route = null;
            try {
                numberRoute = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("Mời nhập lại ");
            }
            for (int i = 0; i < numberRoute; i++) {
                route = new Route();
                route.inputInformation();
                routeList.add(route);
            }
            insertRoute(routeList);
            if (route != null) {
                break;
            }
        } while (true);
        System.out.println("---------");
        showAllRouteFromArrayList();
        System.out.println("---------");
    }
    public static void showAllRouteFromArrayList() {
        for (Route route : routeList) {
            System.out.println(route.toString());
        }
    }
    public static Route findRouteToArraylist(int idRoute){
        for (Route route: routeList) {
            if(route != null && route.getId() == idRoute){
                System.out.println(route);
                return route;
            }
        }
        return null;
    }
    public static void insertRoute(List<Route> routeList) {
        String SQL = "INSERT INTO route(id,Distance,NumberOfStops) "
                + "VALUES(?,?,?)";
        try (
                Connection conn = ConnectData.connect();
                PreparedStatement statement = conn.prepareStatement(SQL);) {
            int count = 0;
            for (Route route : routeList) {
                statement.setInt(1, route.getId());
                statement.setInt(2, (int) route.getDistance());
                statement.setInt(3, route.getNumberOfStops());

                statement.addBatch();
                count++;
                if (count % 100 == 0 || count == routeList.size()) {
                    statement.executeBatch();
                }
            }
            conn.close();
            statement.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
