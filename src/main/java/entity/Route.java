package entity;

import baseclass.InputInformation;

import java.io.Serializable;
import java.util.Scanner;

public class Route implements InputInformation {
    private static int idAuto = 100;
    private int id;
    private double distance;
    private int numberOfStops;

    public Route(){

    }

    public Route(int id, double distance, int numberOfStops) {
        this.id = id;
        this.distance = distance;
        this.numberOfStops = numberOfStops;
    }

    public static int getIdAuto() {
        return idAuto;
    }

    public static void setIdAuto(int idAuto) {
        Route.idAuto = idAuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getNumberOfStops() {
        return numberOfStops;
    }

    public void setNumberOfStops(int numberOfStops) {
        this.numberOfStops = numberOfStops;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", distance=" + distance +
                ", numberOfStops=" + numberOfStops +
                '}';
    }
    @Override
    public void inputInformation() {
        try {
            id = idAuto++;
            System.out.println("Nhâp khoảng cách: ");
            distance = new Scanner(System.in).nextDouble();
            System.out.println("Nhập điểm dừng: ");
            numberOfStops = new  Scanner(System.in).nextInt();
        }catch (Exception ex){
            System.out.println("Mời nhập lại: ");
        }
    }
}
