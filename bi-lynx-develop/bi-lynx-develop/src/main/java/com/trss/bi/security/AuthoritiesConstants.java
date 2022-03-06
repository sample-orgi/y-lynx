package com.trss.bi.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN = "ROLE_ADMIN";

    public static final String CUSTOMER_ADMIN = "ROLE_CUSTOMER_ADMIN";

    public static final String USER = "ROLE_USER";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    public static final String ACCESS_ADMIN = "hasRole('ROLE_ADMIN')";

    public static final String ACCESS_CUSTOMER_ADMIN = "hasRole('ROLE_CUSTOMER_ADMIN')";

    public static final String ACCESS_ADMIN_OR_CUSTOMER_ADMIN = "hasRole('ROLE_ADMIN') or hasRole('ROLE_CUSTOMER_ADMIN')";

    public static final String ACCESS_USER = "hasRole('ROLE_USER')";

    public static final String ACCESS_ANONYMOUS = "hasRole('ROLE_ANONYMOUS')";

    private AuthoritiesConstants() {
    }
}
