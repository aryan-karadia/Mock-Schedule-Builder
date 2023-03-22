import org.junit.Test;
import static org.junit.Assert.*;

public class TaskTest {

    @Test
    public void testTaskConstructor() throws Exception {
        try {
            Task testTask = new Task(1, 30, 2, "Kit feeding");
            assertNotNull("Task constructor does not create a Task object when given a valid task", testTask);
        }
        catch (Exception e) {
            fail("Task constructor threw an unexpected exception instead of creating new object.");
        }
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTaskConstructorWithInvalidTimeWindow() throws Exception {
        try {
            Task testTask = new Task(1, 30, 25, "Kit feeding");
        }
        catch (Exception e) {
            fail("Task constructor throws an unexpected error when creating a Task object with an invalid time window.")
        }
    }

}
