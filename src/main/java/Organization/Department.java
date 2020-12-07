package Organization;


import org.eclipse.jetty.server.Authentication;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String dName;
    private String description;
    private int employeeNo;
    private int id;
    public Department(String dName, String description, int employeeNo){
        this.dName = dName;
        this.description = description;
        this.employeeNo = employeeNo;
    }

    public String getDName() {
        return dName;
    }

    public String getDescription() {
        return description;
    }

    public int getEmployeeNo() {
        return employeeNo;
    }
    public int getId() {
        return id;
    }
    public void save() {
        try(Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO departments (dName, description, employeeNo) VALUES (:dName, :description, :employeeNo)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("dName", this.dName)
                    .addParameter("description", this.description)
                    .addParameter("employeeNo", this.employeeNo)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Department> all() {
        String sql = "SELECT * FROM departments";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Department.class);
        }
    }
    public static Department find(int id) {
        try(Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM departments where id=:id";
            Department department = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
            return department;
        }
    }
    public List<Authentication.User> getUsers() {
        try(Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM users where department=:dName";
            return con.createQuery(sql)
                    .addParameter("dName", this.dName)
                    .executeAndFetch(Authentication.User.class);
        }
    }
    public List<Object> getNews() {
        List<Object> allNews = new ArrayList<Object>();

        try(Connection con = Database.sql2o.open()) {
            String sqlGeneral = "SELECT * FROM news where dName=:dName AND type='General';";
            List<GeneralNews> generalNews = con.createQuery(sqlGeneral)
                    .addParameter("dName", this.dName)
                    .executeAndFetch(GeneralNews.class);
            allNews.addAll(generalNews);

            String sqlDept = "SELECT * FROM news news where dName=:dName AND type='Department';";
            List<NewsDepartment > departmentNews = con.createQuery(sqlDept)
                    .addParameter("dName", this.dName)
                    .executeAndFetch(NewsDepartment .class);
            allNews.addAll(departmentNews);
        }

        return allNews;
    }
}