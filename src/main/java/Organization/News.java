import org.sql2o.Connection;

public abstract class News {
    public String title;
    public String content;
    public String dName;
    public int id;
    public String type;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDName() {
        return dName;
    }
    public int getId() {
        return id;
    }
    public void save() {
        try(Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO news (title, content,dName, type) VALUES (:title, :content, :dName, :type)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("title", this.title)
                    .addParameter("content", this.content)
                    .addParameter("dName", this.dName)
                    .addParameter("type", this.type)
                    .executeUpdate()
                    .getKey();
        }
    }
}