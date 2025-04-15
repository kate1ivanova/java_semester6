import org.example.Department;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void testDepartmentCreation() {
        Department department = new Department("Бизнес");

        assertNotNull(department.getID());
        assertEquals("Бизнес", department.getName());
    }

    @Test
    void testUniqueIds() {
        Department dept1 = new Department("Отдел 15");
        Department dept2 = new Department("Отдел 214");

        assertNotEquals(dept1.getID(), dept2.getID());
    }
}