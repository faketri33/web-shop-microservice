package org.faketri.infrastructure.pojo.request;

public record Credential(String type, String value, boolean temporary) {
}
