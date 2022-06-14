//package com.example.crushandi.controller;
//
//import com.example.crushandi.dto.request.ReplyDto;
//import com.example.crushandi.entity.BlogPost;
//import com.example.crushandi.service.ReplyService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/reply")
//public class ReplyController {
//
//    private final ReplyService replyService;
//
//    public ReplyController(ReplyService replyService) {
//        this.replyService = replyService;
//    }
//
//
//
//
//    //ADD REPLY TO COMMENT
//    @PostMapping("/add/{postId}/{commentId}")
//    public BlogPost addReply(@PathVariable String postId, @PathVariable String commentId, @RequestBody ReplyDto replyDto) {
//        return replyService.addReply(postId, commentId, replyDto.getName(), replyDto.getContent());
//    }
//
//    @DeleteMapping("/{id}")
//    public  void deleteReply(@PathVariable String id){
//        replyService.deleteReply(id);
//    }
//
//    @GetMapping("/{id}")
//    public void  unAuthorizeReply(@PathVariable String id){
//
//    }
//
//}

