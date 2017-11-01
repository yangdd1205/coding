package entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class RolePermission implements Serializable {
    private Long roleId;
    private Long permissionId;
}
