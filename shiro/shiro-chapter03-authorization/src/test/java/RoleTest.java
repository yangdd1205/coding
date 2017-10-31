import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class RoleTest extends BaseTest {
    @Test
    public void testHasRole() {
        login("classpath:shiro-role.ini", "zhang", "123");

        //Shiro提供了hasRole/hasRole用于判断用户是否拥有某个角色/某些权限；但是没有提供如hashAnyRole用于判断是否有某些权限中的某一个。

        //判断拥有角色：role1
        Assert.assertTrue(subject().hasRole("role1"));
        //判断拥有角色：role1 and role2
        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1", "role2")));
        //判断拥有角色：role1 and role2 and !role3
        boolean[] result = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertFalse(result[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole(){
        login("classpath:shiro-role.ini","zhang","123");

        //Shiro提供的checkRole/checkRoles和hasRole/hasAllRoles不同的地方是它在判断为假的情况下会抛出UnauthorizedException异常。

        //断言拥有角色 role1
        subject().checkRole("role1");
        //断言拥有角色 role1 and role3 失败抛出异常
        subject().checkRoles("role1","role3");
    }
}
