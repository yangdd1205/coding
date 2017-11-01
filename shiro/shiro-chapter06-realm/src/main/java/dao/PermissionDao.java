package dao;

import entity.Permission;

public interface PermissionDao {

    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}
