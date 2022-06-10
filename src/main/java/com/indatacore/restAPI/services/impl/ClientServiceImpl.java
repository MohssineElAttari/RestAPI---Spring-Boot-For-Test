package com.indatacore.restAPI.services.impl;

import com.indatacore.restAPI.dto.ClientDTO;
import com.indatacore.restAPI.dto.service.IMapClassWithDto;
import com.indatacore.restAPI.enumeration.EnumSex;
import com.indatacore.restAPI.models.Client;
import com.indatacore.restAPI.repositories.ClientRepository;
import com.indatacore.restAPI.services.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    IMapClassWithDto<Client,ClientDTO> clientMappDto;

    @Override
    public List<ClientDTO> getAllClients() {
        Collection<Client> clients= clientRepository.findAll();
        return clientMappDto.convertListToListDto(clients,ClientDTO.class);
    }
    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        Client clinetE = clientMappDto.convertToEntity(clientDTO,Client.class);
        Client client = clientRepository.save(clinetE);
        return clientMappDto.convertToDto(client,ClientDTO.class);
    }

    @EventListener(ApplicationReadyEvent.class)
    @Override
    public List<ClientDTO> getClientFromCsv() {

        return null;
    }

    //--------------------------------------Bonus--------------------------------------
    //---------------------------------------------------------------------------------
    @Override
    public ClientDTO findClientByID(Long id) {
        Client existClient = clientRepository.findById(id).orElse(null);
        if(existClient!=null){
            Client client = clientRepository.findById(id).get();
            return clientMappDto.convertToDto(client,ClientDTO.class);
        }
        return null;
    }
    @Override
    public ClientDTO findClientByEmail(String email) {
        Client existClient = clientRepository.findByEmail(email);
        if(existClient!=null){
            Client client= clientRepository.findByEmail(email);
            return clientMappDto.convertToDto(client,ClientDTO.class);
        }
        return null;
    }

    @Override
    public List<ClientDTO> getClientsBySex(EnumSex sex) {
        List<Client> client=clientRepository.findClientBySex(sex);
        return clientMappDto.convertListToListDto(client,ClientDTO.class);
    }

    @Override
    public String deleteClient(Long id) {
        try {
            if(clientRepository.findById(id).isPresent()){
                clientRepository.deleteById(id);
                return "Customer has been removed successfully!";
            }else{
                return "The customer has not been removed!";
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return "null";
        }
    }

    public List<ClientDTO> CompteActive(){
        if (clientRepository.ClientActive()==null){
            return null;
        }
        List<Client>clients= clientRepository.ClientActive();
        return clientMappDto.convertListToListDto(clients,ClientDTO.class);
    }


}
