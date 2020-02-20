package ie.ucd.noteit.controller;

import ie.ucd.noteit.model.Note;
import ie.ucd.noteit.model.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;
    int count = 0;
    int note_count = 0;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("count", count++);
        return "hello.html";
    }

    @GetMapping("/browse")
    public String browse(Model model) {
        ArrayList<Note> notes = new ArrayList<>(noteRepository.findAll());
        model.addAttribute("notes", notes);
        System.out.println("note loaded" + notes);
        return "browse.html";
    }

    @GetMapping("/view")
    public String view(int id, Model model) {
        ArrayList<Note> notes = new ArrayList<>(noteRepository.findAll());
        model.addAttribute(notes.get(id - 1));
        System.out.println("note loaded" + notes);
        return "view.html";
    }


    @GetMapping("/create")
    public String create() {
        return "create.html";
    }

    @PostMapping("/process")
    public void process(Note note, HttpServletResponse response) throws IOException {
        note_count++;
        noteRepository.save(note);
        System.out.println("note saved as: " + note + "\n Message ID: " + note.getId());
        response.sendRedirect("/");
    }

    @GetMapping("/")
    public String index() {
        return "index.html";
    }


}