package io.nology.jobs.temp;

import jakarta.validation.constraints.NotBlank;

public class CreateTempDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

}
