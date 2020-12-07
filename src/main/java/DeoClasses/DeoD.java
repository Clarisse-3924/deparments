package DeoClasses;

import Organization.Department;
import Organization.News;
import Organization.User;

import java.util.List;

public interface DeoD {
    //create
    void add(Department department);
    void addUserToDepartment(User user, Department department);
    //read

    List<Department> getAll();
    Department findById(int id);
    List<User> getAllUsersInDepartment(int department_id);
    List<News> getDepartmentNews(int id);
    //update
    //delete
    void clearAll();


}
