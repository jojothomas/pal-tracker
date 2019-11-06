package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class PalTrackerApplication {


    private DataSource dataSource;

    @Autowired
    public PalTrackerApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args)
    {
        SpringApplication.run(PalTrackerApplication.class, args);
    }

    @Bean
    TimeEntryRepository timeEntryRepository() {
        return new JdbcTimeEntryRepository(this.dataSource);
    }
}
