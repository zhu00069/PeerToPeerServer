package io.swagger.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.Set;
import java.util.HashSet;

import io.swagger.api.impl.OpportunityApiServiceImpl;
import io.swagger.api.impl.UserApiServiceImpl;
/**
 * Rest API Manager allows us to deploy each of the API classes 
 *
 */
@ApplicationPath("/")
public class RestApplication extends Application {


    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<Class<?>>();
        resources.add(OpportunityApiServiceImpl.class);
        resources.add(UserApiServiceImpl.class);

        return resources;
    }




}