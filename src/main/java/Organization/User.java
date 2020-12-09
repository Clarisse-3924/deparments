package Organization;

import org.sql2o.Connection;

import java.util.List;

public class User {

    private String name;
    private String position;
    private String department;
    private int id;
    public User(String name, String position, String department){
        this.name = name;
        this.position = position;
        this.department = department;
    }

    public String getName() {
        return name;
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
    public  void save() {
        try(Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO users (name, position, department) VALUES (:name, :position, :department)";
            this.id= (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .addParameter("position", this.position)
                    .addParameter("department", this.department)
                    .executeUpdate()
                    .getKey();

        }
    }
    public static List<User> all() {
        String sql = "SELECT * FROM users";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(User.class);
        }
    }
    public static User find(int id) {
        try(Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM users where id=:id";
            User user = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(User.class);
            return user;
        }
    }
}
