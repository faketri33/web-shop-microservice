package org.faketri.entity.profile.exception;

public class UserSavingError extends RuntimeException {
    public UserSavingError(String message) {
        super(message);
    }
}
