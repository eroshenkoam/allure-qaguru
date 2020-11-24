package io.github.eroshenkoam.allure;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public class NamedBy extends By {

    private final By by;
    private String name;

    public NamedBy(final By by) {
        this.by = by;
    }

    public NamedBy as(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        return this.by.findElements(context);
    }

    public String toString() {
        return Optional.ofNullable(name).orElseGet(by::toString);
    }

    public static NamedBy css(final String value) {
        return new NamedBy(By.cssSelector(value));
    }

}