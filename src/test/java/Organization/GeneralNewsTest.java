package Organization;

import org.junit.Test;

import static org.junit.Assert.*;

public class GeneralNewsTest {
    @Test
    public void generalnews_instantiatesCorrectly_true() {
        GeneralNews testgeneralnews = new GeneralNews("news", "bluding house","finance");
        assertEquals(true, testgeneralnews instanceof GeneralNews);
    }
    @Test
    public void getTitle_contentlInstantiatesWithTitle_news() {
        GeneralNews testgeneralnews = new GeneralNews("news", "building house","finance");
        assertEquals("news", testgeneralnews.getTitle());
    }
    @Test
    public void getContent_contentInstantiatesWithcontent_String() {
        GeneralNews generalnews = new GeneralNews("news", "building","finance");
        assertEquals("bluiding", generalnews.getContent());
    }
    @Test
    public void getDepartmentname_departmentnameInstantiatesWithDepartmentname_String() {
        GeneralNews generalnews = new GeneralNews("news", "building","finance");
        assertEquals("bluiding", generalnews.getDepartmentname());
    }

    @Test
    public void find_returnsnewsWithSameId_secondnews() {
        GeneralNews firstnews = new GeneralNews("news", "bluilding","finance");
        firstnews.save();
        GeneralNews secondnews = new GeneralNews("cow", "hebvore","finance");
        secondnews.save();
        assertEquals(GeneralNews.find(secondnews.getId()), secondnews);
    }
    @Test
    public void all_returnsAllInstancesOfAnimal_true() {
        GeneralNews firstnews = new GeneralNews("population", "live home","finace");
        firstnews.save();
        GeneralNews secondnews = new GeneralNews("population", "live home","finace");
        secondnews.save();
        assertEquals(true, GeneralNews.all().get(0).equals(firstnews));
        assertEquals(true, GeneralNews.all().get(1).equals(secondnews));
    }
}