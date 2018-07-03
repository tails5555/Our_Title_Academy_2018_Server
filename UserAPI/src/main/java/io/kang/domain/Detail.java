package io.kang.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @OneToOne
    @JoinColumn(name="userId")
    User user;

    @Column(nullable=false)
    String name;

    @Column(nullable=false)
    String email;

    @Column(nullable=false)
    String homeNumber;

    @Column(nullable=false)
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name="cityId")
    City city;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ageId")
    Age age;
}
