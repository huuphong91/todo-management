package huu.phong.todomanagement.service;

import huu.phong.todomanagement.dto.LoginDto;
import huu.phong.todomanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);

    String login(LoginDto loginDto);
}
