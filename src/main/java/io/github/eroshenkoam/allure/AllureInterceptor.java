package io.github.eroshenkoam.allure;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

public class AllureInterceptor implements Interceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();
        step("Открываем урл " + request.url().toString(), (step) -> {
            step.parameter("Method", request.method());
        });
        return chain.proceed(request);
    }
}
