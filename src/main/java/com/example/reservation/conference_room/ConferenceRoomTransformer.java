package com.example.reservation.conference_room;

import com.example.reservation.organization.Organization;
import org.springframework.stereotype.Component;

@Component
public class ConferenceRoomTransformer {

    public ConferenceRoomDTO toDTO(ConferenceRoom conferenceRoom) {
        ConferenceRoomDTO conferenceRoomDTO = new ConferenceRoomDTO();
        conferenceRoomDTO.setName(conferenceRoom.getName());
        conferenceRoomDTO.setRoomNumber(conferenceRoom.getRoomNumber());
        conferenceRoomDTO.setAvailable(conferenceRoom.isAvailable());
        conferenceRoomDTO.setCapacity(conferenceRoom.getCapacity());
        conferenceRoomDTO.setLevel(conferenceRoom.getLevel());
        conferenceRoomDTO.setOrganizationName(conferenceRoom.getOrganization().getName());
        return conferenceRoomDTO;
    }

    public ConferenceRoom toEntity(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = new ConferenceRoom();
        conferenceRoom.setName(conferenceRoomDTO.getName());
        conferenceRoom.setRoomNumber(conferenceRoomDTO.getRoomNumber());
        conferenceRoom.setAvailable(conferenceRoomDTO.isAvailable());
        conferenceRoom.setCapacity(conferenceRoomDTO.getCapacity());
        conferenceRoom.setLevel(conferenceRoomDTO.getLevel());

        Organization organization = new Organization();
        organization.setName(conferenceRoomDTO.getOrganizationName());

        conferenceRoom.setOrganization(organization);
        return conferenceRoom;
    }


}
