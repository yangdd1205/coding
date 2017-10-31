import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Assert;
import org.junit.Test;

public class PermissionTest extends BaseTest {
    @Test
    public void testIsPermitted() {
        login("classpath:shiro-permission.ini", "zhang", "123");

        //Shiro提供了isPermitted和isPermittedAll用于判断用户是否拥有某个权限或所有权限，也没有提供如isPermittedAny用于判断拥有某一个权限的接口。

        //判断拥有权限：user:create
        Assert.assertTrue(subject().isPermitted("user:create"));
        //判断拥有权限：user:update and user:delete
        Assert.assertTrue(subject().isPermittedAll("user:update", "user:delete"));
        //判断没有权限：user:view
        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:shiro-permission.ini", "zhang", "123");
        //断言拥有权限：user:create
        subject().checkPermission("user:create");

        //断言拥有权限：user:update and user:delete
        subject().checkPermissions("user:update", "user:delete");

        //失败的情况下会抛出UnauthorizedException异常
        subject().checkPermission("user:view");
    }

    @Test
    public void testWildcardPermission1() {
        login("classpath:shiro-permission.ini", "li", "123");

        subject().checkPermissions("system:user:update", "system:user:delete");
        subject().checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission2() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("system:user:create,delete,update:view");

        subject().checkPermissions("system:user:*");
        subject().checkPermissions("system:user");
    }

    @Test
    public void testWildcardPermission3() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view");

        subject().checkPermissions("system:user:view");
    }

    @Test
    public void testWildcardPermission4() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("user:view:1");

        subject().checkPermissions("user:delete,update:1");
        subject().checkPermissions("user:update:1", "user:delete:1");

        subject().checkPermissions("user:update:1", "user:delete:1", "user:view:1");

        subject().checkPermissions("user:auth:1", "user:auth:2");

    }

    @Test
    public void testWildcardPermission5() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermissions("menu:view:1");

        subject().checkPermissions("organization");
        subject().checkPermissions("organization:view");
        subject().checkPermissions("organization:view:1");

    }


    @Test
    public void testWildcardPermission6() {
        login("classpath:shiro-permission.ini", "li", "123");
        subject().checkPermission("menu:view:1");
        subject().checkPermission(new WildcardPermission("menu:view:1"));

    }
}
