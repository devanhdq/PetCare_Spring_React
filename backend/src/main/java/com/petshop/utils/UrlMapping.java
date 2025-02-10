package com.petshop.utils;

public class UrlMapping {
    public static final String API = "/api/v1";

    /*  =========================== USER API ==================================*/
    public static final String USERS = API + "/users";
    public static final String REGISTER = "/user";
    public static final String GET_ALL_USERS = "";
    public static final String GET_USER_BY_ID = "/user/{userId}";
    public static final String UPDATE_USER_BY_ID = "/user/{userId}";
    public static final String DELETE_USER_BY_ID = "/user/{userId}";


    /*  ============================== APPOINTMENT API ============================*/
    public static final String APPOINTMENTS = API + "/appointments";
    public static final String BOOK_APPOINTMENTS = "/appointment";
    public static final String GET_ALL_APPOINTMENTS = "";
    public static final String GET_APPOINTMENT_BY_ID = "/appointment/{appointmentId}";
    public static final String GET_APPOINTMENT_BY_APPOINTMENT_NO = "/appointmentNo/{appointmentNo}";
    public static final String DELETE_APPOINTMENT_BY_ID = "/appointment/{appointmentId}";
    public static final String UPDATE_APPOINTMENT_BY_ID = "/appointment/{appointmentId}";

    /*  ============================== PET API ============================*/
    public static final String PETS = API + "/pets";
    public static final String ADD_PET = "/pet";
    public static final String GET_ALL_PETS = "";
    public static final String GET_PET_BY_ID = "/pet/{petId}";
    public static final String DELETE_PET_BY_ID = "/pet/{petId}";
    public static final String UPDATE_PET_BY_ID = "/pet/{petId}";

    /*  ============================== PHOTO API ============================*/
    public static final String PHOTO = API + "/photos";
    public static final String UPLOAD_PHOTO = "/photo";
    public static final String UPDATE_PHOTO = "/photo/{photoId}";
    public static final String GET_PHOTO = "/photo/{photoId}";
    public static final String DELETE_PHOTO = "/photo/{photoId}";

    /*  ============================== REVIEW API ============================*/
    public static final String REVIEWS = API + "/reviews";
    public static final String SUBMIT_REVIEW = "/review";
    public static final String GET_REVIEW = "/review/{userId}";
    public static final String UPDATE_REVIEW = "/review/{reviewerId}";
    public static final String DELETE_REVIEW = "/review/{reviewerId}";
    public static final String GET_STARS = "/review/{veterinarianId}/stars";
}
