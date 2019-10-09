package com.andreaseisele.mussig.persistence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Artist extends BaseObject<Long> {

    private String name;

}
