//package com.example.crushandi.controller;
//
//import com.example.crushandi.dto.request.CommentDto;
//import com.example.crushandi.entity.BlogPost;
//import com.example.crushandi.entity.Comment;
//import com.example.crushandi.service.CommentService;
//import com.example.crushandi.service.ReplyService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/comment")
//public class CommentController {
//    private final CommentService commentService;
//    private final ReplyService replyService;
//
//    public CommentController(CommentService commentService, ReplyService replyService) {
//        this.commentService = commentService;
//        this.replyService = replyService;
//    }
//
//    @GetMapping("/all")
//    public List<Comment> getAllComments() {
//        return commentService.getAllComments();
//    }
//
//
//    @GetMapping("/{id}")
//    public Comment getCommentById(@PathVariable String id) {
//        return commentService.getComment(id);
//    }
//
//    @PostMapping("/add/{id}")
//    public BlogPost addComment(@PathVariable String id, @RequestBody CommentDto comment) {
//        return commentService.addComment(id, comment.getName(), comment.getContent());
//    }
//
//    @GetMapping("/unauthorized/{id}")
//    public ResponseEntity<?> unauthorized(@PathVariable String id) {
//        commentService.unAuthorizedComment(id);
//        return ResponseEntity.ok().body("Unauthorized");
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteSingleComment(@PathVariable String id) {
//
//       commentService.deleteComment(id);
//        return ResponseEntity.ok().body("Comment Successfully Deleted");
//    }
//
//    @DeleteMapping("/deleteall/")
//    public ResponseEntity<?> deleteMultipleComments(@RequestBody List<String> ids) {
//        commentService.deleteMultipleComments(ids);
//        return ResponseEntity.ok().body("Comment Successfully Deleted");
//    }
//
//}
