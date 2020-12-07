import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        staticFileLocation("/public");
//      Making a home pathway
//        get("/",(request, response) ->{
//            Map<String, Object> model = new HashMap<>();
//            return new ModelAndView(model, "news.hbs");
//        }, new HandlebarsTemplateEngine() );
//        create a new user form
        get("/user/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Departments> departments = Departments.all();
            model.put("departments", departments);
            return new ModelAndView(model, "newUser-form.hbs");
        }, new HandlebarsTemplateEngine());
//      post the user entered
        post("/user/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String fName = request.queryParams("fName");
            String sName = request.queryParams("sName");
            String position = request.queryParams("position");
            String department = request.queryParams("department");
            Users newUser = new Users(fName,sName,position,department);
            model.put("user", newUser);
            newUser.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//      show all users in the db
        get("/allUsers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Users> users = Users.all();
            model.put("user", users);
            return new ModelAndView(model, "allUsers.hbs");
        }, new HandlebarsTemplateEngine());
//      show new department form
        get("/dept/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newDept-form.hbs");
        }, new HandlebarsTemplateEngine());
//        post the department entered
        post("/dept/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String dName = request.queryParams("dName");
            String description = request.queryParams("description");
            Integer employeeNo = Integer.parseInt(request.queryParams("employeeNo"));
            Departments newDept = new Departments(dName,description,employeeNo);
            model.put("department", newDept);
            newDept.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//        show all departments in the db
        get("/allDepts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Departments> departments = Departments.all();
            model.put("departments", departments);
            return new ModelAndView(model, "allDepts.hbs");
        }, new HandlebarsTemplateEngine());
//        form for general news posting
        get("/generalNews/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "generalNews-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/generalNews/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String content = request.queryParams("content");
            String dName = "General News";
            GeneralNews generalNews = new GeneralNews(title,content,dName);
            model.put("generalNews", generalNews);
            generalNews.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
//        show all news
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<GeneralNews> generalNews = GeneralNews.all();
            List<DepartmentNews> departmentNews = DepartmentNews.all();
            model.put("departmentNews", departmentNews);
            model.put("generalNews", generalNews);
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());
//        show form to enter dept related news.
        get("/deptNews/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Departments> departments = Departments.all();
            model.put("departments", departments);
            return new ModelAndView(model, "deptNews-form.hbs");
        }, new HandlebarsTemplateEngine());
//        post the info got from form and save to db
        post("/deptNews/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String content = request.queryParams("content");
            String dName = request.queryParams("dName");
            DepartmentNews departmentNews = new DepartmentNews(title,content,dName);
            model.put("departmentNews", departmentNews);
            departmentNews.save();
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        //      show all users in individual department
//        get("/allUsersDept", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            List<Users> userInDept = Departments.getUsers();
//            model.put("userInDept", userInDept);
//            return new ModelAndView(model, "allUsersDept.hbs");
//
//        }, new HandlebarsTemplateEngine());
    }
}