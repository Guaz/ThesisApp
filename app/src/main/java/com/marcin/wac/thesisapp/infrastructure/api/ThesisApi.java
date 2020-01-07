package com.marcin.wac.thesisapp.infrastructure.api;

import com.marcin.wac.thesisapp.models.ThesisModel;
import com.marcin.wac.thesisapp.models.UserModel;
import com.marcin.wac.thesisapp.models.body.LoginBody;
import com.marcin.wac.thesisapp.models.body.RegisterBody;
import com.marcin.wac.thesisapp.models.body.ThesisBody;
import com.marcin.wac.thesisapp.models.responses.DepartmentsResponse;
import com.marcin.wac.thesisapp.models.responses.GetThesisListResponse;
import com.marcin.wac.thesisapp.models.responses.GetUserListResponse;
import com.marcin.wac.thesisapp.models.responses.LoginResponse;
import com.marcin.wac.thesisapp.models.responses.UniversitiesResponse;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ThesisApi {

    @GET("/test/hello")
    Single<ResponseBody> chceckApi();

    // register- login- logout
    @POST("/api/users/register")
    Single<UserModel> register(@Body RegisterBody registerBody);

    @POST("/api/session")
    Single<LoginResponse> login(@Body LoginBody loginBody);

    @DELETE("/api/session")
    Single<ResponseBody> logout();

    @DELETE("/api/users/{email}")
    Single<ResponseBody> deleteAccount(@Path("email") String email);

    // users

    @GET("/api/users/{email}")
    Single<UserModel> getUserDetails(@Header ("Authorization") String token, @Path("email") String email);

    @GET("/api/users/students")
    Single<GetUserListResponse> getStudentList();

    @GET("/api/users/promoters")
    Single<GetUserListResponse> getPromoterList();

    @GET("/api/users/students/department/{department}")
    Single<GetUserListResponse> getStudentOfDeparmentList(@Path("department") String department);

    @GET("/api/users/promoters/department/{department}")
    Single<GetUserListResponse> getPromoterOfDeparmentList(@Path("department") String department);


    @GET("/api/users/students/university/{university}")
    Single<GetUserListResponse> getStudentOfUniversityList(@Path("university") String university);


    @GET("/api/users/promoters/university/{university}")
    Single<GetUserListResponse> getPromoterOfUniversityList(@Path("university") String university);

    // facilities
    @GET("/api/universities")
    Single<UniversitiesResponse> getUniversities();

    @GET("/api/departments")
    Single<DepartmentsResponse> getDepartments();

    //thesis
    @POST("/api/thesis")
    Single<ResponseBody> postThesis(@Body ThesisBody thesisBody);

    @DELETE("api/thesis/{id}")
    Single<ResponseBody> deleteThesis(@Path("id") Long id);

    @GET("/api/thesis/{id}")
    Single<ThesisModel> getThesisById(@Path("id") Long id);

    @GET("/api/thesis/promoter/{email}")
    Single<GetThesisListResponse> getThesisListByPromoterEmail(@Path("email") String email);

    @GET("/api/thesis/student/{email}")
    Single<ThesisModel> getThesisByStudentEmail(@Path("email") String email);

    @GET("/api/thesis/department/{department}")
    Single<GetThesisListResponse> getThesisListByDepartment(@Path("department") String department);

    @GET("/api/thesis/university/{university}")
    Single<GetThesisListResponse> getThesisListByUniversity(@Path("university") String university);

    @GET("/api/thesis/available")
    Single<GetThesisListResponse> getAvailableThesisList();

    // reserve- occupy
    @PUT("/api/thesis/{id}/reserve/{studentEmail}")
    Single<ResponseBody> reserveThesisForStudent(@Path("id") Long thesisId,
                                                 @Path("studentEmail") String studentEmail);

    @PUT("/api/thesis/{id}/occupy/{studentEmail}")
    Single<ResponseBody> occupyThesisForStudent(@Path("id") Long thesisId,
                                                 @Path("studentEmail") String studentEmail);

    @PUT("/api/thesis/{id}/unreserve")
    Single<ResponseBody> unreserveThesis(@Path("id") Long thesisId);

    @PUT("/api/thesis/{id}/unoccupy")
    Single<ResponseBody> unoccupyThesis(@Path("id") Long thesisId);
}
