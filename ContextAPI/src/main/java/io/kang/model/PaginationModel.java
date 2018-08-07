package io.kang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    public String getQueryString() {
        String url = null;
        try {
            String temp = (st == null) ? "" : URLEncoder.encode(st, "UTF-8");
            url = String.format("id=%d&pg=%d&sz=%d&ob=%d&sb=%d&st=%s", id, pg, sz, ob, sb, temp);
        }
        catch (UnsupportedEncodingException e) {

        }
        return url;
    }
}
