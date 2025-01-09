package com.example.securityserver.util;

import com.example.securityserver.domain.entity.RefreshEntity;
import com.example.securityserver.repository.RefreshRepository;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RefreshUtil {

    private final RefreshRepository refreshRepository;

    public RefreshUtil(RefreshRepository refreshRepository){
        this.refreshRepository = refreshRepository;
    }

    public void addRefreshEntity(String username, String refresh, Long expiredMs){
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
