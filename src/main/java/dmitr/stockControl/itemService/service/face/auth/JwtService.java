package dmitr.stockControl.itemService.service.face.auth;

import io.jsonwebtoken.Claims;

public interface JwtService {

    Claims extractClaimsFromToken(String token);
}
