import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class SimpleWebTest {

    @CsvSource(value = {
            "Selenide,                  Selenide: лаконичные и стабильные UI тесты на Java",
            "Allure framework git,      Allure Framework · GitHub"
    })
//    @CsvFileSource(resources = "/testdata/searchResultShouldContainExpectedResult.csv")
    @ParameterizedTest(name = "First search result for {0} should contain text: {1}")
    @DisplayName("check search results")
    void searchResultShouldContainExpectedTest(String testData, String expectedText) {
        open("https://ya.ru");
        $("#text").setValue(testData);
        $("button[type=submit]").click();
        $$("li.serp-item").first().shouldHave(Condition.text(expectedText));
    }
}

