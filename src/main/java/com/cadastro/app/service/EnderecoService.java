package com.cadastro.app.service;

import com.cadastro.app.model.Endereco;
import com.cadastro.app.model.Pessoa;
import com.cadastro.app.repository.EnderecoRepository;
import com.cadastro.app.repository.PessoaRepository;
import com.cadastro.app.request.EnderecoRequest;
import com.cadastro.app.response.BaseResponse;
import com.cadastro.app.response.ListEnderecoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    final EnderecoRepository enderecoRepository;
    final PessoaRepository pessoaRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        this.enderecoRepository = enderecoRepository;
        this.pessoaRepository = pessoaRepository;
    }


    public BaseResponse salvarCadastroEndereco(EnderecoRequest enderecoRequest){
        Endereco endereco = new Endereco();
        Pessoa pessoa = pessoaRepository.findById(enderecoRequest.getPessoa());
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(400);

        if (enderecoRequest.getLogradouro().isBlank()){
            baseResponse.setMessage("O logradouro precisa ser preenchido!");
            return baseResponse;
        }
        if (enderecoRequest.getNumero().isBlank()){
            baseResponse.setMessage("O número precisa ser preenchido!");
            return baseResponse;
        }
        if (enderecoRequest.getComplemento().isBlank()){
            baseResponse.setMessage("O complemento precisa ser preenchido!");
            return baseResponse;
        }
        if (enderecoRequest.getBairro().isBlank()){
            baseResponse.setMessage("O bairro precisa ser preenchido!");
            return baseResponse;
        }
        if (enderecoRequest.getCidade().isBlank()){
            baseResponse.setMessage("A cidade precisa ser preenchida!");
            return baseResponse;
        }
        if (enderecoRequest.getEstado().isBlank()){
            baseResponse.setMessage("O estado precisa ser preenchido!");
            return baseResponse;
        }
        if (enderecoRequest.getCep().isBlank()){
            baseResponse.setMessage("O CEP precisa ser preenchido!");
            return baseResponse;
        }


        endereco.setLogradouro(enderecoRequest.getLogradouro());
        endereco.setNumero(enderecoRequest.getNumero());
        endereco.setComplemento(enderecoRequest.getComplemento());
        endereco.setBairro(enderecoRequest.getBairro());
        endereco.setCidade(enderecoRequest.getCidade());
        endereco.setEstado(enderecoRequest.getEstado());
        endereco.setCep(enderecoRequest.getCep());
        endereco.setPessoa(pessoa);

        enderecoRepository.save(endereco);

        baseResponse.setStatusCode(201);
        baseResponse.setMessage("Endereço cadastrado com sucesso!!");
        return baseResponse;
    }

    public ListEnderecoResponse listarEnderecos(Long id) {
        ListEnderecoResponse response = new ListEnderecoResponse();
        List<Endereco> lista = enderecoRepository.findByPessoaId(id);

        if (lista.isEmpty()) {
            response.setMessage("Id informado não existente!");
            response.setStatusCode(404);
        } else {

            response.setEnderecos(lista);
            response.setStatusCode(200);
            response.setMessage("Endereços obtidos com sucesso.");


        }
            return response;
    }
}
