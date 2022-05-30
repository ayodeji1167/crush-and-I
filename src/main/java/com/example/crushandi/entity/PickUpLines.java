package com.example.crushandi.entity;

import lombok.*;
import org.springframework.data.annotation.Id;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PickUpLines {
    @Id
    private String id;

    private String line;


}
