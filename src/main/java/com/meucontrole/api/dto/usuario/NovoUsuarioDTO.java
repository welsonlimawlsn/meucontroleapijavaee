package com.meucontrole.api.dto.usuario;

import com.meucontrole.api.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NovoUsuarioDTO {

    private String nome;
    private String sobrenome;
    private String email;
    private String senha;

    public Usuario convertToObject() {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }

}
