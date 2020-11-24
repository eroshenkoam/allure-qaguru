package io.github.eroshenkoam.allure;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface GitHubClient {

    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> listRepos(@Path("user") String user);

}
