package baseclass;

import java.util.Scanner;

public class Person implements InputInformation {
    private String fullName;
    private String address;
    private String phone;

    public Person(String fullName, String address, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    private static Scanner scanner = new Scanner(System.in);

    public Person() {

    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public void inputInformation() {
        System.out.println("Nhập tên người lái: ");
        fullName = scanner.nextLine();
        System.out.println("Nhập địa chỉ: ");
        address = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        phone = scanner.nextLine();
    }
}
