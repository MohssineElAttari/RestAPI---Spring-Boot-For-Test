package com.indatacore.restAPI.dto.service;

import java.util.Collection;
import java.util.List;

public interface IMapClassWithDto<M, D> {

    D convertToDto(M model, Class<D> dtoClass);

    M convertToEntity(D dto, Class<M> modelClass);

    List<D> convertListToListDto(Collection<M> modelList, Class<D> outCLass);

    List<M> convertListToListEntity(Collection<D> dtoList, Class<M> outCLass);
}
