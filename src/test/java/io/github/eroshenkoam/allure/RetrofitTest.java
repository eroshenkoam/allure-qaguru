package io.github.eroshenkoam.allure;

import okhttp3.OkHttpClient;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitTest {

    @Test
    public void testGitHubRepos() throws Exception {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new AllureInterceptor());

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(httpClient.build())
                .build();

        final GitHubClient gitHub = retrofit.create(GitHubClient.class);

        gitHub.listRepos("eroshenkoam").execute();
        gitHub.listRepos("baev").execute();
        gitHub.listRepos("allure-framework").execute();
        gitHub.listRepos("sseliverstov").execute();
    }

}


