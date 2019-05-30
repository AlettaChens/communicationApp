package com.example.graduate.communicationunion.web;

import com.gizwits.energy.android.lib.base.AbstractConstantClass;
import com.gizwits.energy.android.lib.web.base.BaseWebAPI;

public class WebApi extends BaseWebAPI {

    public final static class POST extends AbstractConstantClass {
        public final static String USERLOGIN = "/user/login";
        public final static String USERREGISTER = "/user/register";
        public final static String NICKNAME = "/user/updatenick";
        public final static String PHONE = "/user/updatephone";
        public final static String UPDATEUSER = "/user/updateUser";
        public final static String ADDRESS = "/user/updateaddress";
        public final static String AGE = "/user/updateage";
        public final static String AVATER = "/user/userimageupload";
        public final static String ALlINFO = "/info/searchAllByType";
        public final static String RECOMMEND = "/recommender/publish";
        public final static String SUGGESTION = "/suggestion/publish";
        public final static String ALLBOOK = "/book/searchAll";
        public final static String PUBLIDHBOOK = "/book/publish";
        public final static String DELETEBOOK = "/book/deleteBookById";
        public final static String GETBOOKBYUSERID = "/book/getBookInfoById";
        public final static String PUBLIDHCOMMNET = "/comment/publish";
        public final static String ALLCOMMENT = "/comment/searchAll";
        public final static String DELETETALK = "/formu/deleteFormuById";
        public final static String UPDATESTAR = "/formu/updateStar";
        public final static String PUBLIDHTALK = "/formu/publish";
        public final static String ALLTALK = "/formu/searchAll";
        public final static String GETTALKBUNAME= "/formu/getFormuInfoByAuthorName";
        public final static String PULISHLIKE = "/save/publish";
        public final static String ALLLIKE = "/save/getLikeInfoByUserId";
    }
}
