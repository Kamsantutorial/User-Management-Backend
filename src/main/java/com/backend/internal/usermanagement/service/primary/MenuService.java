package com.backend.internal.usermanagement.service.primary;

import java.util.List;
import org.springframework.data.domain.Page;
import com.backend.internal.usermanagement.dto.base.RequestPageableDTO;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.MenuEntity;
import com.backend.internal.usermanagement.entity.primary.UserEntity;


public interface MenuService {

	MenuEntity findById(Long id);

	List<MenuEntity> getMenus(int length, int start);

	int countAllMenus();

	List<MenuEntity> getParentMenus();

	boolean save(MenuEntity menu);

	List<MenuEntity> getAllMenus();

	int existByMenuUrl(String url);

	MenuEntity existByMenuName(String menuName, int isDeleted);

	List<MenuEntity> findAllByParentId(Long parentId, int isDeleted);

	Page<MenuDTO> findWithPage(MenuDTO menuDTO, RequestPageableDTO pageableDTO);

	List<MenuDTO> findAllTypeMenu();

	List<MenuDTO> findAllByUser(UserEntity userEntity);
}
