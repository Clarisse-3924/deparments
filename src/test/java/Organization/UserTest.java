package Organization;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void user_instantiatesCorrectly_true() {
        User testuser = new User("news", "bluding house","finance");
        assertEquals(true, testuser instanceof User);
    }
    @Test
    public void getName_contentlInstantiatesWithTitle_news() {
        User testuser = new User("news", "building house","finance");
        assertEquals("news", testuser.getName());
    }
    @Test
    public void getPosition_contentInstantiatesWithcontent_String() {
        User testuser = new User("news", "building","finance");
        assertEquals("bluiding", testuser.getPosition());
    }
    @Test
    public void getDepartmentname_departmentnameInstantiatesWithDepartmentname_String() {
        User testuser = new User("news", "building","finance");
        assertEquals("bluiding", testuser.getDepartment());
    }

    @Test
    public void find_returnsnewsWithSameId_secondnews() {
        User firstuser= new User("news", "bluilding","finance");
        firstuser.save();
        User seconduser= new User("cow", "hebvore","finance");
        seconduser.save();
        assertEquals(GeneralNews.find(seconduser.getId()), seconduser);
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        User firstuser = new User("population", "live home","finace");
        firstuser.save();
        User seconduser = new User("population", "live home","finace");
        seconduser.save();
        assertEquals(true, User.all().get(0).equals(firstuser));
        assertEquals(true, User.all().get(1).equals(seconduser));
    }
    @Test
    public void save_assignsIdToObject() {
        User testuser = new User("Clarisse", "HR","finance");
        testuser.save();
        User saveduser = User.all().get(0);
        assertEquals(testuser.getId(), saveduser.getId());
    }


}