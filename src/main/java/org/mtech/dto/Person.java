package org.mtech.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
}
