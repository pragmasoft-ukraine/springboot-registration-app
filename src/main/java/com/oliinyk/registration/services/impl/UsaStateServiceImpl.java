package com.oliinyk.registration.services.impl;

import com.oliinyk.registration.model.State;
import com.oliinyk.registration.repositories.UsaStateRepository;
import com.oliinyk.registration.services.UsaStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("usaStateService")
public class UsaStateServiceImpl implements UsaStateService {

    @Autowired
    private UsaStateRepository usaStateRepository;

    @Override
    public List<State> getStates() {
        return usaStateRepository.findAll();
    }

}
