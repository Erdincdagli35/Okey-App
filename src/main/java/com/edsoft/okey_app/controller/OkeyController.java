package com.edsoft.okey_app.controller;

import com.edsoft.okey_app.response.GamePrepareInfo;
import com.edsoft.okey_app.response.GameStartInfo;
import com.edsoft.okey_app.service.OkeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class OkeyController {

    @Autowired
    private OkeyService okeyService;

    @GetMapping("/prepare")
    public ResponseEntity<GamePrepareInfo> gamePrepare() {
        return ResponseEntity.ok(okeyService.getGamePrepareInfo());
    }

    @GetMapping("/start")
    public ResponseEntity<GameStartInfo> gameStart() {
        return ResponseEntity.ok(okeyService.gameStart());
    }

    @GetMapping("/best/player")
    public ResponseEntity<Integer> getBestPlayer() {
        return ResponseEntity.ok(okeyService.getBestPlayer());
    }
}
