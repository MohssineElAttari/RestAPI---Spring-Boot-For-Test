package com.indatacore.restAPI.dto.service;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapClassWithDto<M,D> implements IMapClassWithDto<M,D>{

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public D convertToDto(M model, Class<D> dtoClass) {
        if(model == null){
            return null;
        }
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(model,dtoClass);
    }

    @Override
    public M convertToEntity(D dto, Class<M> modelClass) {
        if(dto == null)
            return null;

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT).setPropertyCondition(Conditions.isNotNull());
        return modelMapper.map(dto, modelClass);
    }

    @Override
    public List<D> convertListToListDto(Collection<M> modelList, Class<D> outCLass) {
        if(modelList == null)
            return Collections.emptyList();

        return modelList.stream().map(entity -> convertToDto(entity, outCLass)).collect(Collectors.toList());
    }

    @Override
    public List<M> convertListToListEntity(Collection<D> dtoList, Class<M> outCLass) {
        if(dtoList == null)
            return Collections.emptyList();

        return dtoList.stream().map(dto -> convertToEntity(dto, outCLass)).collect(Collectors.toList());
    }

}
