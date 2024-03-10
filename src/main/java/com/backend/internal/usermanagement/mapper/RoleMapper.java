package com.backend.internal.usermanagement.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.role.RoleDTO;
import com.backend.internal.usermanagement.entity.primary.RoleEntity;
import com.backend.internal.usermanagement.vo.role.request.RoleRequestPageVO;
import com.backend.internal.usermanagement.vo.role.response.RoleResponseVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    void copyDtoToEntity(RoleDTO dto, @MappingTarget RoleEntity entity);

    void copyEntityToDto(RoleEntity entity, @MappingTarget RoleDTO dto);

    void copyListEntityToListDto(List<RoleEntity> listEntity, @MappingTarget List<RoleDTO> listDTO);

    void copyRequestPageVoDto(RoleRequestPageVO requestPageVO, @MappingTarget RoleDTO roleDTO);

    void copyRequestPageVoToRequestPageDto(RoleRequestPageVO requestPageVO,
            @MappingTarget RequestPageableDTO requestPageableDTO);

    void copyListDtoToListResponseItemVo(List<RoleDTO> list, @MappingTarget List<RoleResponseVO> response);

    RoleResponseVO map(RoleDTO value);

    void copyListEntityToResponseVo(List<RoleEntity> users, @MappingTarget List<RoleResponseVO> responseVOs);

    void copyListDtoToResponseVo(List<RoleDTO> dtos, @MappingTarget List<RoleResponseVO> responseVOs);

    void copyEntityToResponseVo(RoleEntity role, @MappingTarget RoleResponseVO responseVO);
}
