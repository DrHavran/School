package test.test;


import org.springframework.web.bind.annotation.*;
import test.test.Models.Form.RegisterFormModel;
import test.test.Objects.Note;
import test.test.Objects.Reminder;
import test.test.Objects.User;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class LeadsController {
    private final HashMap<String, User> users;
    private final HashMap<Integer, ArrayList<Note>> notes;
    private final HashMap<Integer, ArrayList<Reminder>> reminders;

    public LeadsController() {
        this.users = new HashMap<>();
        this.notes = new HashMap<>();
        this.reminders = new HashMap<>();
    }

    @PostMapping("/leads")
    public User register(
            @RequestBody RegisterFormModel model
    ){
        if(users.containsKey(model.getEmail())){
            return users.get(model.getEmail());
        }else{
            User user = new User(
                    users.size(),
                    model.getName(),
                    model.getEmail()
            );
            users.put(model.getEmail(), user);
            return user;
        }
    }

    @PostMapping("/leads/{id}/notes")
    public void addNote(
            @PathVariable int id,
            @RequestBody Note note
    ){
        ArrayList<Note> customerNotes;
        if(notes.containsKey(id)){
            customerNotes = notes.get(id);
        }else{
            customerNotes = new ArrayList<>();
            notes.put(id, customerNotes);
        }

        customerNotes.add(note);
    }

    @GetMapping("/leads/{id}/notes")
    public ArrayList<Note> getNotes(
        @PathVariable int id
    ){
        return notes.get(id);
    }

    @PostMapping("/leads/{id}/reminders")
    public void addReminder(
            @PathVariable int id,
            @RequestBody Reminder reminder
    ){
        ArrayList<Reminder> customerReminders;
        if(reminders.containsKey(id)){
            customerReminders = reminders.get(id);
        }else {
            customerReminders = new ArrayList<>();
            reminders.put(id, customerReminders);
        }

        customerReminders.add(reminder);
    }

    @GetMapping("/leads/{id}/reminders")
    public ArrayList<Reminder> getReminders(
            @PathVariable int id
    ){
        return reminders.get(id);
    }
}
