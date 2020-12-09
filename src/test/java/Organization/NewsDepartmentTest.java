package Organization;

import org.junit.Test;

import static org.junit.Assert.*;

public class NewsDepartmentTest {
    @Test
    public void Newsdepartment_instantiatesCorrectly_true() {
        NewsDepartment testnewsdepart = new NewsDepartment("news", "bluding house","finance");
        assertEquals(true, testnewsdepart instanceof NewsDepartment);
    }
    @Test
    public void find_returnsnewsWithSameId_secondnews() {
        NewsDepartment firstnews = new NewsDepartment("news", "bluilding","finance");
        firstnews.save();
        NewsDepartment secondnews = new NewsDepartment("cow", "hebvore","finance");
        secondnews.save();
        assertEquals(NewsDepartment.find(secondnews.getId()), secondnews);
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        NewsDepartment firstnews = new NewsDepartment("population", "live home","finace");
        firstnews.save();
        NewsDepartment secondnews = new NewsDepartment("population", "live home","finace");
        secondnews.save();
        assertEquals(true, NewsDepartment.all().get(0).equals(firstnews));
        assertEquals(true, NewsDepartment.all().get(1).equals(secondnews));
    }
}