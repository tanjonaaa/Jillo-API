package model;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
@RequiredArgsConstructor
public class User {
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
