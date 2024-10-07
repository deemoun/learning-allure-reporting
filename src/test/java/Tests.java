import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Use ordering by @Order annotation
public class Tests {

    @BeforeAll
    @Step("Setup: Initializing resources before all tests")
    public void setup() {
        initializeResources();
    }

    @Test
    @Order(1)
    @Description("This test verifies the addition of two numbers.")
    public void testAddition() {
        int result = add(2, 3);
        validateAddition(result);
    }

    @Test
    @Order(2)
    @Description("This test verifies the multiplication of two numbers.")
    public void testMultiplication() {
        int result = multiply(2, 3);
        validateMultiplication(result);
    }

    @AfterAll
    @Step("TearDown: Cleaning up resources after all tests")
    public void tearDown() {
        cleanupResources();
    }

    // Step to initialize resources
    @Step("Initializing resources")
    public void initializeResources() {
        Allure.step("Resources initialized.");
    }

    // Step to clean up resources
    @Step("Cleaning up resources")
    public void cleanupResources() {
        Allure.step("Resources cleaned up.");
    }

    @Step("Adding two numbers: {0} + {1}")
    public int add(int a, int b) {
        return a + b;
    }

    @Step("Multiplying two numbers: {0} * {1}")
    public int multiply(int a, int b) {
        return a * b;
    }

    @Step("Validating addition result: expected {1}, actual {0}")
    public void validateAddition(int result) {
        assertEquals(5, result, "Addition result is incorrect.");
    }

    @Step("Validating multiplication result: expected {1}, actual {0}")
    public void validateMultiplication(int result) {
        assertEquals(6, result, "Multiplication result is incorrect.");
    }
}
