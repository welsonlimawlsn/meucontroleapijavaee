package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_user")
public class User extends ApplicationUser {

}
