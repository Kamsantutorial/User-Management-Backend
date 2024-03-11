package com.backend.internal.usermanagement.service.primary.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import com.backend.internal.usermanagement.common.enums.ErrorCode;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.user.UserDTO;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.exception.ServerException;
import com.backend.internal.usermanagement.mapper.UserMapper;
import com.backend.internal.usermanagement.repository.base.BaseCriteria;
import com.backend.internal.usermanagement.repository.primary.UserRepository;
import com.backend.internal.usermanagement.service.primary.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity findByUsername(String username) {
        BaseCriteria<UserRepository> criteria = new BaseCriteria<>(userRepository);
        criteria.join("roles", JoinType.LEFT);
        criteria.join("roles.permissions", JoinType.LEFT);
        criteria.equal("username", username);
        criteria.distinct(true);
        return userRepository.findOneWithCriteria(criteria).orElse(null);
    }

    @Override
    public void create(UserDTO dto) throws ServerException {
        UserEntity userEntity = new UserEntity();
        UserMapper.INSTANCE.copyDtoToEntity(dto, userEntity);
        userRepository.save(userEntity);
        UserMapper.INSTANCE.copyEntityToDto(userEntity, dto);
    }

    @Override
    public void update(UserDTO dto, Long id) throws ServerException {
        UserDTO userDTO = this.findOne(id);

        if (Objects.isNull(userDTO)) {
            throw new ServerException(ErrorCode.E002.name(), ErrorCode.E002.getDesc());
        }
        UserEntity userEntity = new UserEntity();
        UserMapper.INSTANCE.copyDtoToEntity(dto, userEntity);
        userRepository.save(userEntity);
        UserMapper.INSTANCE.copyEntityToDto(userEntity, dto);
    }

    @Override
    public void delete(Long id) throws ServerException {
        UserDTO userDTO = this.findOne(id);

        if (Objects.isNull(userDTO)) {
            throw new ServerException(ErrorCode.E002.name(), ErrorCode.E002.getDesc());
        }
        UserEntity userEntity = new UserEntity();
        UserMapper.INSTANCE.copyDtoToEntity(userDTO, userEntity);
        userEntity.setIsDeleted(true);
        userRepository.save(userEntity);
    }

    @Override
    public UserDTO findOne(Long id) throws ServerException {
        BaseCriteria<UserRepository> criteria = new BaseCriteria<>(userRepository);
        criteria.equal("id", id);
        UserEntity userEntity = userRepository.findOneWithCriteria(criteria).orElse(null);
        UserDTO dto = new UserDTO();
        UserMapper.INSTANCE.copyEntityToDto(userEntity, dto);
        return dto;
    }

    @Override
    public List<UserDTO> findAll() {
        BaseCriteria<UserRepository> criteria = new BaseCriteria<>(userRepository);
        criteria.equal("isActive", true);
        List<UserEntity> entities = userRepository.findAllWithCriteria(criteria);
        List<UserDTO> items = new ArrayList<>();
        UserMapper.INSTANCE.copyListEntityToListDto(entities, items);
        return items;
    }

    @Override
    public Page<UserDTO> findWithPage(UserDTO userDTO, RequestPageableDTO pageableDTO) {
        BaseCriteria<UserRepository> criteria = new BaseCriteria<>(userRepository);
        criteria.join("roles", JoinType.LEFT);
        criteria.or(
                criteria.like("username", userDTO.getSearchKeyword()),
                criteria.equal("id", userDTO.getSearchKeyword()));
        criteria.distinct(true);
        criteria.size(pageableDTO.getSize());
        criteria.setPage(pageableDTO.getPageOffset());

        Page<UserEntity> users = userRepository.findAllWithPage(criteria);
        List<UserDTO> userDTOs = new ArrayList<>();
        UserMapper.INSTANCE.copyListEntityToListDto(users.getContent(), userDTOs);
        return new PageImpl<>(userDTOs, users.getPageable(), users.getTotalElements());
    }
}
