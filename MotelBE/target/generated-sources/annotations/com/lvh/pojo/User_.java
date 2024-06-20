package com.lvh.pojo;

import com.lvh.pojo.Comment;
import com.lvh.pojo.Motel;
import com.lvh.pojo.Searchinfo;
import com.lvh.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-19T20:13:35")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, User> userCollection;
    public static volatile CollectionAttribute<User, Comment> commentCollection;
    public static volatile CollectionAttribute<User, User> userCollection1;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, Date> updatedDate;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Date> createdDate;
    public static volatile CollectionAttribute<User, Searchinfo> searchinfoCollection;
    public static volatile CollectionAttribute<User, Motel> motelCollection;
    public static volatile SingularAttribute<User, Long> id;
    public static volatile SingularAttribute<User, String> userRole;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;

}