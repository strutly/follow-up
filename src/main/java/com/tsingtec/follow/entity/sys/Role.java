package com.tsingtec.follow.entity.sys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsingtec.follow.entity.BaseEntity;
import com.tsingtec.follow.vo.resp.sys.menu.MenuRespNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 角色表
 * @author lj
 *
 */
@Data
@Entity
@DynamicInsert(true)
@DynamicUpdate(true)
@Table(name = "sys_role")
@ToString(exclude = {"admins", "menus"})
@EqualsAndHashCode(exclude = {"admins", "menus"})
public class Role extends BaseEntity {

	private String name;

    private String description;

    private Integer status;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_menu",joinColumns = @JoinColumn(name = "rid"),inverseJoinColumns = @JoinColumn(name = "mid"))
    private Set<Menu> menus = new HashSet<>(0);

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Admin> admins = new HashSet<>(0);

    @Transient
    private List<MenuRespNode> chilids;

}