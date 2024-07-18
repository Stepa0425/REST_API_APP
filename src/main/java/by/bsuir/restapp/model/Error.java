package by.bsuir.restapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Error {
    private String field;
    private String message;
}
