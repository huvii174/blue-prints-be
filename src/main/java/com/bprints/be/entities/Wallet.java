package com.bprints.be.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(mappedBy = "wallet")
    private User user;

    @Column(name = "created_by")
    private String createdBy = "System";

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_by")
    private String lastUpdatedBy = "System";

    @Column(name = "updated_on")
    private Date lastUpdatedOn;

    public Wallet(BigDecimal balance, User user) {
        this.balance = balance;
        this.user = user;
    }
}
