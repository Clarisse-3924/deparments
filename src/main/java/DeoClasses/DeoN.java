package DeoClasses;

import Organization.News;
import Organization.NewsDepartment;

import java.util.List;

public interface DeoN {
    void addNews(News news);
    void addDepartmentNews(NewsDepartment department_news);

    //read
    List<News> getAll();

    News findById(int id);

    //update

    //delete

    void clearAll();
}


