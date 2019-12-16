package com.oliinyk.registration.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class StateSiblings implements Serializable {

    @Id
    @Column(insertable = false, updatable = false)
    private Integer firstSiblingStateId;

    @Id
    @Column(insertable = false, updatable = false)
    private Integer secondSiblingStateId;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "firstSiblingStateId")
    private State firstSiblingState;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "secondSiblingStateId")
    private State secondSiblingState;

}
