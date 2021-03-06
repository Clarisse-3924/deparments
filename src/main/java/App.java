import Organization.*;
import spark.ModelAndView;
import com.google.gson.Gson;

import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static Organization.Database.sql2o;
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

        Gson gson = new Gson();


        port(getHerokuAssignedPort());
        staticFileLocation("/public");





        get("/user/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = Department.all();
            model.put("departments", departments);
            return new ModelAndView(model, "newUserForm.hbs");
        }, new HandlebarsTemplateEngine());
        post("/user/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String position = request.queryParams("position");
            String department = request.queryParams("department");
            User newUser = new User(name,position,department);
            model.put("user", newUser);
            newUser.save();
            return new ModelAndView(model, "newUserForm.hbs");
        }, new HandlebarsTemplateEngine());
//      show all users in the db
        get("/allUsers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<User> user = User.all();
            model.put("user", user);
            return new ModelAndView(model, "User.hbs");
        }, new HandlebarsTemplateEngine());
//      show new department form
        get("/dept/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "newDepartmentform.hbs");
        }, new HandlebarsTemplateEngine());
//        post the department entered
        post("/dept/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String departmentname = request.queryParams("departmentname");
            String description = request.queryParams("description");
            Integer numberofemployee = Integer.parseInt(request.queryParams("numberofemployee"));
            Department newDept = new Department(departmentname,description,numberofemployee);
            model.put("department", newDept);
            newDept.save();
            return new ModelAndView(model, "newDepartmentform.hbs");
        }, new HandlebarsTemplateEngine());
//        show all departments in the db
        get("/allDepts", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = Department.all();
            model.put("departments", departments);
            return new ModelAndView(model, "Departments.hbs");
        }, new HandlebarsTemplateEngine());
//        form for general news posting
        get("/generalNews/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "generalNewsform.hbs");
        }, new HandlebarsTemplateEngine());
        post("/generalNews/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String content = request.queryParams("content");
            String departmentname = "General News";
            GeneralNews generalNews = new GeneralNews(title,content,departmentname);
            model.put("generalNews", generalNews);
            generalNews.save();
            return new ModelAndView(model, "generalNewsform.hbs");
        }, new HandlebarsTemplateEngine());
//        show all news
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<GeneralNews> generalNews = GeneralNews.all();
            List<NewsDepartment> departmentNews =NewsDepartment.all();
            model.put("Newsdepartment", departmentNews);
            model.put("generalNews", generalNews);
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());
//        show form to enter dept related news.
        get("/deptNews/new", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = Department.all();
            model.put("departments", departments);
            return new ModelAndView(model, "newDepartmentform.hbs");
        }, new HandlebarsTemplateEngine());
//        post the info got from form and save to db
        post("/deptNews/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String title = request.queryParams("title");
            String content = request.queryParams("content");
            String departmentname = request.queryParams("departmentname");
            NewsDepartment departmentNews = new NewsDepartment(title,content,departmentname);
            model.put("departmentNews", departmentNews);
            departmentNews.save();
            return new ModelAndView(model, "newDepartmentform.hbs");
        }, new HandlebarsTemplateEngine());



        /*post("/usernew/new", "application/json", (req, res) -> {
            User user = gson.fromJson(req.body(), User.class);
            User.save();
            res.status(201);
            res.type("application/json");
            return gson.toJson(user);
        });


        post("/departmentnew/new", "application/json", (req, res) -> {
            Department department = gson.fromJson(req.body(), Department.class);
            Department.save();
            res.status(201);
            res.type("application/json");
            return gson.toJson(department);
        });*/
    }

}