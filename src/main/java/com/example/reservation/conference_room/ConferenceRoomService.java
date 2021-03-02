package com.example.reservation.conference_room;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceRoomService {


    private final ConferenceRoomRepository conferenceRoomRepository;
    private final ConferenceRoomTransformer conferenceRoomTransformer;

    public ConferenceRoomService(ConferenceRoomRepository conferenceRoomRepository, ConferenceRoomTransformer conferenceRoomTransformer) {
        this.conferenceRoomRepository = conferenceRoomRepository;
        this.conferenceRoomTransformer = conferenceRoomTransformer;
    }


    public List<ConferenceRoomDTO> getAllConferenceRoomList() {
        return conferenceRoomRepository.findAll().stream()
                .map(conferenceRoomTransformer::toDTO)
                .collect(Collectors.toList());
    }

    public ConferenceRoomDTO addConferenceRoom(ConferenceRoomDTO conferenceRoomDTO) {
        ConferenceRoom conferenceRoom = conferenceRoomTransformer.toEntity(conferenceRoomDTO);
        return conferenceRoomTransformer.toDTO(conferenceRoomRepository.save(conferenceRoom));
    }
}
