package com.cadastro.app.response;

import com.cadastro.app.model.Endereco;

import java.util.List;

public class ListEnderecoResponse extends BaseResponse{
    private List<Endereco> enderecos;

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
