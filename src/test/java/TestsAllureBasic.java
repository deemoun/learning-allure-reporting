import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

@ExtendWith(AllureJunit5.class)
@Feature("Form Automation")
@Story("Fill, and Submit Form")
@Severity(SeverityLevel.CRITICAL)
@Issue("https://example.com/issues/123")
@TmsLink("https://example.com/test-cases/456")
@Link(name = "Documentation Link", url = "https://example.com/documentation")
public class TestsAllureBasic {

    @BeforeEach
    public void setUp() {
        // Set up browser configuration
        Configuration.browser = "firefox";
        Configuration.headless = true;
    }

    @Test
    @Description("Filling out the form")
    @Step("Fill in the form and submit")
    public void testFillForm() {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource("form.html")).getPath();
        open("file://" + path);

        fillName("John");
        fillLastName("Doe");
        fillEmail("john.doe@example.com");
        fillAddress("123 Main St, Anytown");
        submitForm();
    }

    @Step("Fill in name field with: {name}")
    public void fillName(String name) {
        Allure.addAttachment("Input Data: Name", name);
        $("#name").setValue(name);
    }

    @Step("Fill in last name field with: {lastName}")
    public void fillLastName(String lastName) {
        Allure.addAttachment("Input Data: Last Name", lastName);
        $("#lastName").setValue(lastName);
    }

    @Step("Fill in email field with: {email}")
    public void fillEmail(String email) {
        Allure.addAttachment("Input Data: Email", email);
        $("#notes").setValue(email);
    }

    @Step("Fill in address field with: {address}")
    public void fillAddress(String address) {
        Allure.addAttachment("Input Data: Address", address);
        $("#address").setValue(address);
    }

    @Step("Submit the form")
    public void submitForm() {
        $("button[type='submit']").click();
        Allure.addAttachment("Page Source After Submit", Selenide.webdriver().driver().source());
    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        Selenide.closeWebDriver();
    }
}