package com.backend.internal.usermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.permission.PermissionDTO;
import com.backend.internal.usermanagement.entity.primary.PermissionEntity;
import com.backend.internal.usermanagement.vo.permission.request.PermissionCreateRequestVO;
import com.backend.internal.usermanagement.vo.permission.request.PermissionRequestPageVO;
import com.backend.internal.usermanagement.vo.permission.request.PermissionUpdateRequestVO;
import com.backend.internal.usermanagement.vo.permission.response.PermissionResponseVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionMapper {

        PermissionMapper INSTANCE = Mappers.getMapper(PermissionMapper.class);

        void copyDtoToEntity(PermissionDTO dto, @MappingTarget PermissionEntity entity);

        void copyEntityToDto(PermissionEntity entity, @MappingTarget PermissionDTO dto);

        void copyListEntityToListDto(List<PermissionEntity> listEntity,
                        @MappingTarget List<PermissionDTO> permissionDTOs);

        void copyRequestPageVoDto(PermissionRequestPageVO requestPageVO, @MappingTarget PermissionDTO permissionDTO);

        void copyRequestPageVoToRequestPageDto(PermissionRequestPageVO requestPageVO,
                        @MappingTarget RequestPageableDTO requestPageableDTO);

        void copyListDtoToListResponseItemVo(List<PermissionDTO> permissionDTOs,
                        @MappingTarget List<PermissionResponseVO> responses);

        PermissionResponseVO map(PermissionEntity value);

        void copyCreateRequestVoToDto(PermissionCreateRequestVO vo, @MappingTarget PermissionDTO dto);


        void copyUpdateRequestVoToDto(PermissionUpdateRequestVO vo, @MappingTarget PermissionDTO dto);


        void copyListEntityToResponseVo(List<PermissionEntity> permissions,
                        @MappingTarget List<PermissionResponseVO> responseVOs);

        void copyEntityToResponseVo(PermissionEntity permission, @MappingTarget PermissionResponseVO responseVO);

        void copyDtoToResponseVo(PermissionDTO dto, @MappingTarget PermissionResponseVO responseVO);
}
