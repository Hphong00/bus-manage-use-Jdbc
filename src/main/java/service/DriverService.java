package service;
import com.sun.tools.javac.Main;
import connectDatabase.ConnectData;
import entity.Driver;
import entity.Route;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DriverService {
    static List<Driver> driverList = new ArrayList<Driver>();

    public static void addListDrivertoList() throws IOException {
        System.out.println("Nhập số người lái: ");
        do {
            int numberDriver = -1;
            Driver driver = null;
            try {
                numberDriver = new Scanner(System.in).nextInt();
            } catch (Exception ex) {
                System.out.println("Mời nhập lại ");
            }
            for (int i = 0; i < numberDriver; i++) {
                driver = new Driver();
                driver.inputInformation();
                driverList.add(driver);
            }
            insertDriver(driverList);
            if (driver != null) {
                break;
            }
        } while (true);
        System.out.println("---------");
        showAllDriverFromArrList();
        System.out.println("---------");
    }

    public boolean removeDriverAtLocation(){
        int location = 0;
        try {
            location = new Scanner(System.in).nextInt();
        }catch (Exception ex){
            System.out.println("Mời nhập lại");
        }
        if((location<0) || (location>driverList.size())){
            return false;
        }
        else {
            driverList.remove(location - 1);
            return true;
        }
    }

    public static void showAllDriverFromArrList() {
        for (Driver driver : driverList) {
            System.out.println(driver.toString());
        }
    }
    public static Driver findDriverToArrlist(int idDriver){
        for (Driver driver: driverList) {
            if(driver != null && driver.getId() == idDriver){
                System.out.println(driver);
                return driver;
            }
        }
        return null;
    }
    public static void insertDriver(List<Driver> driverList) {
        String SQL = "INSERT INTO driver(id,fullName,address,phone,levele) "
                + "VALUES(?,?,?,?,?)";
        try (
                //Tạo kết nối cơ sở dữ liệu.
            Connection conn = ConnectData.connect();
            //Tạo một PreparedStatementđối tượng.
            PreparedStatement statement = conn.prepareStatement(SQL)){
            int count = 0;
            for (Driver driver : driverList) {
                statement.setInt(1, driver.getId());
                statement.setString(2, driver.getFullName());
                statement.setString(3, driver.getAddress());
                statement.setString(4, driver.getPhone());
                statement.setString(5, driver.getLevele());
                //Gọi addBatch()phương thức của PreparedStatementđối tượng.
                statement.addBatch();
                count++;
                if (count % 100 == 0 || count == driverList.size()) {
                    //Gọi executeBatch()phương thức để gửi một loạt các INSERTcâu lệnh đến máy chủ cơ sở dữ liệu PostgreSQL để thực thi.
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
