package com.glarimy.quiz.question.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glarimy.quiz.question.data.QuestionRepository;
import com.glarimy.quiz.question.domain.Question;

@Service
public class QuestionService {
	@Autowired
	private QuestionRepository repo;

	public Question add(Question question) {
		return repo.save(question);
	}

	public Question get(String questionId) {
		return repo.findById(questionId).orElseThrow(() -> new RuntimeException());
	}

	public List<Question> list() {
		return repo.findAll();
	}

	public List<Question> fetch(String subject) {
		return repo.findBySubject(subject);
	}
}
