package com.vs.daggersample.data.api;

import com.vs.daggersample.data.entity.Contributor;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Valentyn on 13.04.2018.
 */
public interface Api {

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<ArrayList<Contributor>> repoContributors(@Path("owner") String owner,
                                                        @Path("repo") String repo);

    @GET("/users/{login}")
    Observable<Contributor> getContributor(@Path("login") String login);

}
