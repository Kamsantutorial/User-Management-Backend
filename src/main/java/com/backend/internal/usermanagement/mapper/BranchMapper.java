package com.backend.internal.usermanagement.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.branch.BranchDTO;
import com.backend.internal.usermanagement.entity.primary.BranchEntity;
import com.backend.internal.usermanagement.vo.branch.request.BranchRequestPageVO;
import com.backend.internal.usermanagement.vo.branch.response.BranchResponseVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BranchMapper {

    BranchMapper INSTANCE = Mappers.getMapper(BranchMapper.class);

    void copyListEntityToListDto(List<BranchEntity> listEntity, @MappingTarget List<BranchDTO> listDTO);

    void copyRequestPageVoDto(BranchRequestPageVO requestPageVO, @MappingTarget BranchDTO branchDTO);

    void copyRequestPageVoToRequestPageDto(BranchRequestPageVO requestPageVO,
            @MappingTarget RequestPageableDTO requestPageableDTO);

    void copyListDtoToListResponseItemVo(List<BranchDTO> list, @MappingTarget List<BranchResponseVO> response);

    BranchResponseVO map(BranchDTO value);

}
