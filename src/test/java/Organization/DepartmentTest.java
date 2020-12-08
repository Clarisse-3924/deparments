package Organization;

import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentTest {
    @Test
    public void department_instantiatesCorrectly_true() {
        Department testdepartment = new Department("IT", "computer based",2);
        assertEquals(true, testdepartment instanceof Department);
    }

    @Test
    public void getdepartmentName_departmentInstantiatesWithName() {
        Department testdepartment = new Department("Admission", "recruitment",7);
        assertEquals("IT", testdepartment.getDepartmentname());
    }

}