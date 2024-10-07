import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(AllureJunit5.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Use ordering by @Order annotation
public class UpdatedTests {

    @BeforeAll
    @Step("Setup: Initializing resources before all tests")
    public static void setup() {
        initializeResources();
    }

    @Test
    @Order(1)
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies the addition of two numbers.")
    public void testAddition() {
        int result = add(2, 3);
        validateAddition(result);
    }

    @Test
    @Order(2)
    @Severity(SeverityLevel.MINOR)
    @Description("This test verifies the multiplication of two numbers.")
    public void testMultiplication() {
        int result = multiply(2, 3);
        validateMultiplication(result);
    }

    
    @Test
    @Order(3)
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test verifies the subtraction of two numbers.")
    public void testSubtraction() {
        int result = subtract(5, 3);
        validateSubtraction(result);
    }

    @Step("Subtracting two numbers: {0} - {1}")
    public int subtract(int a, int b) {
        return a - b;
    }

    @Step("Validating subtraction result: expected {1}, actual {0}")
    public void validateSubtraction(int result) {
        assertEquals(2, result, "Subtraction result is incorrect.");
    }

    @AfterAll
    @Step("TearDown: Cleaning up resources after all tests")
    public static void tearDown() {
        cleanupResources();
    }

    // Step to initialize resources
    @Step("Initializing resources")
    public static void initializeResources() {
        Allure.step("Resources initialized.");
    }

    // Step to clean up resources
    @Step("Cleaning up resources")
    public static void cleanupResources() {
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
