package com.indatacore.restAPI.controllers;

import com.github.javafaker.Faker;
import com.indatacore.restAPI.dto.ClientDTO;
import com.indatacore.restAPI.enumeration.EnumSex;
import com.indatacore.restAPI.models.Client;
import com.indatacore.restAPI.response.ResponseHandler;
import com.indatacore.restAPI.services.impl.ClientServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
public class ClientController {
    
}
