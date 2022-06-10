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

import java.util.List;

@RestController
@RequestMapping("/v1/api/client")
public class ClientController {
    @Autowired
    ClientServiceImpl clientService;
    @Autowired
    Faker fakerClient;

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
    public ResponseEntity<Object> getAll(){
        try {
            List<ClientDTO> clients = clientService.getAllClients();
            return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, clients);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //------- Add client : ------------------------------------------------------------------
    @PostMapping("/add")
    @ApiOperation(value = "ajouter un client", notes ="Cette methode permet d'ajouter un client")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des clients trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des clients  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est clients"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<Object> add(@RequestBody ClientDTO userDto )  {
        try {
            ClientDTO client = clientService.addClient(userDto);
            return ResponseHandler.generateResponse("Successfully added data!", HttpStatus.CREATED, client);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,null);
        }
    }

    //------- Add random client : ------------------------------------------------------------------
    @PostMapping("/random")
    @ApiOperation(value = "ajouter un clinet aléatoire", notes ="Cette methode permet d'ajouter un client en générant des valeurs aléatoires")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des clients trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des clients  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est clients"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<Object> addRandomClient()  {
        try {
            String firstName = fakerClient.address().firstName();
            String lastName = fakerClient.address().lastName();
            String email = fakerClient.internet().emailAddress();
            String phone = fakerClient.phoneNumber().phoneNumber();
            ClientDTO randomclient=new ClientDTO(email,phone, firstName,lastName);
            ClientDTO newClient = clientService.addClient(randomclient);
            return ResponseHandler.generateResponse("Successfully added random data!", HttpStatus.CREATED, newClient);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.NOT_FOUND,null);
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Object> findByID(@PathVariable(name = "id") Long id){
        ClientDTO result=clientService.findClientByID(id);
        try {
            if (result==null){
                return ResponseHandler.generateResponse("unavailable data!",HttpStatus.OK,result);
            }
            return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK,result);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/byEmail/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(name = "email") String email){
        ClientDTO result = clientService.findClientByEmail(email);
        try {
            return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK,result);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/bySex/{sex}")
    public ResponseEntity<Object> findBySex(@PathVariable(name = "sex") EnumSex sex){
        List<ClientDTO> result = clientService.getClientsBySex(sex);
        try {
            return ResponseHandler.generateResponse("Successfully retrieved data!",HttpStatus.OK,result);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClient(@RequestParam(name = "id") Long id){
        try {
            clientService.deleteClient(id);
            return ResponseHandler.generateResponse("Successfully Delete Client"+"("+id+")",HttpStatus.OK,1);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

    @GetMapping("/activecompte")
    public ResponseEntity<Object> CompteActive(){
        try {
            List<ClientDTO> clientDTOList = clientService.CompteActive();
            return ResponseHandler.generateResponse("Successfully Get list compte active",HttpStatus.OK,clientDTOList);
        }catch(Exception e){
            return ResponseHandler.generateResponse(e.getMessage(),HttpStatus.MULTI_STATUS,null);
        }
    }

}
