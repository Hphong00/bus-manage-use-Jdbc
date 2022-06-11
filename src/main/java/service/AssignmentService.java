package service;

import connectDatabase.ConnectData;
import entity.Assignment;
import entity.Driver;
import entity.Route;
import entity.detail.DetailRoute;
import main.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AssignmentService {

    public static void addListAssignment() throws Exception {
        do {
            int numberAssignment = -1;
            try {
                System.out.println("Nhập số lượng phân côn");
                numberAssignment = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("Mời nhập lại: ");
            }
            Driver driver = null;
            for (int i = 0; i < numberAssignment; i++) {
                try {
                    System.out.println("Nhập mã lái xe: ");
                    int idDriver = new Scanner(System.in).nextInt();
                    driver = DriverService.findDriverToArrlist(idDriver);

                    System.out.println("Nhập số tuyến: ");
                    int numberRoute = new Scanner(System.in).nextInt();

                    ArrayList<DetailRoute> detailRouteArrayList = new ArrayList<DetailRoute>();
                    addDtRouteToArrayList(detailRouteArrayList,numberRoute);

                    Main.assignment.addAssignmentToMap(driver,detailRouteArrayList);

                    Main.assignment.showAssignmentMap();
                } catch (Exception ex) {
                    System.out.println("Mời nhập lại mã");
                }
            }
            if (driver != null) {
                break;
            }
        } while (true);
    }

    public static void addDtRouteToArrayList(ArrayList<DetailRoute> detailRouteArrayList,int numberRoute) {
        int totalNumberOfTurns = 0;
        double statistical = 0;
        for (int i = 0; i < numberRoute; i++) {
            System.out.println("Nhập mã tuyến");
            int idRoute = new Scanner(System.in).nextInt();
            Route route = RouteService.findRouteToArraylist(idRoute);
            System.out.println("Nhập sô lượt lái của tuyến");
            int numberOfTurns = new Scanner(System.in).nextInt();
            if (totalNumberOfTurns>15) {
                System.out.println("Nhập quá 15.");
                break;
            }
            totalNumberOfTurns += numberOfTurns;
            if (totalNumberOfTurns>15) {
                System.out.println("Nhập quá 15.");
                break;
            }
            DetailRoute detailRoute = new DetailRoute(route, numberOfTurns);
            detailRouteArrayList.add(detailRoute);
            System.out.println("Tổng số lượt trong ngày: " + totalNumberOfTurns);


            statistical+= route.getDistance() * numberOfTurns;
        }
        //khoang cach 1 ngayf  = khoang cach * so lan * so tuyen
        System.out.print("Tổng khoảng cách trong ngày: ");
        statistical=statistical*numberRoute;
        System.out.println(statistical);
    }

    public static void sort() {
        int number = 0;
        do {
            try {
                System.out.println("Mời bạn chọn sắp xếp: ");
                System.out.println("1. Theo Họ tên lai xe. ");
                System.out.println("2. Theo Tên số lượng tuyến . ");
                number = new Scanner(System.in).nextInt();
                if (number == 1 || number == 2) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Mời nhập lại: ");
            }
        } while (true);
        if (number == 1) {
            Main.assignment.sortNameDriver();
        } else if (number == 2) {
            Main.assignment.sortNumberRoute();
        }
    }
}
