package com.meucontrole.api.dto.usuario;

import com.meucontrole.api.entidades.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private String id;
    private String nome;
    private String sobrenome;
    private String email;

    public static UsuarioDTO getDTOObject(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.id = usuario.getId();
        usuarioDTO.nome = usuario.getNome();
        usuarioDTO.sobrenome = usuario.getSobrenome();
        usuarioDTO.email = usuario.getEmail();
        return usuarioDTO;
    }

}
