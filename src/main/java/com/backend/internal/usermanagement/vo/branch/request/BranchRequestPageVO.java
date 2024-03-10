package com.backend.internal.usermanagement.vo.branch.request;

import com.backend.internal.usermanagement.vo.RequestPageableVO;

import lombok.Data;

@Data
public class BranchRequestPageVO extends RequestPageableVO {
	 private String searchKeyword;
}
