package com.meucontrole.api;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class RestApplication extends Application {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
