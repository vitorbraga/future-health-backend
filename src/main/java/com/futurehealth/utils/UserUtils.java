package com.futurehealth.utils;

import com.futurehealth.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtils {

    public static final int PASSWORD_STRENGTH = 4;

    public static <T extends User> T setPasswordEncoded(T user, String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(PASSWORD_STRENGTH);
        user.setPassword(bCryptPasswordEncoder.encode(password));

        return user;
    }
}
