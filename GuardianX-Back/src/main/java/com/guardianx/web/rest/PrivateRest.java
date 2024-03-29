package com.guardianx.web.rest;

import com.guardianx.utils.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/private")
@PreAuthorize("hasAnyAuthority('task:read')")
public class PrivateRest {

    @GetMapping("/hello")
    public ResponseEntity<Response<String>> hello(){
        Response<String> response = new Response<>();
        response.setResult("Hey There");
        return ResponseEntity.ok().body(response);
    }
}
