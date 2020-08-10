package io.qaguru.github;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;

public class NamedBy extends By {

    private final By origin;
    private String name;

    public NamedBy(final By origin) {
        this.origin = origin;
    }

    public NamedBy as(final String name) {
        this.name = name;
        return this;
    }

    @Override
    public List<WebElement> findElements(final SearchContext context) {
        return origin.findElements(context);
    }

    @Override
    public String toString() {
        return Optional.ofNullable(name).orElseGet(origin::toString);
    }

    public static NamedBy css(final String css) {
        return new NamedBy(By.cssSelector(css));
    }
    public static NamedBy named(final By origin) {
        return new NamedBy(origin);
    }



}
