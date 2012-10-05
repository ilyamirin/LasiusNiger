package org.lasiusniger.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author ilyamirin
 */
@ToString
@EqualsAndHashCode(of={"email"})
@Entity(name="users")
public class User implements Serializable {

    @Getter
    @Setter
    @Id
    private String email;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
