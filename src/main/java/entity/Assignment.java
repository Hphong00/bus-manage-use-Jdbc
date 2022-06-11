package entity;

//import comaprator.MyComaprator;
import connectDatabase.ConnectData;
import entity.detail.DetailRoute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;



public class Assignment {
    static Map<Driver, ArrayList<DetailRoute>> linkedHashMap = new LinkedHashMap<Driver, ArrayList<DetailRoute>>();
    private static int idAtuto = 100000;
    private static int id;

    public Assignment() {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addAssignmentToMap(Driver driver, ArrayList<DetailRoute> detailRouteArrayList) throws SQLException {
        linkedHashMap.put(driver, detailRouteArrayList);
        insertAssinment(linkedHashMap);
    }

    public void showAssignmentMap() {
        for (Map.Entry<Driver, ArrayList<DetailRoute>> entry : linkedHashMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "\n");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return Objects.equals(linkedHashMap, that.linkedHashMap);
    }

    public void sortNameDriver() {
        Set<Map.Entry<Driver, ArrayList<DetailRoute>>> entrySet = linkedHashMap.entrySet();

        List<Map.Entry<Driver, ArrayList<DetailRoute>>> list = new ArrayList<>(entrySet);

        Collections.sort(list, (o1, o2) -> o1.getKey().getFullName().compareTo(o2.getKey().getFullName()));

        list.forEach(s -> {
            System.out.println(s.getKey() + "\t" + s.getValue());
        });
        showAssignmentMap();
    }

    public void sortNumberRoute() {
        Set<Map.Entry<Driver, ArrayList<DetailRoute>>> entrySet = linkedHashMap.entrySet();

        List<Map.Entry<Driver, ArrayList<DetailRoute>>> list = new ArrayList<>(entrySet);

        Collections.sort(list, new Comparator<Map.Entry<Driver, ArrayList<DetailRoute>>>() {
            @Override
            public int compare(Map.Entry<Driver, ArrayList<DetailRoute>> o1, Map.Entry<Driver, ArrayList<DetailRoute>> o2) {
//                return (o1.getValue());
                return 0;
            }
        });
    }

    public static void insertAssinment(Map<Driver, ArrayList<DetailRoute>> linkedHashMap) throws SQLException {
        String SQL = "INSERT INTO assignment(id,driver_id,driver_fullName,driver_address,driver_phone,driver_levele,route_id,route_distance,route_NumberOfStops,numberOfTurns) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?)";
        try (
                Connection conn = ConnectData.connect();
                PreparedStatement statement = conn.prepareStatement(SQL)) {
            int count = 0;
            id = idAtuto++;
            Assignment assignment = new Assignment();
            for (int i = 0; i < linkedHashMap.size(); i++) {
                try {
                    statement.setInt(1,assignment.getId());
                    for (Driver driver : linkedHashMap.keySet()) {
                        statement.setInt(2, driver.getId());
                        statement.setString(3, driver.getFullName());
                        statement.setString(4, driver.getAddress());
                        statement.setString(5, driver.getPhone());
                        statement.setString(6, driver.getLevele());
                    }
                    for (ArrayList<DetailRoute> arrayList : linkedHashMap.values()) {
                        for (DetailRoute detai: arrayList
                             ) {
                            statement.setInt(7, detai.getRoute().getId());
                            statement.setDouble(8, detai.getRoute().getDistance());
                            statement.setInt(9, detai.getRoute().getNumberOfStops());
                            statement.setInt(10, detai.getNumberOfTurns());
                        }
                    }
                    statement.addBatch();
                    count++;
                    if (count % 100 == 0 || count == linkedHashMap.size()) {
                        statement.executeBatch();
                    }
                conn.close();
                statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}