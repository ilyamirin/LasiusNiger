package org.lasiusniger.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ilyamirin
 */
@ToString
@Entity(name = "guests")
public class Guest implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String UUID;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
