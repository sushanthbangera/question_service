package com.glarimy.quiz.question.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.glarimy.quiz.question.domain.Question;

public interface QuestionRepository extends MongoRepository<Question, String> {
	public List<Question> findBySubject(String subject);
}
