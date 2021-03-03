package com.example.reservation.conference_room;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conferencerooms")
public class ConferenceRoomController {


    private final ConferenceRoomService conferenceRoomService;

    public ConferenceRoomController(ConferenceRoomService conferenceRoomService) {
        this.conferenceRoomService = conferenceRoomService;
    }

    @GetMapping
    public List<ConferenceRoomDTO> getAllConferenceRooms() {
        return conferenceRoomService.getAllConferenceRoomList();
    }

    @PostMapping
    public ConferenceRoomDTO addConferenceRoom(@RequestBody ConferenceRoomDTO conferenceRoomDTO) {
        return conferenceRoomService.addConferenceRoom(conferenceRoomDTO);
    }
}
