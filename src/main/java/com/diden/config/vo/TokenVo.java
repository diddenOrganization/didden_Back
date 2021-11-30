package com.diden.config.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenVo {
    private String accessJwsToken = null;
    private String refreshJwsToken = null;
}
