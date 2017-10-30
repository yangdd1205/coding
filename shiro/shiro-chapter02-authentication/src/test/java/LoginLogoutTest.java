import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class LoginLogoutTest {

    @Test
    public void testHelloWorld() throws Exception {
        //1.获取SecurityManager工厂，此处使用ini文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4.登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.验证失败
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        //6.退出
        subject.logout();
    }

    @Test
    public void testCustomRealm() throws Exception {
        //1.获取SecurityManager工厂，此处使用ini文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4.登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.验证失败
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        //6.退出
        subject.logout();
    }

    @Test
    public void testCustomMultiRealm() throws Exception {

        //securityManager会按照realms指定的顺序进行身份认证。但是他们的验证属于单或的关系，无论怎么排，无论在哪个realm通过，所有的都会执行一遍

        //1.获取SecurityManager工厂，此处使用ini文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4.登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.验证失败
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        //6.退出
        subject.logout();
    }

    @Test
    public void testJDBCRealm() throws Exception {

        /*
        *org.apache.shiro.realm.jdbc.JdbcRealm：
        * 通过sql查询相应的信息，如
        * “select password from users where username = ?”获取用户密码，
        * “select password, password_salt from users where username = ?”获取用户密码及盐；
        * “select role_name from user_roles where username = ?”获取用户角色；
        * “select permission from roles_permissions where role_name = ?”获取角色对应的权限信息；
        * 也可以调用相应的api进行自定义sql；
        */

        //1.获取SecurityManager工厂，此处使用ini文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        //3.得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            //4.登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5.验证失败
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());

        //6.退出
        subject.logout();
    }

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();//退出时请解除绑定Subject到线程 否则对下次测试造成影响
    }
}
