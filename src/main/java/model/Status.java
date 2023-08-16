package model;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Status {
    private int id;
    @NonNull
    private String name;
}
