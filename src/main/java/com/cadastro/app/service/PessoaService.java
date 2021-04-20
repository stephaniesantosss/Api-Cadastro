package com.cadastro.app.service;

import com.cadastro.app.model.Pessoa;
import com.cadastro.app.repository.PessoaRepository;
import com.cadastro.app.request.PessoaRequest;
import com.cadastro.app.response.BaseResponse;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public BaseResponse salvarCadastroPessoa(PessoaRequest pessoaRequest) {
        Pessoa pessoa = new Pessoa();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(400);
        boolean emailDuplicado = pessoaRepository.existsByEmail(pessoaRequest.getEmail());
        boolean cpfDuplicado = pessoaRepository.existsByCpf(pessoaRequest.getCpf());

        if (pessoaRequest.getNome().isEmpty() || pessoaRequest.getNome() == null) {
            baseResponse.setMessage("Favor preencher com o nome!");
            return baseResponse;
        }

        if (pessoaRequest.getEmail().isEmpty() || pessoaRequest.getEmail() == null) {
            baseResponse.setMessage("Favor preencher com o e-mail!");
            return baseResponse;
        }
        if (emailDuplicado) {
            baseResponse.setMessage("Este e-mail já está cadastrado!");
            return baseResponse;
        }
        if (pessoaRequest.getCpf().isEmpty() || pessoaRequest.getCpf() == null) {
            baseResponse.setMessage("Favor preencher com o CPF!");
            return baseResponse;
        }
        if (cpfDuplicado) {
            baseResponse.setMessage("Este cpf já está cadastrado!");
            return baseResponse;
        }
        if (pessoaRequest.getDataNasc().isEmpty() || pessoaRequest.getDataNasc() == null) {
            baseResponse.setMessage("Favor preencher com a data de nascimento!");
            return baseResponse;
        }
        pessoa.setNome(pessoaRequest.getNome());
        pessoa.setEmail(pessoaRequest.getEmail());
        pessoa.setCpf(pessoaRequest.getCpf());
        pessoa.setDataNasc(pessoaRequest.getDataNasc());

        pessoaRepository.save(pessoa);

        baseResponse.setStatusCode(201);
        baseResponse.setMessage("Pessoa cadastrada com sucesso!!");
        return baseResponse;

    }
}
