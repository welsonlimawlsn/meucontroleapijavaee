package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AccountBlock extends AbstractEntity {
    private String email;
    private LocalDateTime expiration;
}
