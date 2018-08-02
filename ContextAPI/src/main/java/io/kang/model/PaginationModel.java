package io.kang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationModel {
    private long id; // categoryId.
    private int sz = 6; // size. 기본 값 6
    private int pg = 1; // page. 기본 값 1
    private int ob; // order by.
    private int sb; // search by.
    private long requestCount; // requestCount.
    private String st; // search text.
}
