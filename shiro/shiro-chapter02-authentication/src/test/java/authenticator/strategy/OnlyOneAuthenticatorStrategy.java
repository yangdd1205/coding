package authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    /**
     * 在所有Realm验证之前调用
     *
     * @param realms
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return super.beforeAllAttempts(realms, token);
    }

    /**
     * 在每个Realm之前调用
     *
     * @param realm
     * @param token
     * @param aggregate
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.beforeAttempt(realm, token, aggregate);
    }

    /**
     * 在每个Realm之后调用
     *
     * @param realm
     * @param token
     * @param singleRealmInfo
     * @param aggregateInfo
     * @param t
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        AuthenticationInfo info;
        if (singleRealmInfo == null) {
            info = aggregateInfo;
        } else {
            if (aggregateInfo == null) {
                info = singleRealmInfo;
            } else {
                info = merge(singleRealmInfo, aggregateInfo);
                if (info.getPrincipals().asList().size() > 1) {
                    System.out.println(info.getPrincipals().getRealmNames());
                    throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                            "could not be authenticated by any configured realms.  Please ensure that only one realm can " +
                            "authenticate these tokens.");
                }
            }
        }
        return info;
    }

    /**
     * 在所有Realm之后调用
     *
     * @param token
     * @param aggregate
     * @return
     * @throws AuthenticationException
     */
    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return super.afterAllAttempts(token, aggregate);
    }
}
