package africa.semicolon.lumexpress.data.models;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@ToString
public class Customer extends LumExpressUser{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private Cart cart;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Address> address;
}
