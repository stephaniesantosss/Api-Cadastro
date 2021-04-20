package com.cadastro.app.controller;

import com.cadastro.app.model.Pessoa;
import com.cadastro.app.request.PessoaRequest;
import com.cadastro.app.response.BaseResponse;
import com.cadastro.app.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends BaseController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity salvar(@RequestBody PessoaRequest pessoaRequest) {
        try {
            BaseResponse response = pessoaService.salvarCadastroPessoa(pessoaRequest);
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(errorBase.getStatusCode()).body(errorBase);
        }
    }
}
