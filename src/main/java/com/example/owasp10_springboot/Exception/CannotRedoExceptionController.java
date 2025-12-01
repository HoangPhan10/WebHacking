package com.example.owasp10_springboot.Exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.undo.UndoManager;

@Controller
public class CannotRedoExceptionController {

	@RequestMapping(value = "/cre")
	public void process() {
		new UndoManager().redo();
	}
}