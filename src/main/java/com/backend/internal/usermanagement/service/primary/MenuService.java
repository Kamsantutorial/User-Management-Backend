package com.backend.internal.usermanagement.service.primary;

import java.util.List;
import org.springframework.data.domain.Page;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.service.base.BaseService;


public interface MenuService extends BaseService<MenuDTO, RequestPageableDTO> {

	MenuEntity findById(Long id);

	int countAllMenus();

	List<MenuDTO> getParentMenus();

	boolean save(MenuEntity menu);

	List<MenuDTO> getAllMenus();

	List<MenuEntity> findAllByParentId(Long parentId, int isDeleted);

	Page<MenuDTO> findWithPage(MenuDTO menuDTO, RequestPageableDTO pageableDTO);

	List<MenuDTO> findAllTypeMenu();

	List<MenuDTO> findAllByUser(UserEntity userEntity);
}
