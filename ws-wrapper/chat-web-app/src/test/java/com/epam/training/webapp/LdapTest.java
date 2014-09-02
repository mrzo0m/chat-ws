package com.epam.training.webapp;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Properties;
import java.util.jar.Attributes;

/**
 * Created by mr.zoom on 06.07.14.
 */
@Ignore
public class LdapTest {

    @Test
    public void test1() throws NamingException {
        Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.setProperty(Context.PROVIDER_URL, "ldap://localhost:10389");
        env.setProperty(Context.SECURITY_PRINCIPAL, "uid=admin,ou=system");
        env.setProperty(Context.SECURITY_CREDENTIALS, "secret");
        DirContext ctx = new InitialDirContext(env);
        ctx.close();
    }
}
