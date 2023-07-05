package com.java.fullProject.someConceptTesting.springActuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyCustomEndpoint implements HealthIndicator {

/*These values will be shown under the myCustomEndpoint component when we will hit the health
endpoint in actuators */
    @Override
    public Health health() {
        return Health.up()
                .withDetail("Name","Rakesh")
                .withDetail("service","up hai bhai")
                .build();

/*       We can use this information if our health is down
        return Health.down()
                .withDetail("Name","Rakesh")
                .withDetail("anything","down ho gai")
                .build();*/
    }
}
