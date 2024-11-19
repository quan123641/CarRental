package com.hoaiphong.carrental.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "feedbacks")
public class FeedBack {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "Rating")
    private int rating;

    @Column(name = "Content")
    private String content;

    @Column(name = "Date_time")
    private LocalDateTime dateTime;

    @OneToOne
    @JoinColumns({
        @JoinColumn(name = "booking_id", referencedColumnName = "booking_id", updatable = false),
        @JoinColumn(name = "car_id", referencedColumnName = "car_id", updatable = false)
    })
    private CarBooking carBooking;
}