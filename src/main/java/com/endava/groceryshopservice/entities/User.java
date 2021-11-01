package com.endava.groceryshopservice.entities;

import com.endava.groceryshopservice.entities.user_permission.Role;
import com.endava.groceryshopservice.entities.user_permission.Status;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "T_USER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status")
    private Status status;
}
