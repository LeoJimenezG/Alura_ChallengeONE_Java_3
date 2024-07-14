package com.challenge.forohub.domain.author;

import com.challenge.forohub.domain.profile.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "users")
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Profile getProfile() {
        return profile;
    }

    public void updateAuthor(UpdateAuthorDTO updateAuthorDTO){
        if (updateAuthorDTO.name() != null){
            this.name = updateAuthorDTO.name();
        }
        if (updateAuthorDTO.email() != null){
            this.email = updateAuthorDTO.email();
        }
        if (updateAuthorDTO.password() != null){
            this.password = updateAuthorDTO.password();
        }
    }
}
