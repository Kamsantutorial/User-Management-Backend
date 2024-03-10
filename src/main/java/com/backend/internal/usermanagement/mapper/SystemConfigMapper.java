package com.backend.internal.usermanagement.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.backend.internal.usermanagement.dto.SystemConfigDTO;
import com.backend.internal.usermanagement.entity.base.SystemConfigEntity;
import com.backend.internal.usermanagement.vo.SystemConfigVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SystemConfigMapper {
    SystemConfigMapper INSTANCE = Mappers.getMapper(SystemConfigMapper.class);

    void copyEntityToDto(SystemConfigEntity entity, @MappingTarget SystemConfigDTO dto);

    void copyDtoToEntity(SystemConfigDTO dto, @MappingTarget SystemConfigEntity entity);

    void copyListEntityToListDto(List<SystemConfigEntity> listEntity, @MappingTarget List<SystemConfigDTO> listDTO);

    void copyListEntityToListResponseVo(List<SystemConfigDTO> listDTO,
            @MappingTarget List<SystemConfigVO> listVO);

    void copyVoToDto(SystemConfigVO vo, @MappingTarget SystemConfigDTO dto);

}
