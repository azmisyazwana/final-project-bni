package com.finalproject.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @Column(name = "user_id")
    private Long userId;
    private String fullname;
    private Date dob;
    private String address;
    private String phone;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;
}
