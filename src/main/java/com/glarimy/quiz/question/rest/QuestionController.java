package com.glarimy.quiz.question.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.glarimy.quiz.question.domain.Question;
import com.glarimy.quiz.question.service.QuestionService;

import io.swagger.annotations.SwaggerDefinition;

@RestController
@Validated
@CrossOrigin
@SwaggerDefinition
public class QuestionController {

	@Autowired
	private QuestionService service;

	@PostMapping("/question")
	public ResponseEntity<Question> post(@RequestBody @Valid Question question, UriComponentsBuilder builder) {
		HttpHeaders headers = new HttpHeaders();
		question = service.add(question);
		headers.setLocation(builder.path("/question/{quetionId}").buildAndExpand(question.getQuestionId()).toUri());
		return new ResponseEntity<Question>(question, headers, HttpStatus.CREATED);
	}

	@GetMapping("/question")
	public ResponseEntity<List<Question>> list(@RequestParam(value = "subject", defaultValue = "all") String subject) {
		List<Question> questions;
		if (subject.equals("all"))
			questions = service.list();
		else
			questions = service.fetch(subject);
		return new ResponseEntity<List<Question>>(questions, HttpStatus.OK);
	}

	@GetMapping("/question/{questionId}")
	public ResponseEntity<Question> get(@PathVariable("questionId") String questionId) {
		Question question = service.get(questionId);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}

}
