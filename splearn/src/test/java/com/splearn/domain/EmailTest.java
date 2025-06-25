package com.splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {
    @Test
    void equality(){
        var email1 = new Email("cham@app.com");
        var email2 = new Email("cham@app.com");
        assertThat(email1).isEqualTo(email2);
    }
    

}