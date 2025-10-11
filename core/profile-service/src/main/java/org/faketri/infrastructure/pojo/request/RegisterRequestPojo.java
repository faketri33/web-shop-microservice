package org.faketri.infrastructure.pojo.request;

import java.util.List;

public record RegisterRequestPojo(String email, String username, List<Credential> credentials, String firstname,
                                  String lastname) {
}
