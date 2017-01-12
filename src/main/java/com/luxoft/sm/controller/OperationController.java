package com.luxoft.sm.controller;

import com.luxoft.sm.domain.Operation;
import com.luxoft.sm.repository.OperationRepository;
import com.luxoft.sm.services.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.Map;

/**
 * Created by Luxoft on 12.01.2017.
 */
@Controller
public class OperationController {

    private OperationRepository operationRepository;
    @Autowired
    public void setOperationRepository(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @RequestMapping(value = "/operation", method = RequestMethod.POST)
    public String welcome(Authentication authentication) {
        CurrentUser currentUser = (CurrentUser) authentication.getPrincipal();
        Operation fakeOperation = new Operation(currentUser.getId(), new Date(), 1L, 200L, 2L, 400L, 2L);
        operationRepository.save(fakeOperation);
        return "redirect:user";
    }
}
