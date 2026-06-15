package umc.domain.auth.oauth;

import lombok.Getter;
import umc.domain.auth.exception.AuthException;
import umc.domain.auth.exception.code.AuthErrorCode;

@Getter
public class OAuthAttributeMissingException extends AuthException {

    private final String attribute;

    public OAuthAttributeMissingException(String attribute) {
        super(AuthErrorCode.OAUTH_MISSING_ATTRIBUTES);
        this.attribute = attribute;
    }
}
