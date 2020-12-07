import org.sql2o.Connection;

import java.util.List;

public class User {
    private String fName;
    private String sName;
    private String position;
    private String department;
    private int id;
    public User(String fName, String sName, String position, String department){
        this.fName = fName;
        this.sName = sName;
        this.position = position;
        this.department = department;
    }

    public String getFName() {
        return fName;
    }

    public String getSName() {
        return sName;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }
    public int getId() {
        return id;
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO users (fName, sName, position, department) VALUES (:fName, :sName, :position, :department)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("fName", this.fName)
                    .addParameter("sName", this.sName)
                    .addParameter("position", this.position)
                    .addParameter("department", this.department)
                    .executeUpdate()
                    .getKey();

        }
    }
    public static List<User> all() {
        String sql = "SELECT * FROM users";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }
    public static User find(int id) {
        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM users where id=:id";
            User user = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
            return user;
        }
    }
}
