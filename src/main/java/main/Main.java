package main;

import entity.Assignment;
import service.AssignmentService;
import service.DriverService;
import service.RouteService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Assignment assignment = new Assignment();

    public static void main(String[] args) throws Exception {
        DriverService driverService = new DriverService();
        RouteService routeService = new RouteService();
        sMenu();
        select();
    }
    private static void select() throws Exception {
        boolean cont = true;
        do {
            System.out.print("Mời bạn chọn: ");
            int number = 0;
            try {
                number = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Moi nhap lai: ");
            }
            switch (number) {
                case 1:
                    DriverService.addListDrivertoList();
                    sMenu();
                    break;
                case 2:
                    DriverService.showAllDriverFromArrList();
                    sMenu();
                    break;
                case 3:
                    RouteService.addRouteToList();
                    sMenu();
                    break;
                case 4:
                    RouteService.showAllRouteFromArrayList();
                    sMenu();
                    break;
                case 5:
                    AssignmentService.addListAssignment();
                    sMenu();
                    break;
                case 6:
                    AssignmentService.sort();
                    sMenu();
                    break;
                case 7:
                    System.exit(0);
            }
        } while (cont);
    }

    private static void sMenu() {
        System.out.println("---Quản lý điểm sinh viên---");
        System.out.println("1. Nhập danh sách lái xe. ");
        System.out.println("2. Xem list lái xe. ");
        System.out.println("3. Nhập danh sách tuyến. ");
        System.out.println("4. Xem list tuyến. ");
        System.out.println("5. Nhập danh sách phân công. ");
        System.out.println("6. Sắp xếp. ");
        System.out.println("7. Thoat. ");;
    }
}
