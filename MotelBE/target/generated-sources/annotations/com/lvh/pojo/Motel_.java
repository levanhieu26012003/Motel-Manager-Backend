package com.lvh.pojo;

import com.lvh.pojo.Comment;
import com.lvh.pojo.Image;
import com.lvh.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-20T22:37:54")
@StaticMetamodel(Motel.class)
public class Motel_ { 

    public static volatile SingularAttribute<Motel, Float> area;
    public static volatile SingularAttribute<Motel, String> address;
    public static volatile CollectionAttribute<Motel, Comment> commentCollection;
    public static volatile SingularAttribute<Motel, Boolean> active;
    public static volatile SingularAttribute<Motel, Date> updatedDate;
    public static volatile SingularAttribute<Motel, String> title;
    public static volatile SingularAttribute<Motel, String> wards;
    public static volatile SingularAttribute<Motel, User> userId;
    public static volatile SingularAttribute<Motel, Date> createdDate;
    public static volatile SingularAttribute<Motel, String> province;
    public static volatile SingularAttribute<Motel, Integer> price;
    public static volatile SingularAttribute<Motel, String> district;
    public static volatile CollectionAttribute<Motel, Image> imageCollection;
    public static volatile SingularAttribute<Motel, Integer> numberTenant;
    public static volatile SingularAttribute<Motel, Long> id;
    public static volatile SingularAttribute<Motel, String> status;

}