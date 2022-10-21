package africa.semicolon.lumexpress.service;

import africa.semicolon.lumexpress.data.dtos.request.LoginRequest;
import africa.semicolon.lumexpress.data.dtos.response.LoginResponse;
import africa.semicolon.lumexpress.data.models.LumExpressUser;

import java.util.Optional;

public interface UserService {
    LoginResponse login(LoginRequest loginRequest);
    LumExpressUser getUserByUsername(String email);

}
