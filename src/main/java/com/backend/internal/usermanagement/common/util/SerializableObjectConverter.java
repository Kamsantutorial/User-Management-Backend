package com.backend.internal.usermanagement.common.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import lombok.experimental.UtilityClass;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

@UtilityClass
public class SerializableObjectConverter {

    public static String serialize(OAuth2Authentication object) {
        try {
            byte[] bytes = SerializationUtils.serialize(object);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static OAuth2Authentication deserialize(String encodedObject) {
        try {
            byte[] bytes = Base64.decodeBase64(encodedObject);
            return (OAuth2Authentication) SerializationUtils.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static OAuth2RefreshToken deserializeRefreshToken(String encodedObject) {
        try {
            byte[] bytes = Base64.decodeBase64(encodedObject);
            return (OAuth2RefreshToken) SerializationUtils.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String serializeRefreshToken(OAuth2RefreshToken object) {
        try {
            byte[] bytes = SerializationUtils.serialize(object);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static String serializeAccessToken(OAuth2AccessToken object) {
        try {
            byte[] bytes = SerializationUtils.serialize(object);
            return Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static OAuth2AccessToken deserializeAccessToken(String encodedObject) {
        try {
            byte[] bytes = Base64.decodeBase64(encodedObject);
            return (OAuth2AccessToken) SerializationUtils.deserialize(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
