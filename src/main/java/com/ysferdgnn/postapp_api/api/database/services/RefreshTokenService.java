package com.ysferdgnn.postapp_api.api.database.services;

import com.ysferdgnn.postapp_api.api.database.models.RefreshToken;
import com.ysferdgnn.postapp_api.api.database.repos.concretes.RefreshTokenRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenService {

    RefreshTokenRepositoryImpl refreshTokenRepositoryImpl;

    public RefreshTokenService(RefreshTokenRepositoryImpl refreshTokenRepository) {
        this.refreshTokenRepositoryImpl = refreshTokenRepository;
    }


    public RefreshToken saveOneRefreshToken(RefreshToken refreshToken){
        return  refreshTokenRepositoryImpl.save(refreshToken);
    }

    public  void deleteRefreshToken(RefreshToken refreshToken){
        refreshTokenRepositoryImpl.delete(refreshToken);
    }

    public void  deleteRefreshTokenById(Long id){
        refreshTokenRepositoryImpl.deleteById(id);
    }

    public Optional<RefreshToken> findByUserId(Long id){
        return refreshTokenRepositoryImpl.findByUserId(id);
    }

    public boolean isRefreshTokenExpired(RefreshToken refreshToken){
        return refreshToken.getExpiryDate().before(new Date());
    }

}
