import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.*;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@ExtendWith(AllureJunit5.class)
@Feature("Form Automation")
@Story("Fill, and Submit Form")
@Severity(SeverityLevel.CRITICAL)
@Issue("https://example.com/issues/123")
@TmsLink("https://example.com/test-cases/456")
@Link(name = "Documentation Link", url = "https://example.com/documentation")
public class TestsAllureFull {

    @BeforeEach
    public void setUp() {
        // Set up browser configuration
        Configuration.browser = "firefox";
        Configuration.headless = true;
    }

    @Test
    public void testFillForm() {
        String path = Objects.requireNonNull(getClass().getClassLoader().getResource("form.html")).getPath();
        open("file://" + path);

        fillName("John");
        fillLastName("Doe");
        fillEmail("john.doe@example.com");
        fillAddress("123 Main St, Anytown");
        submitForm();
    }

    public void fillName(String name) {
        Allure.step("Fill in first name field");
        Allure.addAttachment("Input Data: Name", name);
        $("#name").setValue(name);
    }

    public void fillLastName(String lastName) {
        Allure.step("Fill in last name field");
        Allure.addAttachment("Input Data: Last Name", lastName);
        $("#lastName").setValue(lastName);
    }

    public void fillEmail(String email) {
        Allure.step("Fill in last email field");
        Allure.addAttachment("Input Data: Email", email);
        $("#notes").setValue(email);
    }

    public void fillAddress(String address) {
        Allure.step("Fill in last adress field");
        Allure.addAttachment("Input Data: Address", address);
        Allure.addAttachment("Input Data: User Agent", Selenide.webdriver().driver().getUserAgent());
        Allure.addAttachment("Website current URL", Selenide.webdriver().driver().url());
        $("#address").setValue(address);
    }

    public void submitForm() {
        Allure.step("Submit form");
        $("button[type='submit']").click();
        Allure.addAttachment("Page Source After Submit", Selenide.webdriver().driver().source());

        // Assert that the form was submitted successfully by checking for the message "Form is submitted!"
        Allure.step("Verify confirmation message", () -> {
            String confirmationMessage = $("#message").getText(); // Adjust the selector to match the confirmation message element
            assertTrue(confirmationMessage.contains("Form is submitted!"), "The confirmation message was not found!");
        });


        // Take the screenshot and get the absolute path (which might be a URI)
        String screenshotPath = Selenide.screenshot("submit_form_screenshot");

        // Check that the screenshot was generated
        assert screenshotPath != null;

        // Log the screenshot path for debugging
        System.out.println("Screenshot saved at: " + screenshotPath);

        // Convert the URI-like path to a valid file path
        File screenshotFile = null;
        try {
            URI screenshotURI = new URI(screenshotPath);
            screenshotFile = new File(screenshotURI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // Add the screenshot as an Allure attachment if the file exists
        if (screenshotFile != null && screenshotFile.exists()) {
            try (InputStream screenshotStream = new FileInputStream(screenshotFile)) {
                Allure.addAttachment("Screenshot After Submit", "image/png", screenshotStream, "png");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Screenshot file not found: " + screenshotPath);
        }

    }

    @AfterEach
    public void tearDown() {
        // Close the browser after each test
        Selenide.closeWebDriver();
    }
    }