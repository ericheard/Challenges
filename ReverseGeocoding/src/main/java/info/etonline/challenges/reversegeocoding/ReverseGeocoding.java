/**
 * Created by erich on 10/26/2016.
 */
package info.etonline.challenges.reversegeocoding;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
public class ReverseGeocoding {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ReverseGeocoding.class, args);
    }
}
