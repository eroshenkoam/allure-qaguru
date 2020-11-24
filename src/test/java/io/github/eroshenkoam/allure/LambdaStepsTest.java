package io.github.eroshenkoam.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class LambdaStepsTest {

    private final static String REPOSITORY = "eroshenkoam/allure-example";
    private final static String USER = "eroshenkoam";
    private final static int ISSUE_NUMBER = 68;

    @Test
    public void searchForIssue() {
        Allure.feature("Issues");
        Allure.story("Моя любимая история");
        Allure.parameter("Repository", REPOSITORY);

        step("Открываем главную страницу", () -> {
            final String url = "https://github.com";
            open(url);
            Allure.link("Тестинг", url);
        });
        step("Ищем репозиторий " + REPOSITORY, (step) -> {
            step.parameter("name", REPOSITORY);

            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим в репозиторий " + REPOSITORY, (step) -> {
            step.parameter("name", REPOSITORY);

            $(By.linkText(REPOSITORY)).click();
        });
        step("Переходим в раздел Issues", () -> {
            $(withText("Issues")).click();
        });
        step("Проверяем наличие Issue с номером " + ISSUE_NUMBER, (step) -> {
            step.parameter("number", REPOSITORY);

            $(withText("#" + ISSUE_NUMBER)).should(Condition.exist);
        });
    }

}
