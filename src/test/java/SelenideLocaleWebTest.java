import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import data.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideLocaleWebTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "/";
        Configuration.pageLoadTimeout = 60000;
    }


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")),
                Arguments.of(Locale.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"))
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Для локали {0} должен отображаться список кнопок {1}")
    @DisplayName("check search results")
    void siteShouldContainsAllButtonsForGivenLocale(Locale locale, List<String> buttonsList) {
        open("https://selenide.org");
        $$("#languages a").find(Condition.text(locale.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(buttonsList));


    }
}
