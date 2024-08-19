package com.lvh.repositories.impl;

import com.lvh.pojo.User;
import com.lvh.repositories.StatsRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsUserByPeriod(int year, String period) {
        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
        Root<User> root = criteriaQuery.from(User.class);

        Expression<Integer> periodExpression;
        if (period.equalsIgnoreCase("MONTH")) {
            periodExpression = criteriaBuilder.function("MONTH", Integer.class, root.get("createdDate"));
        } else if (period.equalsIgnoreCase("QUARTER")) {
            periodExpression = criteriaBuilder.function("QUARTER", Integer.class, root.get("createdDate"));
        } else if (period.equalsIgnoreCase("YEAR")) {
            periodExpression = criteriaBuilder.function("YEAR", Integer.class, root.get("createdDate"));
        } else {
            throw new IllegalArgumentException("Unsupported period: " + period);
        }

        criteriaQuery.multiselect(periodExpression, criteriaBuilder.count(root.get("id")));
        criteriaQuery.groupBy(periodExpression);

        // Thêm điều kiện lọc theo năm
        Predicate yearPredicate = criteriaBuilder.equal(criteriaBuilder.function("YEAR", Integer.class, root.get("createdDate")), year);

        // Biểu thức cho loại người dùng
        Expression<String> userTypeExpression = root.get("userRole");

        // Tạo truy vấn với multiselect và group by
        criteriaQuery.multiselect(periodExpression, userTypeExpression, criteriaBuilder.count(root.get("id")));
        criteriaQuery.where(yearPredicate);
        criteriaQuery.groupBy(periodExpression, userTypeExpression);
        
        
        criteriaQuery.orderBy(criteriaBuilder.asc(periodExpression));

        Query query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
