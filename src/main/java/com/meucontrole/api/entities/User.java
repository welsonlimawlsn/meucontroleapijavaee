package com.meucontrole.api.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBLUSER")
public class User extends ApplicationUser {
}
