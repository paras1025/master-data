package com.master.response;

import com.master.request.Pagination;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetListResponse<L> {

    List<L> data;

    Pagination pagination;

}
