package io.kang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Detail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String homeNumber;

    @Column(nullable=false)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name="cityId")
    private City city;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ageId")
    private Age age;
}
