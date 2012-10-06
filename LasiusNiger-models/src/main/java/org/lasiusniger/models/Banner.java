package org.lasiusniger.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author ilyamirin
 */
@ToString
@EqualsAndHashCode(of = {"UUID"})
@Entity(name = "banners")
public class Banner implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String UUID;
    @Getter
    @Setter
    @ManyToOne
    private Client user;
    @Getter
    @Setter
    private Integer weight;
}
