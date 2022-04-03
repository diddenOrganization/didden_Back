package com.diden.anno;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AnnoVo {
    private String annoId;
    private String annoTitle;
    private String annoContent;
    private String annoCreateDate;
}
