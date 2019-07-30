package com.camelkyhn.springtodolistapi.middleware.bases;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public class BaseService<TRepository extends JpaSpecificationExecutor<TEntity>, TEntity> {
    protected List<TEntity> applyPagination(TRepository repository, BaseFilterDto filterDto, Specification<TEntity> specification) {
        List<TEntity> resultList;
        filterDto.setTotalCount(repository.count(specification));
        if (filterDto.getAllData()) {
            resultList = repository.findAll(specification);
            filterDto.setPageNumber(1);
            filterDto.setPageSize(resultList.size());
        } else {
            resultList = repository.findAll(specification, PageRequest.of(filterDto.getPageNumber(), filterDto.getPageSize()).first()).getContent();
        }

        return resultList;
    }
}