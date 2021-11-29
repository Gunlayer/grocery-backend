package com.endava.groceryshopservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "T_VISITORS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {
    @Id
    @Column(name = "visitorId")
    private String visitorId;

    @Column(name = "add_date")
    private LocalDate addingDate;

    @Override
    public String toString() {
        return "Visitor{" +
                "visitorId='" + visitorId + '\'' +
                ", addingDate=" + addingDate +
                '}';
    }
}
