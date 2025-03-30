package com.master.request;

import com.master.enums.SortOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortByRequest {
    private String key;
    private SortOrder value;

    public static SortByRequest build(SortOrder value, String key) {
        return new SortByRequest(key, value);
    }
}