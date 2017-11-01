package entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Long id;
    private String role;//角色标识 程序中判断使用，如“admin”
    private String description;//角色描述，UI界面显示使用
    private Boolean available = Boolean.FALSE;//是否可用，如果不可用将不会添加给用户

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public Role() {
    }
}
