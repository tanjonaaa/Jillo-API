package model;

import lombok.*;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
