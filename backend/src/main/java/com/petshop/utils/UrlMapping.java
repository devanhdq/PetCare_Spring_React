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


    //    APPOINTMENT API
    public static final String APPOINTMENTS = API + "/appointments";
    public static final String BOOK_APPOINTMENTS = "/create";
    public static final String GET_ALL_APPOINTMENTS = "/all";
    public static final String GET_APPOINTMENT_BY_ID = "/appointment/{appointmentId}";
    public static final String GET_APPOINTMENT_BY_APPOINTMENT_NO = "/appointmentNo/{appointmentNo}";
    public static final String DELETE_APPOINTMENT_BY_ID = "/appointment/delete/{appointmentId}";
    public static final String UPDATE_APPOINTMENT_BY_ID = "/appointment/update/{appointmentId}";
}
