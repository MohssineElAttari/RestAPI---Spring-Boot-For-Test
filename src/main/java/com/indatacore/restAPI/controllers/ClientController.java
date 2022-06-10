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
    @Autowired
    ClientServiceImpl service;
    @Autowired
    Faker faker;

    //------- All users : -------------------------------------------------------------------

    @GetMapping("/all")
    @ApiOperation(value = "Afficher la liste des clients", notes ="Cette methode permet d'afficher une liste des clients ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des clients trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des clients  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est clients"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<List<ClientDTO>> getAll(){
        List<ClientDTO> clients = service.getAllClients();
        return ResponseEntity.ok(clients);
    }

    //------- Add user : ------------------------------------------------------------------
    @PostMapping("/add")
    @ApiOperation(value = "ajouter un utilisateur", notes ="Cette methode permet d'ajouter un client")
    public ResponseEntity<ClientDTO> add(@RequestBody ClientDTO userDto )  {
        ClientDTO newuser = service.addClient(userDto);
        return new ResponseEntity<ClientDTO>(newuser, HttpStatus.CREATED);
    }
    //------- Add random user : ------------------------------------------------------------------
    @PostMapping("/random")
    @ApiOperation(value = "ajouter un utilisateur aléatoire", notes ="Cette methode permet d'ajouter un client en générant des valeurs aléatoires")
    public ResponseEntity<ClientDTO> addRandom()  {
        String firstName = faker.address().firstName();
        String lastName = faker.address().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().phoneNumber();
        ClientDTO randomclient=new ClientDTO(email,phone, firstName,lastName);
        ClientDTO newuser = service.addClient(randomclient);
        return new ResponseEntity<ClientDTO>(newuser, HttpStatus.CREATED);
    }
}
