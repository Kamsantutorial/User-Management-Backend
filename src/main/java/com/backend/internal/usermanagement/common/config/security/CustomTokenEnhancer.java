package com.backend.internal.usermanagement.common.config.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import com.backend.internal.usermanagement.dto.menu.MenuDTO;
import com.backend.internal.usermanagement.entity.primary.UserEntity;
import com.backend.internal.usermanagement.mapper.MenuMapper;
import com.backend.internal.usermanagement.mapper.UserMapper;
import com.backend.internal.usermanagement.service.primary.MenuService;
import com.backend.internal.usermanagement.vo.menu.response.MenuLoginResponseVO;
import com.backend.internal.usermanagement.vo.user.response.UserLoginResponseVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Bunthoeurn CHUOB
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    private static final Logger logger = LogManager.getLogger(CustomTokenEnhancer.class);

    private final MenuService menuService;

    public CustomTokenEnhancer(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        try {
            UserEntity user = (UserEntity) authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>();

            UserLoginResponseVO userResponse = new UserLoginResponseVO();
            UserMapper.INSTANCE.copyEntityToLoginResponseVo(user, userResponse);
            additionalInfo.put("users", userResponse);

            List<MenuDTO> menus = menuService.findAllByUser(user);
            List<MenuLoginResponseVO> menuResponse = new ArrayList<>();
            MenuMapper.INSTANCE.copyListDtoToLoginResponseVo(menus, menuResponse);
            additionalInfo.put("menus", menuResponse);
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        } catch (Exception e) {
            logger.info("Error", e);
        }
        return accessToken;
    }
}
