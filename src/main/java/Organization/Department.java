package Organization;


import org.eclipse.jetty.server.Authentication;
import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String departmentname;
    private String description;
    private int NumberOfEmployee;
    private int id;
    public Department(String departmentname, String description, int NumberOfEmployee){
        this.departmentname = departmentname;
        this.description = description;
        this.NumberOfEmployee = NumberOfEmployee;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfEmployee() {
        return NumberOfEmployee;
    }
    public int getId() {
        return id;
    }
    public  void save() {
        try(Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO departments (departmentname, description, NumberOfEmployee) VALUES (:departmentname, :description, :NumberOfEmployee)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("departmentname", this.departmentname)
                    .addParameter("description", this.description)
                    .addParameter("NumberOfEmployee", this.NumberOfEmployee)
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
            String sql = "SELECT * FROM users where department=:departmentname";
            return con.createQuery(sql)
                    .addParameter("departmentname", this.departmentname)
                    .executeAndFetch(Authentication.User.class);
        }
    }
    public List<Object> getNews() {
        List<Object> allNews = new ArrayList<Object>();

        try(Connection con = Database.sql2o.open()) {
            String sqlGeneral = "SELECT * FROM news where dName=:dName AND type='General';";
            List<GeneralNews> generalNews = con.createQuery(sqlGeneral)
                    .addParameter("departmentname", this.departmentname)
                    .executeAndFetch(GeneralNews.class);
            allNews.addAll(generalNews);

            String sqlDept = "SELECT * FROM news news where departmentname=:departmentname AND type='Department';";
            List<NewsDepartment > departmentNews = con.createQuery(sqlDept)
                    .addParameter("departmentname", this.departmentname)
                    .executeAndFetch(NewsDepartment .class);
            allNews.addAll(departmentNews);
        }

        return allNews;
    }
}