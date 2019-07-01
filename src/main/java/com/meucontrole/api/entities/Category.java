package com.meucontrole.api.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "tbl_category")
public class Category extends AbstractEntity {

    @Column(name = "cln_name", nullable = false, length = 50)
    private String name;

    @ManyToOne
    private Category parent;

    @ManyToOne(optional = false)
    private User user;
}
