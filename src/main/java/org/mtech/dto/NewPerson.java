package org.mtech.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewPerson {

    private String firstName;
    private String lastName;
}
