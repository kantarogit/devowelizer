package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.Accessors;

/**
 * Probably not that needed :) but it's a good practice to have a model for the input
 * Using Lombok to generate the getters and setters and save on boilerplate code
 * using @With to generate a wither method in combination with static constructor can be useful for creating Domain Specific Language
 */
@Data
@NoArgsConstructor(staticName = "trying")
@AllArgsConstructor
@Accessors(chain = true)
@With
public class DevowelInputModel {
    private String input;
}
