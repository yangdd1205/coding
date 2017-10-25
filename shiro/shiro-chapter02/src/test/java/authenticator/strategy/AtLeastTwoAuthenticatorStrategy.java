package authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class AtLeastTwoAuthenticatorStrategy extends AbstractAuthenticationStrategy {

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
        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
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
