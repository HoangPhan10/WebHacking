package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.undo.UndoManager;

@Controller
public class CannotUndoExceptionController {

	@RequestMapping(value = "/cue")
	public void process() {
		new UndoManager().undo();
	}
}