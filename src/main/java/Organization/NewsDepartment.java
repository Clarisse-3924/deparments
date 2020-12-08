package Organization;


import org.sql2o.Connection;

import java.util.List;


public class NewsDepartment extends News {
    public static final String DATABASE_TYPE = "Department";

    public NewsDepartment(String title, String content, String departmentName){
        this.title = title;
        this.content = content;
        this.departmentName = departmentName;
        type = DATABASE_TYPE;
    }
    public static List<NewsDepartment> all() {
        String sql = "SELECT * FROM news WHERE type='Department';";
        try(Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(NewsDepartment.class);
        }
    }
    public static NewsDepartment find(int id) {
        try(Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM news where id=:id";
            NewsDepartment news = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(NewsDepartment.class);
            return news;
        }
    }
}
