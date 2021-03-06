package com.sombre.shop.controllers.filters;

import com.google.gson.Gson;
import com.sombre.shop.controllers.adminsCtrl.AdminsCtrl;
import com.sombre.shop.controllers.adminsCtrl.blacklistCtrl.BlacklistCtrl;
import com.sombre.shop.controllers.usersCtrl.UsersCtrl;
import com.sombre.shop.models.pojo.dto.UniqueIdDto;
import com.sombre.shop.models.pojo.entity.Admins;
import com.sombre.shop.models.pojo.entity.Users;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import org.eclipse.jetty.http.HttpHeader;
import spark.Filter;

import static spark.Spark.halt;

/**
 * Created by inna on 15.02.17.
 */
public class BeforeFilter {

    static Gson gson = new Gson();

    private static Users getUserByAccessToken(String accessToken) {
        return UsersCtrl.getUserDaoService().getUserByAccessToken(accessToken);
    }

    @Getter
    private static final Filter checkSession = (request, response) -> {

    };

    @Getter
    private static final Filter checkAuthorizationAndBlacklist = (request, response) -> {

        if (request.headers(HttpHeader.AUTHORIZATION.asString()) == null)
            halt(401, "Exception: You are unauthorized user!");

        Users user = getUserByAccessToken(request.headers(HttpHeader.AUTHORIZATION.asString()));
        if (user == null)
            halt(401, "Exception: You are unauthorized user! user==null");

        if (!request.session().attribute("AccessToken").equals(request.headers(HttpHeader.AUTHORIZATION.asString()))) {
            halt(401, "Exception: Please, login to your account!");
        }

        if (BlacklistCtrl.getBlacklistDaoService()
                .getFILTERBlacklistByUserId(user.getUniqueid()) != null)
            halt(401, "Exception: Administrators added your account to blacklist.");
    };

    @Getter
    private static final Filter ownerChecker = (request, response) -> {

        Users owner = getUserByAccessToken(request.headers(HttpHeader.AUTHORIZATION.asString()));
        ObjectConverterValidator.nullChecker(owner);

        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(owner.getUniqueid());
        if (admin == null || admin.getDegree() > 1) {
            halt(401, "Exception: You are not owner!");
        }
    };

    @Getter
    private static final Filter checkRealUser = (request, response) -> {

        UniqueIdDto user = gson.fromJson(request.body(), UniqueIdDto.class);
        ObjectConverterValidator.nullChecker(user);

        Users userFromDb = getUserByAccessToken(request.headers(HttpHeader.AUTHORIZATION.asString()));
        ObjectConverterValidator.nullChecker(userFromDb);

        if (user.getUniqueid() != userFromDb.getUniqueid()) {
            halt(401, "Exception: You can't use someone else's account!");
        }

    };

    @Getter
    private static final Filter adminChecker = (request, response) -> {

        Users userFromDb = getUserByAccessToken(request.headers(HttpHeader.AUTHORIZATION.asString()));
        ObjectConverterValidator.nullChecker(userFromDb);

        Admins admin = AdminsCtrl.getAdminDaoService().getAdminByUserId(userFromDb.getUniqueid());

        if (admin == null) {
            halt(401, "Exception: You are not admin!");
        }
    };


}
