package com.example.reservation.conference_room;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConferenceRoomService {


    private final ConferenceRoomRepository conferenceRoomRepository;


    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository) {
        this.conferenceRoomRepository = conferenceRoomRepository;
    }


    public List<ConferenceRoom> getAllConferenceRooms() {
        return conferenceRoomRepository.findAll();
    }

    public ConferenceRoom addConferenceRoom(ConferenceRoom conferenceRoom) {
        return conferenceRoomRepository.save(conferenceRoom);
    }
}
