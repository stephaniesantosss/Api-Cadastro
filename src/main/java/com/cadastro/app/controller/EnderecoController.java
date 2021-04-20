package com.cadastro.app.controller;

import com.cadastro.app.request.EnderecoRequest;
import com.cadastro.app.response.BaseResponse;
import com.cadastro.app.response.ListEnderecoResponse;
import com.cadastro.app.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController extends BaseController{
    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody EnderecoRequest enderecoRequest){
        try {
            BaseResponse response = enderecoService.salvarCadastroEndereco(enderecoRequest);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
        }
    }
    @GetMapping(path = "/{id}")
    public ResponseEntity listarEndereco(@PathVariable Long id) {
        try {
            ListEnderecoResponse response = enderecoService.listarEnderecos(id);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
        }
    }
}
