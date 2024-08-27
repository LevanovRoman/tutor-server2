package com.testing.questions_history.controller;

import com.testing.questions_history.QuestionNotFoundException;
import com.testing.questions_history.model.Category;
import com.testing.questions_history.model.Question;
import com.testing.questions_history.service.CategoryService;
import com.testing.questions_history.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;
    private final CategoryService categoryService;

    public List<Category> getCategoryList(){
        return categoryService.getAllCategory();
    }

    @GetMapping
    public String questionHome(Model model){
        model.addAttribute("categoryList", getCategoryList());
        return "question-home";
    }

    @GetMapping("/all")
    public String allQuestions(Model model){
        List<Question> questionList = questionService.getAllQuestions();
        model.addAttribute("questionsList", questionList);
        model.addAttribute("categoryList", getCategoryList());
        return "question-all";
    }

    @GetMapping("/category/{categoryId}")
    public String questionsByCategory(@PathVariable("categoryId") int categoryId, Model model){
        System.out.println(categoryId);
        List<Question> questionList = questionService.getQuestionsByCategoryId(categoryId);
        model.addAttribute("questionsList", questionList);
        model.addAttribute("categoryList", getCategoryList());
        return "question-all";
    }

    @GetMapping("/new")
    public String showNewForm(Model model){
        model.addAttribute("categoryList", getCategoryList());
        model.addAttribute("question", new Question());
        model.addAttribute("pageTitle", "Add New Question");
        return "question-form";
    }

    @PostMapping("/save")
    public String saveQuestion(Question question, RedirectAttributes attributes){
        questionService.save(question);
        attributes.addFlashAttribute("message", "The question has been saved successfully.");
        return "redirect:/question/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes attributes){
        try{
            Question question = questionService.getQuestionById(id);
            model.addAttribute("question", question);
            model.addAttribute("pageTitle", "Edit Question (ID: " + id + ")");

            return "question-form";
        } catch (QuestionNotFoundException e) {
            attributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/question/all";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes attributes){
        try{
            questionService.deleteQuestionById(id);
            attributes.addFlashAttribute("message", "The question ID " + id + " has been deleted.");
        } catch (QuestionNotFoundException e) {
            attributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/question/all";
    }

//    @GetMapping("/createQuestion")
//    public String createQuestion(Model model){
//
//        return "addQuestion";
//    }
//
//    @PostMapping("/createQuestion")
//    public String createQuestion(@RequestParam("questionTitle") String questionTitle, @RequestParam("option1") String option1,
//                                 @RequestParam("option2") String option2, @RequestParam("option3") String option3,
//                                 @RequestParam("option4") String option4, @RequestParam("rightAnswer") String rightAnswer,
//                                 @RequestParam("difficulty_level") String difficulty_level,
//                                 @RequestParam("category") String category,
//                                 Model model) {
//        Question question = new Question(questionTitle, option1, option2, option3, option4, rightAnswer,
//                difficulty_level, category);
//        questionService.save(question);
//        return "redirect:/allQuestions";
//    }
//    @PostMapping("/save")
//    public String addQuestion(@ModelAttribute Question question){
//        questionService.save(question);
//        return "redirect:/allQuestions";
//    }
//
//    @RequestMapping("/editQuestion/{id}")
//    public String editQuestion(@PathVariable("id") int id, Model model){
//        Question question = questionService.getQuestionById(id);
//        model.addAttribute("question", question);
//        return "questionEdit";
//    }
//
//    @RequestMapping("/deleteQuestion/{id}")
//    public String deleteQuestion(@PathVariable("id") int id){
//        questionService.deleteQuestionById(id);
//        return "redirect:/allQuestions";
//    }
}
