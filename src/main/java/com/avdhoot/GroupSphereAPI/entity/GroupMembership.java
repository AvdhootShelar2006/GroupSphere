package com.avdhoot.GroupSphereAPI.entity;

import com.avdhoot.GroupSphereAPI.enums.GroupRole;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"group_id", "user_id"} // unique combination
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class GroupMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    private LocalDate joinedAt;

    @Enumerated(EnumType.STRING)
    private GroupRole groupRole;

}
