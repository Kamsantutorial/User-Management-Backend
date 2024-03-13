package com.backend.internal.usermanagement.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.role.RoleDTO;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.vo.user.request.UserCreateRequestVO;
import com.backend.internal.usermanagement.vo.user.request.UserRequestPageVO;
import com.backend.internal.usermanagement.vo.user.request.UserUpdateRequestVO;
import com.backend.internal.usermanagement.vo.user.response.UserLoginResponseVO;
import com.backend.internal.usermanagement.vo.user.response.UserResponseVO;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    void copyDtoToEntity(UserDTO dto, @MappingTarget UserEntity entity);

    void copyEntityToDto(UserEntity entity, @MappingTarget UserDTO dto);

    void copyListEntityToListDto(List<UserEntity> listEntity, @MappingTarget List<UserDTO> listDTO);

    void copyRequestPageVoDto(UserRequestPageVO requestPageVO, @MappingTarget UserDTO userDTO);

    void copyRequestPageVoToRequestPageDto(UserRequestPageVO requestPageVO,
            @MappingTarget RequestPageableDTO requestPageableDTO);

    void copyListDtoToListResponseItemVo(List<UserDTO> list, @MappingTarget List<UserResponseVO> response);

    UserResponseVO map(UserDTO value);

    void copyListEntityToResponseVo(List<UserEntity> users, @MappingTarget List<UserResponseVO> responseVOs);

    void copyEntityToResponseVo(UserEntity user, @MappingTarget UserResponseVO responseVO);

    void copyEntityToLoginResponseVo(UserEntity user, @MappingTarget UserLoginResponseVO responseVO);

    void copyCreateRequestVoToDto(UserCreateRequestVO vo, @MappingTarget UserDTO dto);

    void copyUpdateRequestVoToDto(UserUpdateRequestVO vo, @MappingTarget UserDTO dto);

    void copyDtoToResponseVo(UserDTO dto, @MappingTarget UserResponseVO vo);
}
