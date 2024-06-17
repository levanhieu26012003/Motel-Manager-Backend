package com.lvh.pojo;

import com.lvh.pojo.Comment;
import com.lvh.pojo.User;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-17T20:47:20")
@StaticMetamodel(Searchinfo.class)
public class Searchinfo_ { 

    public static volatile SingularAttribute<Searchinfo, Integer> numRooms;
    public static volatile SingularAttribute<Searchinfo, Date> createdDate;
    public static volatile SingularAttribute<Searchinfo, BigDecimal> minPrice;
    public static volatile CollectionAttribute<Searchinfo, Comment> commentCollection;
    public static volatile SingularAttribute<Searchinfo, Boolean> active;
    public static volatile SingularAttribute<Searchinfo, String> location;
    public static volatile SingularAttribute<Searchinfo, Long> id;
    public static volatile SingularAttribute<Searchinfo, Date> updatedDate;
    public static volatile SingularAttribute<Searchinfo, BigDecimal> maxPrice;
    public static volatile SingularAttribute<Searchinfo, User> userId;

}