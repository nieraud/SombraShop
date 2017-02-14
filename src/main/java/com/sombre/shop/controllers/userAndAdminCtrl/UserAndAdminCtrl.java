package com.sombre.shop.controllers.userAndAdminCtrl;

import com.google.gson.Gson;
import com.sombre.shop.models.factory.DaoServiceFactory;
import com.sombre.shop.models.pojo.dto.user.input.UserRegistration;
import com.sombre.shop.models.pojo.dto.user.input.returnPassAndSalt.HashPasswordAndSalt;
import com.sombre.shop.models.pojo.dto.user.output.UserForAddingToDB;
import com.sombre.shop.models.repositories.userRepository.UserRepository;
import com.sombre.shop.utils.dateTimeParser.DateTimeParser;
import com.sombre.shop.utils.security.UserSecurity;
import com.sombre.shop.utils.validator.ObjectConverterValidator;
import lombok.Getter;
import lombok.NonNull;
import org.eclipse.jetty.http.HttpStatus;
import org.joda.time.DateTime;
import org.sql2o.Sql2oException;
import spark.Route;

/**
 * Created by inna on 11.02.17.
 */
public class UserAndAdminCtrl {

    private static final UserRepository userDaoService = DaoServiceFactory.getUserService();
    private static Gson gson = new Gson();


    // User's part

    @Getter
    private static final Route registrationUser = (request, response) -> {

        UserRegistration user = gson.fromJson(request.body(), UserRegistration.class);
        ObjectConverterValidator.nullChecker(user);

        HashPasswordAndSalt security = UserSecurity.generateHashPassword(user.getPassword());

        DateTime date = DateTimeParser.parse(user.getBirthday(), "yyyy-MM-dd");

        UserForAddingToDB endingUser = new UserForAddingToDB(user.getFirstname(),
                user.getLastname(),
                date,
                user.getPhonenumber(),
                user.getUseremail(),
                security.getPassword(),
                security.getSalt());
        ObjectConverterValidator.nullChecker(endingUser);

        if (userDaoService.registration(endingUser)) {

            response.status(HttpStatus.OK_200);
            return "true";

        } else throw new Sql2oException();

    };

    @Getter
    private static final Route authorization = (request, response) -> {

        /*AuthUser user = gson.fromJson(request.body(), AuthUser.class);
        Users userFromDB = userDaoService.getUserByUserEmail(new UserIdentification(user.getUsername()));

        boolean state = BCrypt.checkpw(user.getPassword(), userFromDb.getHashPassword());

        if (state) {
            String accessToken = UserSecurity.Hasher.generateAccessToken(userFromDb);
            userService.userAuth(accessToken, userFromDb);
            response.header("AccessToken", accessToken);
            return gson.toJson("authorization=OK");
        }*/
        return "Authorization Failed";
    };


    @Getter
    private static final Route updateUser = (request, response) -> {

        return "";
    };

    @Getter
    private static final Route deleteUser = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route userById = (request, response) -> {
        return "";
    };


    // Admin's part

    @Getter
    private static final Route addAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route authorizationAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route updateAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route deleteAdmin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route admin = (request, response) -> {
        return "";
    };

    @Getter
    private static final Route allAdmins = (request, response) -> {
        return "";
    };
}