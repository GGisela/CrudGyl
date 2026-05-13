package com.gyl.CrudGyl.service;

import com.gyl.CrudGyl.dto.LoginRequestDTO;
import com.gyl.CrudGyl.dto.RegistroRequestDTO;
import com.gyl.CrudGyl.dto.RegistroResponseDTO;
import com.gyl.CrudGyl.dto.TokenResponseDTO;

public interface AuthenticationService {
    RegistroResponseDTO registrar(RegistroRequestDTO dto);

    TokenResponseDTO login(LoginRequestDTO dto);
}