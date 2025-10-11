package org.faketri.infrastructure.pojo.response;

import java.util.UUID;

public record ProfileResponsePojo(UUID id, String email, String username, String images) {
}