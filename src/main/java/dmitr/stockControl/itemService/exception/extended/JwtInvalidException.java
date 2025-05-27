package dmitr.stockControl.itemService.exception.extended;

import dmitr.stockControl.itemService.exception.base.UnauthorizedException;

public class JwtInvalidException extends UnauthorizedException {

    public JwtInvalidException() {
        super("auth.jwt.invalid");
    }
}
