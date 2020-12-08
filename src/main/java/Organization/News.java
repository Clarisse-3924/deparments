package Organization;

import org.sql2o.Connection;

public abstract class News  {
    public String title;
    public String content;
    public String departmentName ;
    public int id;
    public String type;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDepartmentName () {
        return departmentName ;
    }
    public int getId() {
        return id;
    }
    public void save() {
        try(Connection con = Database.sql2o.open()) {
            String sql = "INSERT INTO news (title, content,departmentName , type) VALUES (:title, :content, :departmentName, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("content", this.content)
                    .addParameter("departmentName ", this.departmentName )
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
}