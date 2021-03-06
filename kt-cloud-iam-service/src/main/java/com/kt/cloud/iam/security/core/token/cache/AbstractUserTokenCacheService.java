package com.kt.cloud.iam.security.core.token.cache;

import com.alibaba.fastjson.JSONObject;
import com.kt.cloud.iam.api.user.permission.response.LoginUserResponse;
import com.kt.cloud.iam.security.core.common.RedisKeyConst;
import com.kt.cloud.iam.security.core.token.generate.UserTokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public abstract class AbstractUserTokenCacheService implements IUserTokenCacheService {

    private final UserTokenGenerator userTokenGenerator;

    public AbstractUserTokenCacheService(UserTokenGenerator userTokenGenerator) {
        this.userTokenGenerator = userTokenGenerator;
    }

    private final int expires = (60 * 60 * 24 * 7) * 1000;

    private String createAccessTokenKey(String accessToken) {
        return RedisKeyConst.LOGIN_USER_ACCESS_TOKEN_KEY_PREFIX + accessToken;
    }

    private String createUserIdKey(Long userId) {
        return RedisKeyConst.LOGIN_USER_ID_KEY_PREFIX + userId;
    }

    @Override
    public final void remove(String accessToken) {
        LoginUserResponse loginUserResponse = get(accessToken);
        if (loginUserResponse != null) {
            removeCache(createAccessTokenKey(accessToken));
            removeCache(createUserIdKey(loginUserResponse.getUserId()));
        }
    }

    @Override
    public final LoginUserResponse get(String accessToken) {
        Object cache = getCache(createAccessTokenKey(accessToken));
        if (cache == null) {
            return null;
        }
        return JSONObject.parseObject((String) cache, LoginUserResponse.class);
    }

    @Override
    public void remove(Long userId) {
        String token = (String) getCache(createUserIdKey(userId));
        if (StringUtils.isNotBlank(token)) {
            removeCache(createAccessTokenKey(token));
            removeCache(createUserIdKey(userId));
        }
    }

    @Override
    public final UserCacheInfo save(LoginUserResponse userContext) {
        String accessToken = genAccessToken();
        saveCache(createAccessTokenKey(accessToken), JSONObject.toJSONString(userContext), expires);
        saveCache(createUserIdKey(userContext.getUserId()), accessToken, expires);
        return new UserCacheInfo(accessToken, expires);
    }

    private String genAccessToken() {
        String accessToken;
        // ????????????
        do {
            accessToken = userTokenGenerator.generate();
        } while (!checkAccessTokenIsNotExists(accessToken));
        return accessToken;
    }

    private boolean checkAccessTokenIsNotExists(String accessToken) {
        LoginUserResponse loginUserResponse = get(accessToken);
        return loginUserResponse == null;
    }


    abstract void saveCache(String key, Object value, long expires);

    abstract Object getCache(String key);

    abstract void removeCache(String key);

}
