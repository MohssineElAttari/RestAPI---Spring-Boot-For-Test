package com.indatacore.restAPI.services;

import com.indatacore.restAPI.dto.ClientDTO;
import com.indatacore.restAPI.enumeration.EnumSex;
import com.indatacore.restAPI.models.Client;

import java.util.List;

public interface IClientService {
        List<ClientDTO> getAllClients();
        ClientDTO addClient(ClientDTO client);
        List<ClientDTO>getClientFromCsv();

        //--------------------------------------Bonus--------------------------------------
        //---------------------------------------------------------------------------------
        ClientDTO findClientByID(Long id);
        ClientDTO findClientByEmail(String email);
        List<ClientDTO> getClientsBySex(EnumSex sex);
        String deleteClient(Long id);
        List<ClientDTO> CompteActive();
    }
