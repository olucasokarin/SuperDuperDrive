package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NoteService {

    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }


    public int createNote(Note note) {
        User user = userService.getCurrentUser();
        return noteMapper.insert(new  Note(null, note.getNoteTitle(), note.getNoteDescription(), user.getUserId()));
    }
    public void updateNote(Note note) {
        User user = userService.getCurrentUser();
        noteMapper.update(note.getNoteId(), note.getNoteTitle(), note.getNoteDescription());
    }

    public List<Note> getNotes() {
        User user = userService.getCurrentUser();
        return noteMapper.getNotes(user.getUserId());
    }

    public Note getNote(Integer noteId) {
        return noteMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId) {
        noteMapper.deleteNote(noteId);
    }

}
