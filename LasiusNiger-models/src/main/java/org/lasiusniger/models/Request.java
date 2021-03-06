package org.lasiusniger.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@EqualsAndHashCode
@Entity(name = "requests")
public class Request implements Serializable {

    public enum RequestType {

        CLICK, IMPRESSION;
    }
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(nullable = false)
    private Zone zone;
    @Getter
    @Setter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recieved_at", nullable = false)
    private Date recievedAt;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestType type;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(nullable = false)
    private Guest guest;
}
