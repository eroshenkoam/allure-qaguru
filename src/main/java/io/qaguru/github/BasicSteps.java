package io.qaguru.github;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.link;
import static io.qameta.allure.Allure.parameter;

public class BasicSteps {

    private static final String BASE_URL = "https://github.com";

    @Step("Открываем главную страницу")
    public void openMainPage() {
        open(BASE_URL);
    }

    @Step("Ищем репозиторий")
    public void searchForRepository(final String name) {
        link("GitHub", String.format("%s/%s", BASE_URL, name));
        parameter("Репозиторий", name);

        $(".header-search-input").click();
        $(".header-search-input").sendKeys(name);
        $(".header-search-input").submit();
    }

    @Step("Переходим по ссылке репозитория")
    public void openRepositoryByLink(final String name) {
        $(By.linkText(name)).click();
    }

    @Step("Открываем страницу с задачами")
    public void openIssuesPage() {
        $(withText("Issues")).click();
    }

    @Step("Проверяем наличие задачи с номером")
    public void shouldSeeIssueWithNumber(final int number) {
        parameter("Номер задачи", number);

        $(withText("#" + number)).should(Condition.exist);
    }

    @Step("Проверяем отсутствие задачи с номером")
    public void shouldNotSeeIssueWithNumber(final int number) {
        parameter("Номер задачи", number);

        $(withText("#" + number)).shouldNot(Condition.exist);
    }

}
