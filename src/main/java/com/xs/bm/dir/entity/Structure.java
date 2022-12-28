package com.xs.bm.dir.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Structure {
    private Long id;
    private Long rootId;
    private String name;
    private Integer type;
    private String accountId;
}
