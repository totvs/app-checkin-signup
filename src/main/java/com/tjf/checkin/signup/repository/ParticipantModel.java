package com.tjf.checkin.signup.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Participant Entity
 *
 * @author Marcos Paulo dos Santos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "participant")
public class ParticipantModel {

    /** The id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** The email. */
    @NotBlank(message = "{ParticipantModel.email.NotBlank}")
    @Email(message = "{ParticipantModel.email.EmailBadFormed}")
    @Column(unique = true)
    private String email;

    /** The mac address. */
    @NotBlank(message = "{ParticipantModel.macAddress.NotBlank}")
    @Column(unique = true)
    private String macAddress;

    /** The name. */
    @NotBlank(message = "{ParticipantModel.name.NotBlank}")
    private String name;

    /** The provider. */
    @NotBlank(message = "{ParticipantModel.provider.NotBlank}")
    private String provider;
}