package com.backend.internal.usermanagement.vo.user.response;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author SETC01
 *
 */
@Data
public class BranchLoginResponseVO implements Serializable {
	private Long id;
	private String branchName;
}
