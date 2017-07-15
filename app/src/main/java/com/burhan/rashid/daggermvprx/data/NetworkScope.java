package com.burhan.rashid.daggermvprx.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * The name of the scope can be anything
 * Created by Burhanuddin Rashid on 12-Jan-17.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface NetworkScope {
}
