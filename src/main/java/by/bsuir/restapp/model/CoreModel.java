package by.bsuir.restapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoreModel {
    private List<Error> errors;
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
