package org.lasiusniger.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Entity(name = "zones")
public class Zone implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String UUID;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(nullable=false)
    private Client client;
    @Getter
    @Setter
    private Integer width;
    @Getter
    @Setter
    private Integer height;
    @Getter
    @Setter
    @Column(name="is_active", nullable=false)
    private Boolean isActive;
    @Getter
    @Setter
    private String strategy;
    @Getter
    @Setter
    @ManyToMany
    private Set<Banner> banners = new HashSet<Banner>();
    @Getter
    @Setter
    @Column(nullable=false)
    private String target;
}
