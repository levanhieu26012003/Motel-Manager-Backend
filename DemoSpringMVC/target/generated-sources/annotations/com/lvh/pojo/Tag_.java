package com.lvh.pojo;

import com.lvh.pojo.ProdTag;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-04-16T20:50:08")
@StaticMetamodel(Tag.class)
public class Tag_ { 

    public static volatile CollectionAttribute<Tag, ProdTag> prodTagCollection;
    public static volatile SingularAttribute<Tag, String> name;
    public static volatile SingularAttribute<Tag, Integer> id;
    public static volatile SingularAttribute<Tag, String> tagcol;

}