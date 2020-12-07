package DeoClasses;

import Organization.Department;
import Organization.User;

import java.util.List;

public interface Userdeo {
    void  add(User user);

    //read

    List<User> getAll();
    List<Department> getAllUserDepartments(int user_id);
    User findById(int id);

    //update

    //delete

    void clearAll();
}

