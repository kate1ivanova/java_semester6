
import org.example.annotations.AutoInjectable;

// Define a test class with dependencies to be injected
public class TestClass {
    @AutoInjectable
    private Service service;

    public Service getService() {
        return service;
    }
}