package com.camelkyhn.springtodolistapi.middleware.bases;

import java.util.List;

public interface IService<T extends BaseEntity, TDto extends BaseDto, TFilterDto extends BaseFilterDto> {
    Result<T> get(Long id);

    Result<List<T>> list(TFilterDto filterDto);

    Result<T> create(TDto dto);

    Result<T> update(Long id, TDto dto);

    Result<Boolean> delete(Long id);
}