package com.backend.internal.usermanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.vo.menu.request.MenuCreateRequestVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuRequestPageVO;
import com.backend.internal.usermanagement.vo.menu.request.MenuUpdateRequestVO;
import com.backend.internal.usermanagement.vo.menu.response.MenuLoginResponseVO;
import com.backend.internal.usermanagement.vo.menu.response.MenuResponseVO;
import com.backend.internal.usermanagement.vo.permission.response.MenuTypeResponseVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuMapper {

    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);

    void copyEntityToDto(MenuEntity entity, @MappingTarget MenuDTO dto);

    void copyDtoToEntity(MenuDTO dto, @MappingTarget MenuEntity entity);

    void copyListEntityToListDto(List<MenuEntity> listEntity, @MappingTarget List<MenuDTO> listMenu);

    void copyRequestPageVoDto(MenuRequestPageVO requestPageVO, @MappingTarget MenuDTO menuDTO);

    void copyRequestPageVoToRequestPageDto(MenuRequestPageVO requestPageVO,
            @MappingTarget RequestPageableDTO requestPageableDTO);

    void copyListDtoToListResponseItemVo(List<MenuDTO> menuDTOs, @MappingTarget List<MenuResponseVO> responses);

    MenuResponseVO map(MenuEntity value);

    void copyListEntityToResponseVo(List<MenuEntity> menus, @MappingTarget List<MenuResponseVO> responseVOs);

    void copyListDtoToResponseVo(List<MenuDTO> menus, @MappingTarget List<MenuResponseVO> responseVOs);

    void copyListDtoToLoginResponseVo(List<MenuDTO> menus, @MappingTarget List<MenuLoginResponseVO> responseVOs);

    @Mapping(source = "url", target = "path")
    @Mapping(source = "menuName", target = "name")
    MenuLoginResponseVO map(MenuDTO dto);

    void copyEntityToResponseVo(MenuEntity menu, @MappingTarget MenuResponseVO responseVO);

    void copyListEntityToResponseTypeVo(List<MenuDTO> menus, @MappingTarget List<MenuTypeResponseVO> responseVOs);

    void copyCreateRequestVoToDto(MenuCreateRequestVO vo, @MappingTarget MenuDTO dto);

    void copyUpdateRequestVoToDto(MenuUpdateRequestVO vo, @MappingTarget MenuDTO dto);

}
