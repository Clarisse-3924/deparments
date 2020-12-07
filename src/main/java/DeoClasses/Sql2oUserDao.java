package DeoClasses;

import Organization.Department;
import Organization.User;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Sql2oUserDao {

        private final Sql2o sql2o;

        public Sql2oUsersDao(Sql2o sql2o) {
            this.sql2o = sql2o;
        }

        @Override
        public void add(User user) {
            try (Connection con=sql2o.open()){
                String sql ="INSERT INTO staff (name,position,staff_role) VALUES (:name,:position,:staff_role) ";

                int id=(int) con.createQuery(sql,true)
                        .bind(user)
                        .executeUpdate()
                        .getKey();
                user.setId(id);


            }catch (Sql2oException e){
                System.out.println(e);
            }

        }

        @Override
        public List<User> getAll() {
            try (Connection con=sql2o.open()){
                String sql=("SELECT * FROM staff");
                return con.createQuery(sql)
                        .executeAndFetch(User.class);
            }
        }

        @Override
        public List<Department> getAllUserDepartments(int user_id) {
            List<Department> departments=new ArrayList<>();
            try (Connection con=sql2o.open()) {
                String sql = "SELECT department_id FROM users_departments WHERE user_id=:user_id";
                List<Integer> departmentIds = con.createQuery(sql)
                        .addParameter("user_id", user_id)
                        .executeAndFetch(Integer.class);

                for (Integer id : departmentIds) {
                    String userResults = "SELECT * FROM departments WHERE id=:id";
                    departments.add(con.createQuery(userResults)
                            .addParameter("id", id)
                            .executeAndFetchFirst(Department.class));

                }

                return departments;
            }
        }

        @Override
        public User findById(int id) {
            try (Connection con=sql2o.open()){
                String sql=("SELECT * FROM staff WHERE id=:id");
                return con.createQuery(sql)
                        .addParameter("id",id)
                        .executeAndFetchFirst(Users.class);
            }
        }


        @Override
        public void clearAll() {

            try (Connection con=sql2o.open()){
                String sql ="DELETE FROM staff ";
                con.createQuery(sql).executeUpdate();
                String sqlUsersDepartments="DELETE FROM users_departments";
                con.createQuery(sqlUsersDepartments).executeUpdate();


            }catch (Sql2oException e){
                System.out.println(e);
            }


        }
    }

