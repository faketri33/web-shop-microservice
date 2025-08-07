package org.faketri.entity.user.exception;

public class UserSavingError extends RuntimeException {
    public UserSavingError(String message) {
        super(message);
    }
}
