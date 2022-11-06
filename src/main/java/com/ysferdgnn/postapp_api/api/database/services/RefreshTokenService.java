package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import com.ysferdgnn.postapp_api.api.database.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    RefreshTokenRepository refreshTokenRepository;
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }


    public RefreshToken saveOneRefreshToken(RefreshToken refreshToken){
        return  refreshTokenRepository.save(refreshToken);
    }

    public  void deleteRefreshToken(RefreshToken refreshToken){
        refreshTokenRepository.delete(refreshToken);
    }

    public void  deleteRefreshTokenById(Long id){
        refreshTokenRepository.deleteById(id);
    }

    public Optional<RefreshToken> findByUserId(Long id){
        return refreshTokenRepository.findByUserId(id);
    }

    public boolean isRefreshTokenExpired(RefreshToken refreshToken){
        return refreshToken.getExpiryDate().before(new Date());
    }

}
