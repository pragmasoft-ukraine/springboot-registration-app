package com.oliinyk.registration.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class UsaUser extends User {

    @Column(nullable = false, insertable = false, updatable = false)
    private Integer stateId;

    @OneToOne
    @JoinColumn(name = "stateId", referencedColumnName = "id")
    private State state;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
