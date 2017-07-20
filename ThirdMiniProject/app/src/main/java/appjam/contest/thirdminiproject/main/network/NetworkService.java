package appjam.contest.thirdminiproject.main.network;


import java.util.ArrayList;

import appjam.contest.thirdminiproject.main.model.Place;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by user on 2016-12-31.
 */

public interface NetworkService {


    /**
     * 익명게시글 목록 가져오기 : GET, /posts
     * 익명게시글 상태보기: GET, /posts{id}  =>{id}라고하면 동적으로 변한다는것임
     * 익명게시글 등록하기: POST, /posts
     */
    @POST("/place/save")
    Call<ArrayList<Place>> save_And_getList(@Body Place place);



}

