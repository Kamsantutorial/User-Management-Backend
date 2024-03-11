package com.backend.internal.usermanagement.vo.branch.response;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class BranchResponseVO {
	private Long id;
	private String branchName;
	private String branchCode;
	private Boolean isActive;
	private String createdBy;
	private Timestamp createdAt;
	private String updatedBy;
	private Timestamp updatedAt;
	private Boolean isDeleted;
	private String deletedBy;
	private Timestamp deletedAt;
}
