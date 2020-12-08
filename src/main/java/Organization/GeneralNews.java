package Organization;

import org.sql2o.Connection;

import java.util.List;

public class GeneralNews extends News {
    public static final String DATABASE_TYPE = "General";

    public GeneralNews(String title, String content, String departmentName) {
        this.title = title;
        this.content = content;
        this.departmentName = departmentName;
        type = DATABASE_TYPE;
    }

    public static List<GeneralNews> all() {
        String sql = "SELECT * FROM news WHERE type='General';";
        try (Connection con = Database.sql2o.open()) {
            return con.createQuery(sql).throwOnMappingFailure(false).executeAndFetch(GeneralNews.class);
        }
    }

    public static GeneralNews find(int id) {
        try (Connection con = Database.sql2o.open()) {
            String sql = "SELECT * FROM news where id=:id";
            GeneralNews news = con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(GeneralNews.class);
            return news;
        }
    }

}