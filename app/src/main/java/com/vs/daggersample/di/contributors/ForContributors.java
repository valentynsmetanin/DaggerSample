package com.vs.daggersample.di.contributors;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Valentyn on 14.04.2018.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ForContributors{}