import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {

    @Test
    void shouldSubmitForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id=\"date\"] .input__control").setValue("17.02.2023");
        $("[data-test-id=\"name\"] .input__control").setValue("Маевская Ольга");
        $("[data-test-id=\"phone\"] .input__control").setValue("+79262222222");
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Забронировать")).click();
        $x("//*[contains(text(), \"Встреча\")]").shouldBe(visible, Duration.ofSeconds(15));
    }
}
