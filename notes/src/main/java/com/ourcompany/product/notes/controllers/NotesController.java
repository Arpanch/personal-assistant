package com.ourcompany.product.notes.controllers;

import com.ourcompany.product.notes.dto.NotesServiceRequest;
import com.ourcompany.product.notes.dto.NotesServiceResponse;
import com.ourcompany.product.notes.helper.NotesHelper;
import com.ourcompany.product.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notes")
public class NotesController {

    @Value("${notes.replication.factor}")
    Integer replicaCount;

    @Autowired
    private NotesService notesService;

    @PostMapping("/save")
    @ResponseBody
    public NotesServiceResponse saveNotes(@RequestBody NotesServiceRequest request){
        return NotesHelper.createNoteResponseFromNotes(notesService.saveAllNotes(request));
    }

    @GetMapping("/get/user/{userId}")
    @ResponseBody
    public NotesServiceResponse getNotesByUserId(@PathVariable Long userId){
    	NotesServiceResponse response=  NotesHelper.createNoteResponseFromNotes(notesService.fetchNotesForUserId(userId));
    	return response;
    }

    @GetMapping("/get/key/{key}/")
    @ResponseBody
	public NotesServiceResponse getNotesByKeyword(@PathVariable String key) {
		NotesServiceResponse response = NotesHelper.createNoteResponseFromNotes(notesService.fetchNotesForKeyword(key));
		return response;
	}
}
