package dmitr.stockControl.itemService.service.face.auth;

import dmitr.stockControl.itemService.model.auth.AuthUserDto;

public interface AuthUserTokenExtractorService {

    AuthUserDto getAuthUserFromAccessToken(String accessToken);
}
