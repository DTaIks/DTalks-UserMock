package com.dtalk.Dtalks_UserMock.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class Identification {
    private String type;  // email
    private String value; // test@xxx.com
}

