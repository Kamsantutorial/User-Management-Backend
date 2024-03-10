package com.backend.internal.usermanagement.entity.primary;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Where;
import com.backend.internal.usermanagement.entity.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(name = "menus")
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntity extends BaseEntity<Long> {
	/** */

	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "order_by")
	private Float orderBy;

	@Column(name = "url")
	private String url;

	private String redirect;

	private String component;

	@Column(name = "icon")
	private String icon;

	@Column(name = "parent_id")
	private Long parentId;

	@ManyToOne(targetEntity = MenuEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id", nullable = true, insertable = false, updatable = false)
	private MenuEntity parent;

	@OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
	@Where(clause = "is_deleted = false ")
	private Set<PermissionEntity> permissions;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
	@BatchSize(size = 10)
	private Set<MenuEntity> children = new HashSet<>();

	@Transient
	private List<MenuEntity> child;

	public MenuEntity(String menuName, String url, String icon, Float orderBy,
			Long parentId, String createdBy,
			Timestamp createdAt) {
		super();
		this.menuName = menuName;
		this.url = url;
		this.icon = icon;
		this.orderBy = orderBy;
		this.parentId = parentId;
		this.setCreatedBy(createdBy);
		this.setCreatedAt(createdAt);
	}

	public MenuEntity(String menuName, String url, String icon, Long parentId,
			Boolean isActive) {
		super();
		this.menuName = menuName;
		this.url = url;
		this.icon = icon;
		this.parentId = parentId;
		this.setIsActive(isActive);
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
