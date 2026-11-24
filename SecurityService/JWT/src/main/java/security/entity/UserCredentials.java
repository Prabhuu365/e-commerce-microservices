package security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_CREDENTIALS")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_seq" )
    @SequenceGenerator(name = "user_seq",
            sequenceName = "USER_CREDENTIALS_SEQ",
            allocationSize = 1)
    private Integer id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private  String password;
}
