
package com.smarttravel.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients   // Required for FlightClient & HotelClient
public class BookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookingServiceApplication.class, args);
	}

	// WebClient Bean (used for User, Payment, Notification services)
	@Bean
	public WebClient.Builder webClientBuilder() {
		return WebClient.builder();
	}

}
