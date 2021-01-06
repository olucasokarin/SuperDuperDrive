package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("credentials")
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping()
    public String credentialCreate(@ModelAttribute Credential credential, Model model){
        if(credentialService.getCredential(credential.getCredentialId()) == null)
            credentialService.createCredential(credential);
        else
            credentialService.updateCredential(credential);

        model.addAttribute("result", "success");

        return "result";
    }

    @PostMapping("/{id}")
    public String deleteCredential(@PathVariable("id") Integer id, Model model) {
        credentialService.deleteCredential(id);
        model.addAttribute("result", "success");
        return "result";
    }
}
