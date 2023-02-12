import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryFormTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldSubmitForm() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id=\"city\"] .input__control").setValue("Москва");
        String testDate = generateDate(6);
        $("[data-test-id=\"date\"] .input__control").sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
        $("[data-test-id=\"date\"] .input__control").setValue(testDate);
        $("[data-test-id=\"name\"] .input__control").setValue("Маевская Ольга");
        $("[data-test-id=\"phone\"] .input__control").setValue("+79262222222");
        $("[data-test-id=\"agreement\"]").click();
        $(byText("Забронировать")).click();
        $(".notification__content").shouldHave(Condition.text("Встреча успешно забронирована на " + testDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }
}
