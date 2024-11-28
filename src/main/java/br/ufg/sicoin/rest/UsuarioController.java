package br.ufg.sicoin.rest;

import br.ufg.sicoin.dto.LoginR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginR loginRecord){

        String objRetorno = "nandemonai";

        if(loginRecord!=null){
            objRetorno = loginRecord.login() + loginRecord.senha();
        }

        return ResponseEntity.ok(objRetorno);
    }

}
