package com.backend.internal.usermanagement.dto.branch;

import com.backend.internal.usermanagement.dto.BaseDTO;
import lombok.Data;

@Data
public class BranchDTO extends BaseDTO {
	private int id;
	private String branchName;
	private String branchCode;
    private String searchKeyword;
}
