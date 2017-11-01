package service;

import entity.Permission;

public interface PermissionService {
    Permission createPermission(Permission permission);

    void deletePermission(Long permissionId);
}