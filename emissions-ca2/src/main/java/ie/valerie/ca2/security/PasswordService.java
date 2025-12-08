package ie.valerie.ca2.security;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordService {
	    public static String hash(String password) {
	        return BcryptUtil.bcryptHash(password);
	    }

	    public static boolean verify(String password, String hashed) {
	        return BcryptUtil.matches(password, hashed);
	    }
	}


