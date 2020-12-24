# JPA-Query

## JPA 查询语句拼接


```java
package com.ergo.pluse.type;

import com.github.salpadding.jpa.And;
import com.github.salpadding.jpa.Column;
import com.github.salpadding.jpa.QuerySuffix;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class OrderQuery extends PageSize{
    public static final String QUERY = "select m from MessageEntity m ";
    private LocalDate after;

    private LocalDate before;

    private String phone;

    private Long orderId;

    private String name;

    private String vendor;

    private Long userId;

    private ApplicationContext context;


    public void setBefore(String before) {
        this.before = before == null ? null : LocalDate.parse(before);
    }

    public void setAfter(String after) {
        this.after = after == null ? null : LocalDate.parse(after);
    }


    public void query(){
        EntityManager em = context.getBean(EntityManager.class);
        And and = new And();

        if(after != null){
            and.addChild(new Column("m.createdAt", ">=", after));
        }

        if(before != null){
            and.addChild(new Column("m.createdAt", "<", before));
        }

        if(phone != null && !phone.isEmpty()){
            and.addChild(new Column("m.phone", "=", phone));
        }

        if(name != null && !name.isEmpty()){
            and.addChild(new Column("m.name", "=", name));
        }

        if(vendor != null && !vendor.isEmpty()){
            and.addChild(new Column("m.username", "=", vendor));
        }

        if(userId != null){
            and.addChild(new Column("m.id", "=", userId));
        }

        if(orderId != null){
            and.addChild(new Column("m.orderId", "=", orderId));
        }

        QuerySuffix sf = and.build();

        String suffix = (and.isEmpty() ? "" : " where ") + sf.getSuffix();
        Query q = em.createQuery(QUERY + suffix);
        sf.getParams().forEach(q::setParameter);
    }
}

```