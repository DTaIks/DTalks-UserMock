package com.dtalk.Dtalks_UserMock.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data // lombok 포함 시 equals/hashCode 자동 구현
@NoArgsConstructor
@AllArgsConstructor
public class Identification {
    private String type;
    private String value;
}
