package com.example.stickers.service;

import com.example.stickers.dto.ProfileRequestDto;
import com.example.stickers.dto.ProfileResponseDto;

public interface IProfileService {

    ProfileResponseDto getProfile();

    ProfileResponseDto updateProfile(ProfileRequestDto profileRequestDto);
}
