package com.example.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Tu
 * @Package com.example.shiro
 * @date 2021/2/1-10:52
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}

