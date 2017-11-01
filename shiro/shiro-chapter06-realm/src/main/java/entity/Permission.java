package entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Permission implements Serializable {
    private Long id;
    private String permission;//权限标识，程序中判断使用“user:create”
    private String description;//权限描述，UI界面使用
    private Boolean available = Boolean.FALSE;//是否可用，如果不可用将不会添加给用户

    public Permission( String permission, String description, Boolean available) {
        this.permission = permission;
        this.description = description;
        this.available = available;
    }

    public Permission() {
    }
}
