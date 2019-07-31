package com.camelkyhn.springtodolistapi.middleware.bases;

import com.camelkyhn.springtodolistapi.middleware.enums.Status;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BaseService<TRepository extends JpaSpecificationExecutor<TEntity>, TEntity> {
    protected List<Predicate> applyBaseFilters(BaseFilterDto filterDto, CriteriaBuilder criteriaBuilder, Root<TEntity> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (filterDto.getStatus() != null) {
            predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("status"), filterDto.getStatus())));
        }

        predicates.add(criteriaBuilder.and(criteriaBuilder.notEqual(root.get("status"), Status.Deleted)));
        return predicates;
    }

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