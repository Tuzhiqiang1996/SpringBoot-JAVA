package com.example.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Tu
 * @Package com.example.common.dto
 * @date 2021/2/1-11:45
 */
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

}
