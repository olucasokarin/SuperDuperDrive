package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;


@Service
public class CredentialService {

    private final CredentialMapper credentialMapper ;
    private final UserService userService;
    private final HashService hashService;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, UserService userService, HashService hashService, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userService = userService;
        this.hashService = hashService;
        this.encryptionService = encryptionService;
    }

    public int createCredential(Credential credential) {
        User user = userService.getCurrentUser();

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
//        String hashedPassword = hashService.getHashedValue(credential.getPassword(), encodedKey);

        String hashedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        return credentialMapper.insert(new Credential(null, credential.getUsername(), credential.getUrl(), encodedKey, hashedPassword, user.getUserId()));
    }

    public void updateCredential(Credential credential) {
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);

        String hashedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credentialMapper.update(credential.getCredentialId(), credential.getUsername(), credential.getUrl(), encodedKey, hashedPassword);
    }

    public List<Credential> getCredentials() {
        User user = userService.getCurrentUser();
        return credentialMapper.getCredentials(user.getUserId());
    }

    public Credential getCredential(Integer credentialId) {
       return credentialMapper.getCredential(credentialId);
    }

    public void deleteCredential(Integer credentialId) {
        credentialMapper.deleteCredential(credentialId);
    }

}
