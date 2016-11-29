package net.therap.hyperbee.web.controller;

import net.therap.hyperbee.domain.Note;
import net.therap.hyperbee.service.ActivityService;
import net.therap.hyperbee.service.StickyNoteService;
import net.therap.hyperbee.web.helper.NoteHelper;
import net.therap.hyperbee.web.helper.SessionHelper;
import net.therap.hyperbee.web.validator.NoteDateTimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.simple.SimpleLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

import static net.therap.hyperbee.utils.constant.Messages.*;
import static net.therap.hyperbee.utils.constant.Url.*;

/**
 * @author bashir
 * @since 11/22/16
 */
@Controller
public class NoteController {

    private static final Logger log = LogManager.getLogger(SimpleLogger.class);

    @Autowired
    private StickyNoteService noteService;

    @Autowired
    ActivityService activityService;

    @Autowired
    private SessionHelper sessionHelper;

    @Autowired
    private NoteHelper noteHelper;

    @Autowired
    private NoteDateTimeValidator noteDateTimeValidator;

    @InitBinder("noteCommand")
    private void noteInputInitBinder(WebDataBinder binder) {
        binder.addValidators(noteDateTimeValidator);
    }

    @GetMapping(NOTE_VIEW_URL)
    public String viewNotes(Model model, HttpSession session) {

        int userId = sessionHelper.getUserIdFromSession();
        List<Note> noteList = noteService.findActiveNotesForUser(userId);

        model.addAttribute("noteList", noteList);

        if (!model.containsAttribute("noteCommand")) {
            model.addAttribute("noteCommand", new Note());
        }
        activityService.archive(NOTE_PAGE_VIEW_ACTIVITY);

        return NOTE_VIEW_ALL;
    }

    @PostMapping(NOTE_SAVE_URL)
    public String saveNote(@Valid @ModelAttribute("noteCommand") Note note,
                           BindingResult bindingResult, @RequestParam String dateRemindString,
                           RedirectAttributes redirectAttributes,
                           Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {

            log.debug("ERROR IN SAVING NOTE");
            model.addAttribute("message", NOTE_SAVE_FAILURE);
            model.addAttribute("redirectUrl", NOTE_VIEW_URL);
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "noteCommand", bindingResult);
            redirectAttributes.addFlashAttribute("noteCommand", note);
            activityService.archive(NOTE_SAVE_FAILED);

            return "redirect:" + NOTE_VIEW_URL;
        }

        int userId = sessionHelper.getUserIdFromSession();

        log.debug("AuthUser ID: " + userId);
        log.debug("Date Remind: " + dateRemindString);

        noteHelper.processNoteForSaving(note, dateRemindString);
        noteService.saveNoteForUser(note, userId);

        model.addAttribute("message", NOTE_SAVE_SUCCESS);
        model.addAttribute("redirectUrl", NOTE_VIEW_URL);
        activityService.archive(NOTE_SAVE_ACTIVITY);

        return "redirect:/" + NOTE_VIEW_URL;
    }

    @PostMapping(NOTE_DELETE_URL)
    public String noteDelete(@PathVariable("id") int noteId, HttpSession session,
                             @ModelAttribute("noteCommand") Note note, Model model) {

        noteService.markNoteAsInactiveForUser(sessionHelper.getUserIdFromSession(), noteId);
        log.debug("Selected note ID Delete: " + noteId);

        model.addAttribute("message", NOTE_DELETE_SUCCESS);
        model.addAttribute("redirectUrl", NOTE_VIEW_URL);
        activityService.archive(NOTE_DELETE_ACTIVITY);

        return SUCCESS_VIEW;
    }
}
