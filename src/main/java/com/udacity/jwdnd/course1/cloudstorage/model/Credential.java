package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {
    private Integer credentialId;
    private String username;
    private String url;
    private String key;
    private String password;
    private Integer userId;

    public Credential(Integer credentialId, String username, String url, String key, String password, Integer userId) {
        this.credentialId = credentialId;
        this.username = username;
        this.url = url;
        this.key = key;
        this.password = password;
        this.userId = userId;
    }


    public Integer getCredentialId() {
        return credentialId;
    }

    public String getUsername() {
        return username;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getKey() {
        return key;
    }

    public Integer getUserId() {
        return userId;
    }


}
