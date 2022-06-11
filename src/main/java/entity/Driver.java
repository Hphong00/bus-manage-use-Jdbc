package entity;

import baseclass.InputInformation;
import baseclass.Person;

import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver extends Person implements InputInformation, Comparable<Driver>, Comparator<Driver> {
    private static int idAuto = 10000;
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";

    private int id;
    private String levele;

    public Driver() {

    }

    public Driver(String fullName, String address, String phone, int id, String levele) {
        super(fullName, address, phone);
        this.id = id;
        this.levele = levele;
    }

    public Driver(int id, String levele) {
        this.id = id;
        this.levele = levele;
    }

    public static int getIdAuto() {
        return idAuto;
    }

    public static void setIdAuto(int idAuto) {
        Driver.idAuto = idAuto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLevele() {
        return levele;
    }

    public void setLevele(String levele) {
        this.levele = levele;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id + " " +
                super.toString() +
                ", level='" + levele + '\'' +
                '}';
    }

    @Override
    public void inputInformation() {
        id = idAuto++;
        super.inputInformation();
        System.out.println("Nhâp loại trình độ: ");
        levelChoose();
    }

    public void levelChoose() {
        int s = 0;
        do {
            System.out.println("1-6. A-F");
            try {
                Scanner scanner = new Scanner(System.in);
                s = scanner.nextInt();
                if (s <= 6 && s >= 1) {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Mời Nhập lại:");
            }
        } while (true);
        switch (s) {
            case 1:
                this.levele = Driver.A;
                break;
            case 2:
                this.levele = Driver.B;
                break;
            case 3:
                this.levele = Driver.C;
                break;
            case 4:
                this.levele = Driver.D;
                break;
            case 5:
                this.levele = Driver.E;
                break;
            case 6:
                this.levele = Driver.F;
                break;
        }
    }

    @Override
    public int compareTo(Driver o) {
        return 0;
    }

    @Override
    public int compare(Driver o1, Driver o2) {
        return 0;
    }
}
