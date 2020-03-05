package io.github.tyman138.task2.specification;

import io.github.tyman138.task2.entity.Car;
import io.github.tyman138.task2.filter.CarFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarSpecification implements Specification<Car> {
    private CarFilter carFilter;

    public CarSpecification(CarFilter carFilter) {
        this.carFilter = carFilter;
    }

    @Override
    public Predicate toPredicate(Root<Car> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if (carFilter.getMark()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("mark"), carFilter.getMark()));
        }

        if (carFilter.getColor()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("color"), carFilter.getColor()));
        }

        if (carFilter.getYear() != 0) {
            predicates.add(criteriaBuilder.equal(root.get("year"), carFilter.getYear()));
        }

        if (carFilter.getCountryFactory()!=null) {
            predicates.add(criteriaBuilder.equal(root.get("countryFactory"), carFilter.getCountryFactory()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
