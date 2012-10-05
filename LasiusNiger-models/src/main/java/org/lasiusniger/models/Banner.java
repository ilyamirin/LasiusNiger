package org.lasiusniger.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Entity(name="banners")
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
    private User user;
    @Getter
    @Setter
    private Integer weight;
    @Getter
    @Setter
    @ManyToMany
    private Set<Zone> zones = new HashSet<Zone>();
}
