package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.parameter;
import static io.qameta.allure.Allure.step;

@Owner("eroshenkoam")
@Feature("Работа с задачами")
public class IssueTest {

    private static final int ISSUE = 12;
    private static final String BASE_URL = "https://github.com";
    private static final String REPOSITORY = "eroshenkoam/allure-example";

    private final BasicSteps steps = new BasicSteps();

    @Test
    @DisplayName("Пользователь должен иметь возможность найти Issue по номеру")
    public void shouldFindIssueByNumber() {
        link("GitHub", String.format("%s/%s", BASE_URL, REPOSITORY));
        parameter("Репозиторий", REPOSITORY);
        parameter("Номер задачи", ISSUE);

        step("Открываем главную страницу", () -> {
            open(BASE_URL);
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Переходим по ссылке репозитория " + REPOSITORY, () -> {
            $(By.linkText("eroshenkoam/allure-example")).click();
        });
        step("Открываем страницу с задачами", () -> {
            $(withText("Issues")).click();
        });
        step("Проверяем наличие задачи с номером " + ISSUE, () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }

    @Test
    @DisplayName("Пользователь должен найти отсутствующую Issue созданную через API")
    public void shouldNotFindIssueByMissingNumber() {
        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.openRepositoryByLink(REPOSITORY);
        steps.openIssuesPage();
        steps.shouldNotSeeIssueWithNumber(1111);
    }

}
