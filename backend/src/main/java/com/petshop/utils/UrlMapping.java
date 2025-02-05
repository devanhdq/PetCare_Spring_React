package com.petshop.utils;

public class UrlMapping {
    public static final String API = "/api/v1";

    //    USER API
    public static final String USERS = API + "/users";
    public static final String REGISTER = "/register";
    public static final String UPDATE_USER_BY_ID = "/update/{userId}";
    public static final String DELETE_USER_BY_ID = "/delete/{userId}";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String GET_ALL_USERS = "/all";
}
