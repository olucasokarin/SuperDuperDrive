package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public String noteCreate(@ModelAttribute Note note, Model model){
        if(noteService.getNote(note.getNoteId()) == null)
            noteService.createNote(note);
        else
            noteService.updateNote(note);

        model.addAttribute("result", "success");
        return "result";
    }

    @PostMapping("/{id}")
    public String deleteNote(@PathVariable("id") Integer id, Model model) {
        noteService.deleteNote(id);
        model.addAttribute("result", "success");
        return "result";
    }
}
