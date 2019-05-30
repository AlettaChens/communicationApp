package com.example.graduate.communicationunion.web;

import android.content.Context;

import com.example.graduate.communicationunion.bean.Book;
import com.example.graduate.communicationunion.bean.Comment;
import com.example.graduate.communicationunion.bean.Formu;
import com.example.graduate.communicationunion.bean.Info;
import com.example.graduate.communicationunion.bean.Like;
import com.example.graduate.communicationunion.bean.User;
import com.gizwits.energy.android.lib.web.base.BaseWebAPIManager;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;

import java.io.File;
import java.util.List;

import static com.example.graduate.communicationunion.BuildConfig.API_SERVER_URL;
import static com.example.graduate.communicationunion.web.WebApi.POST.ALLBOOK;
import static com.example.graduate.communicationunion.web.WebApi.POST.ALLCOMMENT;
import static com.example.graduate.communicationunion.web.WebApi.POST.ALLLIKE;
import static com.example.graduate.communicationunion.web.WebApi.POST.ALLTALK;
import static com.example.graduate.communicationunion.web.WebApi.POST.ALlINFO;
import static com.example.graduate.communicationunion.web.WebApi.POST.AVATER;
import static com.example.graduate.communicationunion.web.WebApi.POST.DELETEBOOK;
import static com.example.graduate.communicationunion.web.WebApi.POST.DELETETALK;
import static com.example.graduate.communicationunion.web.WebApi.POST.GETBOOKBYUSERID;
import static com.example.graduate.communicationunion.web.WebApi.POST.GETTALKBUNAME;
import static com.example.graduate.communicationunion.web.WebApi.POST.PUBLIDHBOOK;
import static com.example.graduate.communicationunion.web.WebApi.POST.PUBLIDHCOMMNET;
import static com.example.graduate.communicationunion.web.WebApi.POST.PUBLIDHTALK;
import static com.example.graduate.communicationunion.web.WebApi.POST.PULISHLIKE;
import static com.example.graduate.communicationunion.web.WebApi.POST.RECOMMEND;
import static com.example.graduate.communicationunion.web.WebApi.POST.SUGGESTION;
import static com.example.graduate.communicationunion.web.WebApi.POST.UPDATESTAR;
import static com.example.graduate.communicationunion.web.WebApi.POST.UPDATEUSER;
import static com.example.graduate.communicationunion.web.WebApi.POST.USERLOGIN;
import static com.example.graduate.communicationunion.web.WebApi.POST.USERREGISTER;

public class WebApiManager extends BaseWebAPIManager {

    private static WebApiManager instance;

    private Context mContext;

    private volatile boolean inited;


    private WebApiManager() {
    }

    static {
        instance = new WebApiManager();
    }

    public synchronized void init(Context context) {
        if (!inited) {
            mContext = context;
            inited = true;
        }
    }

    public static WebApiManager getInstance() {
        return instance;
    }

    /**
     * 用户登录
     *
     * @param nickname
     * @param password
     * @param callback
     * @return
     */
    public Callback.Cancelable login(String nickname, String password, Callback.CommonCallback<ApiResponse<User>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + USERLOGIN);
        requestParams.addQueryStringParameter("nickname", nickname);
        requestParams.addQueryStringParameter("password", password);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 用户注册
     *
     * @param nickname
     * @param password
     * @param phone
     * @param callback
     * @return
     */
    public Callback.Cancelable register(String nickname, String password, String phone, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + USERREGISTER);
        requestParams.addQueryStringParameter("nickname", nickname);
        requestParams.addQueryStringParameter("password", password);
        requestParams.addQueryStringParameter("phone", phone);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 更新图像
     *
     * @param avater
     * @param userId
     * @param callback
     * @return
     */
    public Callback.Cancelable updateAvatar(File avater, int userId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + AVATER);
        requestParams.addParameter("userId", userId);
        requestParams.addParameter("file", avater);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }


    public Callback.Cancelable updateUser(String nickname, String sex, String phone, String age, String address, int userId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + UPDATEUSER);
        requestParams.addParameter("userId", userId);
        requestParams.addQueryStringParameter("nickname", nickname);
        requestParams.addQueryStringParameter("sex", sex);
        requestParams.addQueryStringParameter("phone", phone);
        requestParams.addQueryStringParameter("age", age);
        requestParams.addQueryStringParameter("address", address);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 算法推荐接口
     *
     * @param userId
     * @param callback
     * @return
     */
    public Callback.Cancelable recommend(int userId, Callback.CommonCallback<ApiResponse<List<Info>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + RECOMMEND);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }


    /**
     * 显示某种类的全部资讯
     *
     * @param type
     * @param callback
     * @return
     */
    public Callback.Cancelable showInfoListByType(String type, Callback.CommonCallback<ApiResponse<List<Info>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + ALlINFO);
        requestParams.addParameter("type", type);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }


    /**
     * 意见反馈接口
     *
     * @param userAvater
     * @param userName
     * @param suggestion
     * @param callback
     * @return
     */


    public Callback.Cancelable Suggestion(String userAvater, String userName, String suggestion, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + SUGGESTION);
        requestParams.addParameter("userAvater", userAvater);
        requestParams.addParameter("userName", userName);
        requestParams.addParameter("suggestion", suggestion);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 发布图书
     *
     * @param file
     * @param title
     * @param price
     * @param description
     * @param phone
     * @param callback
     * @return
     */
    public Callback.Cancelable publishBook(File file, String title, float price, String description, String phone, int userId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + PUBLIDHBOOK);
        requestParams.addParameter("file", file);
        requestParams.addParameter("title", title);
        requestParams.addParameter("price", price);
        requestParams.addParameter("description", description);
        requestParams.addParameter("phone", phone);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 获取全部图书
     *
     * @param callback
     * @return
     */
    public Callback.Cancelable ALLBook(Callback.CommonCallback<ApiResponse<List<Book>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + ALLBOOK);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 获取某用户发布的图书信息
     *
     * @param userId
     * @param callback
     * @return
     */
    public Callback.Cancelable getBookByUserId(int userId, Callback.CommonCallback<ApiResponse<List<Book>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + GETBOOKBYUSERID);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 删除图书
     *
     * @param id
     * @param callback
     * @return
     */
    public Callback.Cancelable DeleteBook(int id, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + DELETEBOOK);
        requestParams.addParameter("id", id);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 评论接口
     *
     * @param comment_user
     * @param content
     * @param formuId
     * @param callback
     * @return
     */
    public Callback.Cancelable publishComment(String comment_user, String content, int formuId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + PUBLIDHCOMMNET);
        requestParams.addParameter("comment_user", comment_user);
        requestParams.addParameter("content", content);
        requestParams.addParameter("formuId", formuId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 获取全部的评论
     *
     * @param formuId
     * @param callback
     * @return
     */
    public Callback.Cancelable AllComment(int formuId, Callback.CommonCallback<ApiResponse<List<Comment>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + ALLCOMMENT);
        requestParams.addParameter("formuId", formuId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }


    /**
     * 发布论条
     *
     * @param title
     * @param content
     * @param anthorName
     * @param anthorAvatar
     * @param callback
     * @return
     */
    public Callback.Cancelable publishTalk(String title, String content, String anthorName, String anthorAvatar, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + PUBLIDHTALK);
        requestParams.addParameter("content", content);
        requestParams.addParameter("anthorName", anthorName);
        requestParams.addParameter("anthorAvatar", anthorAvatar);
        requestParams.addParameter("title", title);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }
    /**
     * 获取全部的论条
     *
     * @param callback
     * @return
     */
    public Callback.Cancelable AllTalk(int userId,Callback.CommonCallback<ApiResponse<List<Formu>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + ALLTALK);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 某用户发布的论条
     *
     * @param anthorName
     * @param callback
     * @return
     */
    public Callback.Cancelable getTalkByName(String anthorName, Callback.CommonCallback<ApiResponse<List<Formu>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + GETTALKBUNAME);
        requestParams.addParameter("anthorName", anthorName);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    public Callback.Cancelable deleteTalkById(int formuId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + DELETETALK);
        requestParams.addParameter("id", formuId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 更新赞赏
     *
     * @param formuId
     * @param updateType
     * @param callback
     * @return
     */
    public Callback.Cancelable updateStar(int formuId,int userId, String updateType, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + UPDATESTAR);
        requestParams.addParameter("updateType", updateType);
        requestParams.addParameter("userId", userId);
        requestParams.addParameter("forumId", formuId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }




    /**
     * 历史记录接口
     *
     * @param newId
     * @param userId
     * @param callback
     * @return
     */
    public Callback.Cancelable publishlike(int newId, int userId, Callback.CommonCallback<ApiResponse> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + PULISHLIKE);
        requestParams.addParameter("newId", newId);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }

    /**
     * 历史记录列表
     *
     * @param userId
     * @param callback
     * @return
     */
    public Callback.Cancelable allLike(int userId, Callback.CommonCallback<ApiResponse<List<Like>>> callback) {
        RequestParams requestParams = new RequestParams(API_SERVER_URL + ALLLIKE);
        requestParams.addParameter("userId", userId);
        return xRequest(HttpMethod.POST, requestParams, callback);
    }
}
