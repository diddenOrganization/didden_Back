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

    public AnnoVo() {
    }

    public AnnoVo(String annoId, String annoTitle, String annoContent, String annoCreateDate) {
        this.annoId = annoId;
        this.annoTitle = annoTitle;
        this.annoContent = annoContent;
        this.annoCreateDate = annoCreateDate;
    }
}
